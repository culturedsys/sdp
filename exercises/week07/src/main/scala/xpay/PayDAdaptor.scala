package xpay

/**
  * An adaptor from Xpay to PayD
  */
class PayDAdaptor(xpay: Xpay) extends PayD {
  override def getCustCardNo: String = xpay.getCreditCardNo

  override def setCustCardNo(custCardNo: String): Unit = xpay.setCreditCardNo(custCardNo)

  override def getCVVNo: Integer = xpay.getCardCVVNo.toInt
  override def setCVVNo(cVVNo: Integer): Unit = xpay.setCardCVVNo(cVVNo.toShort)

  // I'm assuming that PayD's CardExpMonthDate is a four-digit MMYY code
  // and that Xpay's ExpMonth and ExpYear are two-digit codes
  override def getCardExpMonthDate: String =
    xpay.getCardExpMonth + xpay.getCardExpYear
  override def setCardExpMonthDate(cardExpMonthDate: String): Unit = {
    xpay.setCardExpMonth(cardExpMonthDate.substring(0, 2))
    xpay.setCardExpYear(cardExpMonthDate.substring(2, 4))
  }

  // I suppose strictly the card owner might not be the customer, and so CardOwnerName and CustomerName might not
  // be equivalent; in that case, these methods would need more complicated implementations.
  override def getCardOwnerName: String = xpay.getCustomerName
  override def setCardOwnerName(cardOwnerName: String): Unit = xpay.setCustomerName(cardOwnerName)

  override def getTotalAmount: Double = xpay.getAmount
  override def setTotalAmount(totalAmount: Double): Unit = xpay.setAmount(totalAmount)
}
