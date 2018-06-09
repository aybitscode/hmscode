package com.aybits.hms.api.func.employee.beans;

import com.aybits.hms.api.func.common.beans.ContactDetails;
<<<<<<< HEAD
import com.aybits.hms.api.func.common.beans.UserAddress;
import com.aybits.hms.api.func.common.beans.UserIdentifier;

public class Employee {

        private String empId;
        private String login;
        private String password;
        private Role role;
        private ContactDetails contactDetails;
        private UserAddress userAddress;
        private UserIdentifier userIdentifier;
=======
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
>>>>>>> adil_develop

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

<<<<<<< HEAD
        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

=======
>>>>>>> adil_develop
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

<<<<<<< HEAD
        public UserAddress getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(UserAddress userAddress) {
            this.userAddress = userAddress;
        }

        public UserIdentifier getUserIdentifier() {
            return userIdentifier;
        }

        public void setUserIdentifier(UserIdentifier userIdentifier) {
            this.userIdentifier = userIdentifier;
=======
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
>>>>>>> adil_develop
        }
}
