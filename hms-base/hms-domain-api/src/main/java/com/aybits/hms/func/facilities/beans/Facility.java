package com.aybits.hms.func.facilities.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.vouchers.beans.Voucher;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("facility")
public class Facility {

        @JsonProperty("facility_id")
        private String  facilityId;
        @JsonProperty("facility_message_key")
        private String  facilityMessageKey;
        @JsonProperty("facility_description")
        private String  facilityDescription;
        @JsonProperty("facility_availability")
        private Boolean facilityAvailability;
        @JsonProperty("is_chargeable")
        private Boolean isChargeable;
        @JsonProperty("facility_type")
        private String facilityType;
        @JsonProperty("facility_price")
        private Double facilityPrice;
        @JsonProperty("applicable_voucher")
        private List<Voucher> voucherList;

        public Facility() {
        }

        public Facility(String facilityId, String facilityMessageKey, String facilityDescription,
                        Boolean facilityAvailability,
                        Boolean isChargeable,
                        Double facilityPrice,
                        List<Voucher> voucherList) {
                this.facilityId = facilityId;
                this.facilityMessageKey = facilityMessageKey;
                this.facilityDescription = facilityDescription;
                this.facilityAvailability = facilityAvailability;
                this.isChargeable = isChargeable;
                this.facilityType = facilityType;
                this.facilityPrice = facilityPrice;
                this.voucherList = voucherList;
        }

        public String getFacilityId() {
                return facilityId;
        }

        public void setFacilityId(String facilityId) {
                this.facilityId = facilityId;
        }

        public String getFacilityMessageKey() {
                return facilityMessageKey;
        }

        public void setFacilityMessageKey(String facilityMessageKey) {
                this.facilityMessageKey = facilityMessageKey;
        }

        public String getFacilityDescription() {
                return facilityDescription;
        }

        public void setFacilityDescription(String facilityDescription) {
                this.facilityDescription = facilityDescription;
        }

        public Boolean getFacilityAvailability() {
                return facilityAvailability;
        }

        public void setFacilityAvailability(Boolean facilityAvailability) {
                this.facilityAvailability = facilityAvailability;
        }

        public String getFacilityType() {
                return facilityType;
        }

        public void setFacilityType(String facilityType) {
                this.facilityType = facilityType;
        }

        public Double getFacilityPrice() {
                return facilityPrice;
        }

        public void setFacilityPrice(Double facilityPrice) {
                this.facilityPrice = facilityPrice;
        }

        public List<Voucher> getVoucherList() {
                return voucherList;
        }

        public void setVoucherList(List<Voucher> voucherList) {
                this.voucherList = voucherList;
        }

        public Boolean getChargeable() {
                return isChargeable;
        }

        public void setChargeable(Boolean chargeable) {
                isChargeable = chargeable;
        }

        @Override
        public String toString(){
                return HMSJSONParser.convertObjectToJSON((Object)this);
        }
}
