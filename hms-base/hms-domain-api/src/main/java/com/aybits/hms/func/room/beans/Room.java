package com.aybits.hms.func.room.beans;

import com.aybits.hms.arch.util.HMSJSONParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("room")
public class Room {

    @JsonProperty("hotel_id")
        private String hotelId;
    @JsonProperty("room_id")
        private String roomId;
    @JsonProperty("room_door_number")
        private String roomDoorNumber;
    @JsonProperty("room_category")
        private RoomCategory roomCategory;
    @JsonProperty("room_status")
        private RoomStatus roomStatus;

    public Room() {
        super();
    }

    public Room(String hotelId,
                String roomId,
                String roomDoorNumber,
                RoomCategory roomCategory,
                RoomStatus roomStatus) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.roomDoorNumber = roomDoorNumber;
        this.roomCategory = roomCategory;
        this.roomStatus = roomStatus;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomDoorNumber() {
        return roomDoorNumber;
    }

    public void setRoomDoorNumber(String roomDoorNumber) {
        this.roomDoorNumber = roomDoorNumber;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public String toString(){
        return HMSJSONParser.convertObjectToJSON((Object)this);
    }

}
