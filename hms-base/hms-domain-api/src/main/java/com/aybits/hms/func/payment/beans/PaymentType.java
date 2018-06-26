package com.aybits.hms.func.payment.beans;

public enum PaymentType {

    CARD(1),
    CASH(2),
    CHEQUE(3),
    MOBILE_WALLET(4);

    private Integer paymentType;

    PaymentType(Integer paymentType){
        this.paymentType = paymentType;
    }

    public int getPaymentTypeAsInt() {
        return paymentType;
    }

    public String getPaymentTypeAsString() {
        return String.valueOf(paymentType);
    }

    public static PaymentType convertIntToPaymentType(int iPaymentType) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.getPaymentTypeAsInt() == iPaymentType) {
                return paymentType;
            }
        }
        return null;
    }

    public static PaymentType convertStringToPaymentType(String inputPaymentType) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.getPaymentTypeAsString().equals(inputPaymentType)) {
                return paymentType;
            }
        }
        return null;
    }

    public static int convertPaymentTypeToInt(PaymentType inputPaymentType) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.getPaymentTypeAsInt() == inputPaymentType.getPaymentTypeAsInt()) {
                return paymentType.getPaymentTypeAsInt();
            }
        }
        return -1;
    }

    public static String convertPaymentTypeToString(PaymentType inputPaymentType) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.getPaymentTypeAsInt() == inputPaymentType.getPaymentTypeAsInt()) {
                return paymentType.getPaymentTypeAsString();
            }
        }
        return null;
    }

}
