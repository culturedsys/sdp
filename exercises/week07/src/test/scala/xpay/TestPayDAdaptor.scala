package xpay

import org.scalatest.FunSpec

/**
  * Test the adaptor between Xpay and PayD traits
  */
class TestPayDAdaptor extends FunSpec {
  describe("PayDAdaptor") {
    val amount = 100.0
    val cvvno: Short = 123
    val expmonth = "01"
    val expyear = "18"
    val cardno = "1234567890123456"
    val customer = "A Customer"

    it("can get values from an Xpay object") {
      val xpay: Xpay = new XpayImpl

      xpay.setAmount(amount)
      xpay.setCardCVVNo(cvvno)
      xpay.setCardExpMonth(expmonth)
      xpay.setCardExpYear(expyear)
      xpay.setCreditCardNo(cardno)
      xpay.setCustomerName(customer)

      val payd: PayD = new PayDAdaptor(xpay)

      assert(payd.getTotalAmount == amount)
      assert(payd.getCVVNo == cvvno)
      assert(payd.getCardExpMonthDate == expmonth + expyear)
      assert(payd.getCustCardNo == cardno)
      assert(payd.getCardOwnerName == customer)
    }

    it("can set values in an Xpay object") {
      val xpay: Xpay = new XpayImpl
      val payd: PayD = new PayDAdaptor(xpay)

      payd.setCardExpMonthDate(expmonth + expyear)
      payd.setCardOwnerName(customer)
      payd.setCustCardNo(cardno)
      payd.setCVVNo(cvvno.toInt)
      payd.setTotalAmount(amount)

      assert(xpay.getCardExpMonth === expmonth)
      assert(xpay.getCardExpYear === expyear)
      assert(xpay.getCustomerName === customer)
      assert(xpay.getCreditCardNo === cardno)
      assert(xpay.getCardCVVNo === cvvno)
      assert(xpay.getAmount === amount)
    }

  }
}
