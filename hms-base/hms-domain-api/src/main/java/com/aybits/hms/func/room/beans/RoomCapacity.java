package com.aybits.hms.func.room.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RoomCapacity {


    SINGLE_BED(1),
    DOUBLE_BED(2),
    EXTRA_SINGLE_BED(3),
    EXTRA_DOUBLE_BED(4);

    @JsonProperty("room_capacity")
    private final Integer roomCapacity;

    RoomCapacity(Integer roomCapacity){
        this.roomCapacity=roomCapacity;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public String getRoomCapacityAsString() {
        return String.valueOf(roomCapacity);
    }

    public static RoomCapacity convertIntToRoomCapacity(int iRoomCapacity) {
        for (RoomCapacity roomCapacity : RoomCapacity.values()) {
            if (roomCapacity.getRoomCapacity() == iRoomCapacity) {
                return roomCapacity;
            }
        }
        return null;
    }

    public static RoomCapacity convertStringToRoomCapacity(String inputRoomCapacity) {
        for (RoomCapacity roomCapacity : RoomCapacity.values()) {
            if (roomCapacity.getRoomCapacityAsString().equals(inputRoomCapacity)) {
                return roomCapacity;
            }
        }
        return null;
    }

    public static int convertRoomCapacityToInt(RoomCapacity inputRoomCapacity) {
        for (RoomCapacity roomCapacity : RoomCapacity.values()) {
            if (roomCapacity.getRoomCapacity() == inputRoomCapacity.getRoomCapacity()) {
                return roomCapacity.getRoomCapacity();
            }
        }
        return -1;
    }

    public static String convertRoomCapacityToString(RoomCapacity inputRoomCapacity) {
        for (RoomCapacity roomCapacity : RoomCapacity.values()) {
            if (roomCapacity.getRoomCapacity() == inputRoomCapacity.getRoomCapacity()) {
                return roomCapacity.getRoomCapacityAsString();
            }
        }
        return null;
    }

}
