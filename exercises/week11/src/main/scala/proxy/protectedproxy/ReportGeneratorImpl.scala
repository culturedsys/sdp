package proxy.protectedproxy

class ReportGeneratorImpl extends ReportGenerator {
  override def generateDailyReport(): String =
    """ |********************Location X Daily Report********************
        |Location ID: 012
        |TodayâĂŹs Date: Sun Sep 14 13:28:12 IST 2014
        |Total Pizza Sell: 112
        |Total Sale: $2534
        |Net Profit: $1985
        |***************************************************************""".stripMargin

}
