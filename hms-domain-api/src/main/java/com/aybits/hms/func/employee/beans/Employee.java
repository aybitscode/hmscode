package com.aybits.hms.func.employee.beans;

import com.aybits.hms.api.func.common.beans.ContactDetails;
import com.aybits.hms.api.func.common.beans.HMSAddress;
import com.aybits.hms.api.func.common.beans.IdentificationParams;
import com.aybits.hms.api.func.login.beans.LoginAttributes;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Employee {


    @JsonProperty("emp_id")
        private String empId;
    @JsonProperty("login_attributes")
        private LoginAttributes loginAttributes;
    @JsonProperty("emp_role")
        private Role role;
    @JsonProperty("contact_details")
        private ContactDetails contactDetails;
    @JsonProperty("address")
        private HMSAddress HMSAddress;
    @JsonProperty("identification_params")
        private IdentificationParams identificationParams;

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public ContactDetails getContactDetails() {
            return contactDetails;
        }

        public void setContactDetails(ContactDetails contactDetails) {
            this.contactDetails = contactDetails;
        }

        public HMSAddress getHMSAddress() {
            return HMSAddress;
        }

        public void setHMSAddress(HMSAddress HMSAddress) {
            this.HMSAddress = HMSAddress;
        }

        public IdentificationParams getIdentificationParams() {
            return identificationParams;
        }

        public void setIdentificationParams(IdentificationParams identificationParams) {
            this.identificationParams = identificationParams;
        }
}
