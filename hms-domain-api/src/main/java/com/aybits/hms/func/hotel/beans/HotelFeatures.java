package com.aybits.hms.func.hotel.beans;

import com.aybits.hms.func.common.beans.HMSAddress;
import com.fasterxml.jackson.annotation.JsonProperty;


public class HotelFeatures {

        @JsonProperty("hotel_name")
        private String hotelName;
        @JsonProperty("hotel_address")
        private HMSAddress hotelAddress;
        @JsonProperty("room_count")
        private String roomCount;
        @JsonProperty("room_door_no_format")
        private String roomDoorNoFormat;
        @JsonProperty("employee_count")
        private String employeeCount;
        @JsonProperty("bed_count")
        private String totalBeds;
        @JsonProperty("hotel_rating")
        private String hotelRating;
        @JsonProperty("hotel_logo")
        private Byte[] hotelLogo;



        public String getHotelName() {
                return hotelName;
        }

        public void setHotelName(String hotelName) {
                this.hotelName = hotelName;
        }

        public HMSAddress getHotelAddress() {
                return hotelAddress;
        }

        public void setHotelAddress(HMSAddress hotelAddress) {
                this.hotelAddress = hotelAddress;
        }

        public String getRoomCount() {
                return roomCount;
        }

        public void setRoomCount(String roomCount) {
                this.roomCount = roomCount;
        }

        public String getRoomDoorNoFormat() {
                return roomDoorNoFormat;
        }

        public void setRoomDoorNoFormat(String roomDoorNoFormat) {
                this.roomDoorNoFormat = roomDoorNoFormat;
        }

        public String getEmployeeCount() {
                return employeeCount;
        }

        public void setEmployeeCount(String employeeCount) {
                this.employeeCount = employeeCount;
        }

        public String getTotalBeds() {
                return totalBeds;
        }

        public void setTotalBeds(String totalBeds) {
                this.totalBeds = totalBeds;
        }
}
