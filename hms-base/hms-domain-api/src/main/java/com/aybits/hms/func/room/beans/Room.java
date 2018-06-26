package com.aybits.hms.func.room.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("hms_room")
public class Room {

    @JsonProperty("hotel_id")
        private Integer hotelId;
    @JsonProperty("room_id")
        private Integer roomId;
    @JsonProperty("room_door_number")
        private Integer roomDoorNumber;
    @JsonProperty("room_category")
        private RoomCategory roomCategory;




    public Room() {
        super();
    }

    public Room(Integer hotelId, Integer roomId, Integer roomDoorNumber, RoomCategory roomCategory) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.roomDoorNumber = roomDoorNumber;
        this.roomCategory = roomCategory;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomDoorNumber() {
        return roomDoorNumber;
    }

    public void setRoomDoorNumber(Integer roomDoorNumber) {
        this.roomDoorNumber = roomDoorNumber;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }

}
