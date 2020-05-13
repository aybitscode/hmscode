package com.aybits.hms.func.room.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("room_price")
public class RoomPrice {
    @JsonProperty("room_base_price")
    private Double roomBasePrice;
    @JsonProperty("room_facilities_price")
    private Double roomFacilitiesPrice;
    @JsonProperty("applied_voucher_rebate")
    private Double appliedVoucherRebate;
    @JsonProperty("applied_faciliteis_charges")
    private Double appliedFacilitiesCharges;

}
