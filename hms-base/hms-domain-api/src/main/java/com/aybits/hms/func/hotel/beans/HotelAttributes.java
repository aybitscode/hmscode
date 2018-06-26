package com.aybits.hms.func.hotel.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.beans.HMSAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("hotel_attributes")
public class HotelAttributes {

        @JsonProperty("hotel_name")
        private String hotelName;
        @JsonProperty("hotel_address")
        private HMSAddress hotelAddress;
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



        public HotelAttributes(String hotelName,
                               String hotelRating,
                               String hotelLogo,
                               String roomDoorNoFormat,
                               Integer roomCount,
                               Integer employeeCount,
                               Integer totalBeds,
                               HMSAddress hotelAddress) {
                this.hotelName = hotelName;
                this.hotelAddress = hotelAddress;
                this.roomCount = roomCount;
                this.roomDoorNoFormat = roomDoorNoFormat;
                this.employeeCount = employeeCount;
                this.totalBeds = totalBeds;
                this.hotelRating = hotelRating;
                this.hotelLogo = hotelLogo;
        }

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

        @Override
        public String toString(){
                return HMSJSONParser.convertObjectToJSON((Object)this);
        }
}
