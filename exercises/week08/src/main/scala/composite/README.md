# (a) What is the Composite Pattern?

The composite design pattern involves a hierarchical data structure where elements of the hierarchy, both containers
and leaves, can be treated uniformly.

# (b) Under what conditions would you use a Composite Design Pattern?

You use the composite pattern when you have a hierarchical data structure, and there are operations which makes sense
to perform on both leaf and container nodes.

# (c) What are the four participants of the Composite Design Pattern?

 * Component, an interface specifying operations which can be applied to both container and leaf nodes
 * Container, an implementation of the Component interface which implements the operations in a way approptiate for
   nodes that may contain other nodes
 * Leaf, an implementation of the Component interface which represents nodes which do not contain other nodes. It may
   treat some operations (such as add child) as a no-op, and respond to other operations as if it were any empty
   container (such as a request for a list of child nodes, to which it could return an empty list). Alternatively, the
   Component interface may not specify such operations, in which case the Leaf class will not implement them.
 * The client, which accesses nodes in the hierarchy through the uniform Component interface
