package com.aybits.hms.func.hotel.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.ContactDetails;
import com.aybits.hms.func.common.beans.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("hotel_attributes")
public class HotelAttributes {

        @JsonProperty("hotel_name")
        private String hotelName;
        @JsonProperty("hotel_address")
        private Address hotelAddress;
        @JsonProperty("hotel_contact_details")
        private ContactDetails hotelContactDetails;
        @JsonProperty("hotel_room_count")
        private Integer roomCount;
        @JsonProperty("hotel_room_doorno_format")
        private String roomDoorNoFormat;
        @JsonProperty("hotel_employee_count")
        private Integer employeeCount;
        @JsonProperty("hotel_bed_count")
        private Integer totalBeds;
        @JsonProperty("hotel_rating")
        private String hotelRating;
        @JsonProperty("hotel_logo")
        private String hotelLogo;


        public HotelAttributes(){

        }

        public HotelAttributes(String hotelName,
                               String hotelRating,
                               String hotelLogo,
                               String roomDoorNoFormat,
                               Integer roomCount,
                               Integer employeeCount,
                               Integer totalBeds,
                               Address hotelAddress,
                               ContactDetails hotelContactDetails) {
                this.hotelName = hotelName;
                this.hotelAddress = hotelAddress;
                this.roomCount = roomCount;
                this.roomDoorNoFormat = roomDoorNoFormat;
                this.employeeCount = employeeCount;
                this.totalBeds = totalBeds;
                this.hotelRating = hotelRating;
                this.hotelLogo = hotelLogo;
                this.hotelContactDetails = hotelContactDetails;
        }

        public String getHotelName() {
                return hotelName;
        }

        public void setHotelName(String hotelName) {
                this.hotelName = hotelName;
        }

        public Address getHotelAddress() {
                return hotelAddress;
        }

        public void setHotelAddress(Address hotelAddress) {
                this.hotelAddress = hotelAddress;
        }

        public Integer getRoomCount() {
                return roomCount;
        }

        public void setRoomCount(Integer roomCount) {
                this.roomCount = roomCount;
        }

        public String getRoomDoorNoFormat() {
                return roomDoorNoFormat;
        }

        public void setRoomDoorNoFormat(String roomDoorNoFormat) {
                this.roomDoorNoFormat = roomDoorNoFormat;
        }

        public Integer getEmployeeCount() {
                return employeeCount;
        }

        public void setEmployeeCount(Integer employeeCount) {
                this.employeeCount = employeeCount;
        }

        public Integer getTotalBeds() {
                return totalBeds;
        }

        public void setTotalBeds(Integer totalBeds) {
                this.totalBeds = totalBeds;
        }

        public String getHotelRating() {
                return hotelRating;
        }

        public void setHotelRating(String hotelRating) {
                this.hotelRating = hotelRating;
        }

        public String getHotelLogo() {
                return hotelLogo;
        }

        public void setHotelLogo(String hotelLogo) {
                this.hotelLogo = hotelLogo;
        }

        public ContactDetails getHotelContactDetails() {
                return hotelContactDetails;
        }

        public void setHotelContactDetails(ContactDetails hotelContactDetails) {
                this.hotelContactDetails = hotelContactDetails;
        }

        @Override
        public String toString(){
                return HMSJSONParser.convertObjectToJSON((Object)this);
        }
}
