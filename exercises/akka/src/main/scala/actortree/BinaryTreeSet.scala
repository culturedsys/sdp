package actortree

import akka.actor._
import scala.collection.immutable.Queue

object BinaryTreeSet {

  trait Operation {
    def requester: ActorRef
    def id: Int
    def elem: Int
  }

  trait OperationReply {
    def id: Int
  }

  /** Request with identifier `id` to insert an element `elem` into the tree.
    * The actor at reference `requester` should be notified when this operation
    * is completed.
    */
  case class Insert(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request with identifier `id` to check whether an element `elem` is present
    * in the tree. The actor at reference `requester` should be notified when
    * this operation is completed.
    */
  case class Contains(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request with identifier `id` to remove the element `elem` from the tree.
    * The actor at reference `requester` should be notified when this operation
    * is completed.
    */
  case class Remove(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request to perform garbage collection*/
  case object GC

  /** Holds the answer to the Contains request with identifier `id`.
    * `result` is true if and only if the element is present in the tree.
    */
  case class ContainsResult(id: Int, result: Boolean) extends OperationReply
  
  /** Message to signal successful completion of an insert or remove operation. */
  case class OperationFinished(id: Int) extends OperationReply

}


class BinaryTreeSet extends Actor {
  import BinaryTreeSet._
  import BinaryTreeNode._

  def createRoot: ActorRef = context.actorOf(BinaryTreeNode.props(0, initiallyRemoved = true))

  var root = createRoot

  var pendingQueue = Vector.empty[Operation]

  def receive = normal

  /** Accepts `Operation` and `GC` messages. */
  def normal: Receive = {
    case op: Operation => root ! op
    case GC =>
      val newRoot = createRoot
      root ! CopyTo(newRoot)
      context.become(garbageCollecting(newRoot))
  }

  /** Handles messages while garbage collection is performed.
    * `newRoot` is the root of the new binary tree where we want to copy
    * all non-removed elements into.
    */
  def garbageCollecting(newRoot: ActorRef): Receive = {
    case op: Operation =>
      pendingQueue = pendingQueue :+ op
    case CopyFinished =>
      pendingQueue.foreach(newRoot ! _)
      pendingQueue = Vector.empty
      root = newRoot
      context.become(normal)
  }
}

object BinaryTreeNode {
  trait Position

  case object Left extends Position
  case object Right extends Position

  case class CopyTo(treeNode: ActorRef)
  case object CopyFinished

  def props(elem: Int, initiallyRemoved: Boolean) = Props(classOf[BinaryTreeNode],  elem, initiallyRemoved)
}

class BinaryTreeNode(val elem: Int, initiallyRemoved: Boolean) extends Actor {
  import BinaryTreeNode._
  import BinaryTreeSet._

  var left : Option[ActorRef] = None
  var right: Option[ActorRef] = None

  var removed: Boolean = initiallyRemoved

  def receive: Receive = normal

  /** Handles `Operation` messages and `CopyTo` requests. */
  val normal: Receive = {
    case op @ Contains(requester, id, needle) =>
      if (needle == elem) {
        requester ! ContainsResult(id, !removed)
      } else {
        val child = if (needle < elem) left else right
        child match {
          case None => requester ! ContainsResult(id, result = false)
          case Some(actor) => actor ! op
        }
      }

    case op @ Insert(requester, id, newElem) =>
      if (newElem == elem) {
        removed = false
        requester ! OperationFinished(id)
      } else {
        val child = if (newElem < elem) left else right
        val newChild = child match {
          case None =>
            requester ! OperationFinished(id)
            Some(context.actorOf(BinaryTreeNode.props(newElem, initiallyRemoved = false)))
          case Some(actor) =>
            actor ! op
            child
        }
        if (newElem < elem)
          left = newChild
        else
          right = newChild
      }

    case op @ Remove(requester, id, needle) =>
      if (needle == elem) {
        removed = true
        requester ! OperationFinished(id)
      } else {
        val child = if (needle < elem) left else right
        child match {
          case None => requester ! OperationFinished(id)
          case Some(actor) => actor ! op
        }
      }

    case CopyTo(destination) =>
      val needConfirmation = if (removed) {
        false
      } else {
        destination ! Insert(self, 1, elem)
        true
      }

      val expected = for (Some(actor) <- Set(left, right))
        yield {
          actor ! CopyTo(destination)
          actor
        }
      context.become(copying(expected, !needConfirmation))
  }

  /** `expected` is the set of ActorRefs whose replies we are waiting for,
    * `insertConfirmed` tracks whether the copy of this node to the new tree has been confirmed.
    */
  def copying(expected: Set[ActorRef], insertConfirmed: Boolean): Receive = {
    if (expected.isEmpty && insertConfirmed) {
      context.parent ! CopyFinished
      context.become(normal)
      normal
    } else {
      case CopyFinished =>
        context.become(copying(expected - sender, insertConfirmed))
      case OperationFinished(_) =>
        context.become(copying(expected, insertConfirmed = true))
    }
  }
}
