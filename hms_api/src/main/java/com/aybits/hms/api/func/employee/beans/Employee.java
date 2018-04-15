package com.aybits.hms.api.func.employee.beans;

import com.aybits.hms.api.func.common.beans.ContactDetails;
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

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

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
        }
}
