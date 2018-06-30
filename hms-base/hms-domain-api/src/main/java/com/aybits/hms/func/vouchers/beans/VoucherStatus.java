package com.aybits.hms.func.vouchers.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VoucherStatus {

    VOUCHER_EXPIRED(-1),
    VOUCHER_INVALID(0),
    VOUCHER_APPLICABLE(1),
    VOUCHER_DISABLED(-2),
    VOUCHER_DELETED(-3);

    @JsonProperty("voucher_status")
    private Integer voucherStatus;

    VoucherStatus(Integer voucherStatus){
        this.voucherStatus = voucherStatus;
    }

    public int getVoucherStatusAsInt() {
        return voucherStatus;
    }

    public String getVoucherStatusAsString() {
        return String.valueOf(voucherStatus);
    }

    public static VoucherStatus convertIntToVoucherStatus(int iVoucherStatus) {
        for (VoucherStatus voucherStatus : VoucherStatus.values()) {
            if (voucherStatus.getVoucherStatusAsInt() == iVoucherStatus) {
                return voucherStatus;
            }
        }
        return null;
    }

    public static VoucherStatus convertStringToVoucherStatus(String inputVoucherStatus) {
        for (VoucherStatus voucherStatus : VoucherStatus.values()) {
            if (voucherStatus.getVoucherStatusAsString().equals(inputVoucherStatus)) {
                return voucherStatus;
            }
        }
        return null;
    }

    public static int convertVoucherStatusToInt(VoucherStatus inputVoucherStatus) {
        for (VoucherStatus voucherStatus : VoucherStatus.values()) {
            if (voucherStatus.getVoucherStatusAsInt() == inputVoucherStatus.getVoucherStatusAsInt()) {
                return voucherStatus.getVoucherStatusAsInt();
            }
        }
        return -1;
    }

    public static String convertVoucherStatusToString(VoucherStatus inputVoucherStatus) {
        for (VoucherStatus voucherStatus : VoucherStatus.values()) {
            if (voucherStatus.getVoucherStatusAsInt() == inputVoucherStatus.getVoucherStatusAsInt()) {
                return voucherStatus.getVoucherStatusAsString();
            }
        }
        return null;
    }
}
