package com.aybits.hms.func.facility.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.voucher.beans.Voucher;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("facility")
public class Facility {

        @JsonProperty("facility_id")
        private String  facilityId;
        @JsonProperty("facility_name")
        private String facilityName;
        @JsonProperty("facility_description")
        private String  facilityDescription;
        @JsonProperty("facility_availability")
        private Boolean isFacilityAvailable;
        @JsonProperty("is_chargeable")
        private Boolean isChargeable;
        @JsonProperty("facility_type")
        private FacilityType facilityType;
        @JsonProperty("facility_price")
        private Double facilityPrice;
        @JsonProperty("applicable_voucher")
        private Voucher voucher;

        public Facility() {
        }

        public Facility(String facilityId, String facilityName, String facilityDescription,
                        FacilityType facilityType,
                        Boolean isFacilityAvailable,
                        Boolean isChargeable,
                        Double facilityPrice,
                        Voucher voucher) {
                this.facilityId = facilityId;
                this.facilityName = facilityName;
                this.facilityDescription = facilityDescription;
                this.isFacilityAvailable = isFacilityAvailable;
                this.isChargeable = isChargeable;
                this.facilityType = facilityType;
                this.facilityPrice = facilityPrice;
                this.voucher = voucher;
        }

        public String getFacilityId() {
                return facilityId;
        }

        public void setFacilityId(String facilityId) {
                this.facilityId = facilityId;
        }

        public String getFacilityName() {
                return facilityName;
        }

        public void setFacilityName(String facilityName) {
                this.facilityName = facilityName;
        }

        public String getFacilityDescription() {
                return facilityDescription;
        }

        public void setFacilityDescription(String facilityDescription) {
                this.facilityDescription = facilityDescription;
        }

        public Boolean getIsFacilityAvailable() {
                return isFacilityAvailable;
        }

        public void setIsFacilityAvailable(Boolean isFacilityAvailable) {
                this.isFacilityAvailable = isFacilityAvailable;
        }

        public FacilityType getFacilityType() {
                return facilityType;
        }

        public void setFacilityType(FacilityType facilityType) {
                this.facilityType = facilityType;
        }

        public Double getFacilityPrice() {
                return facilityPrice;
        }

        public void setFacilityPrice(Double facilityPrice) {
                this.facilityPrice = facilityPrice;
        }

        public Voucher getVoucher() {
                return voucher;
        }

        public void setVoucher(Voucher voucher) {
                this.voucher = voucher;
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
