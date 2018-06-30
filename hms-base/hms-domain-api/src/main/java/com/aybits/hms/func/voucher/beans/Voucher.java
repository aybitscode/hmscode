package com.aybits.hms.func.voucher.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;

@JsonRootName("voucher")
public class Voucher {

    @JsonProperty("voucher_id")
    private String voucherId;
    @JsonProperty("voucher_name")
    private String voucherName;
    @JsonProperty("voucher_description")
    private String voucherDescription;
    @JsonProperty("voucher_start_date")
    private Date voucherStartDate;
    @JsonProperty("voucher_expiry_date")
    private Date voucherExpiryDate;
    @JsonProperty("voucher_discount")
    private Double voucherDiscount;
    @JsonProperty("voucher_status")
    private VoucherStatus voucherStatus;

    public Voucher() {
    }

    public Voucher(String voucherId, String voucherName, String voucherDescription,
                   Date voucherStartDate, Date voucherExpiryDate, Double voucherDiscount,
                   VoucherStatus voucherStatus) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.voucherDescription = voucherDescription;
        this.voucherStartDate = voucherStartDate;
        this.voucherExpiryDate = voucherExpiryDate;
        this.voucherDiscount = voucherDiscount;
        this.voucherStatus = voucherStatus;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getVoucherDescription() {
        return voucherDescription;
    }

    public void setVoucherDescription(String voucherDescription) {
        this.voucherDescription = voucherDescription;
    }

    public Date getVoucherStartDate() {
        return voucherStartDate;
    }

    public void setVoucherStartDate(Date voucherStartDate) {
        this.voucherStartDate = voucherStartDate;
    }

    public Date getVoucherExpiryDate() {
        return voucherExpiryDate;
    }

    public void setVoucherExpiryDate(Date voucherExpiryDate) {
        this.voucherExpiryDate = voucherExpiryDate;
    }

    public Double getVoucherDiscount() {
        return voucherDiscount;
    }

    public void setVoucherDiscount(Double voucherDiscount) {
        this.voucherDiscount = voucherDiscount;
    }

    public VoucherStatus getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(VoucherStatus voucherStatus) {
        this.voucherStatus = voucherStatus;
    }


    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }
}
