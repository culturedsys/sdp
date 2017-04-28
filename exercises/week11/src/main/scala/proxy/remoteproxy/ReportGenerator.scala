package proxy.remoteproxy

import java.rmi.Remote
import java.rmi.RemoteException

trait ReportGenerator extends Remote {
  @throws[RemoteException]
  def generateDailyReport(): String
}
