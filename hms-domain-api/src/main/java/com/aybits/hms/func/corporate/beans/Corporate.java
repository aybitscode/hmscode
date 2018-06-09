package com.aybits.hms.func.corporate.beans;

import com.aybits.hms.api.func.common.beans.ContactDetails;
import com.aybits.hms.api.func.common.beans.HMSAddress;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.HMSAddress;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Corporate {


            @JsonProperty("corporate_id")
            private String corporateId;
            @JsonProperty("corporate_name")
            private String corporateName;
            @JsonProperty("address")
            private HMSAddress corporateAddress;
            @JsonProperty("contact_details")
            private ContactDetails corporateContactDetails;

}
