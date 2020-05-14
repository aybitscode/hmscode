package com.aybits.hms.func.payment.beans;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentParams {

    @JsonProperty("payment_type")
    private PaymentType paymentType;
    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("payment_description")
    private String paymentDescription;


    public PaymentParams() {
    }

    public PaymentParams(PaymentType paymentType, String paymentId, String paymentDescription) {
        this.paymentType = paymentType;
        this.paymentId = paymentId;
        this.paymentDescription = paymentDescription;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }
}
