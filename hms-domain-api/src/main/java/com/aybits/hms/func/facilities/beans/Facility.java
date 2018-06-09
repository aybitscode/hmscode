package com.aybits.hms.func.facilities.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("facility")
public class Facility {

        @JsonProperty("facility_id")
        private String  facilityId;
        @JsonProperty("facility_message_key")
        private String  facilityMessageKey;
        private String  facilityDescription;
        private Boolean facilityAvailability;




}
