package com.aybits.hms.room;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.common.HMSRequest;
import com.aybits.hms.common.HMSRequestProcessor;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.func.room.api.RoomAPI;
import com.aybits.hms.func.room.beans.Room;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoomRequestProcessor implements HMSRequestProcessor {
    static Logger Log = Logger.getLogger(RoomRequestProcessor.class);


    @Autowired
    RoomAPI roomAPI;

    @Override
    public void validateRequestData(JSONObject dataJSON) throws HMSRuntimeException {

    }

    @Override
    public HMSResponse processRequest(HMSRequest request) {

        Log.info("Room request handler invoked");


        String operation = request.getOperation();
        String entity = request.getEntity();
        String data = request.getData();
        String action = operation + "/" + entity;
        String tokenId = request.getTokenId();
        HMSResponse hmsResponse = null;

        JSONObject dataJSON = null;

        try {
            dataJSON = new JSONObject(data);
            validateRequestData(dataJSON);
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex, tokenId);
        } catch (Exception ex) {
            hmsResponse = populateGenericErrorResponse(ex, tokenId);
        } finally {
            if (hmsResponse != null) {
                return hmsResponse;
            }
        }
        switch (action) {
            case "fetch-rooms-by-hotel-id":
                hmsResponse = fetchRoomsByHotel(request);
                break;
            case "fetch-com.aybits.hms.room-details":
                hmsResponse = fetchRoomDetails(request);
                break;
        }
        return hmsResponse;
    }


    private HMSResponse fetchRoomsByHotel(HMSRequest request) {
        Log.info("in fetchRoomsByHotel");
        String jsonString = request.getData();
        String tokenId = request.getTokenId();
        RoomAPI roomAPI = new RoomAPI();
        try {
            Room room = (Room) HMSJSONParser.convertJSONToObject(jsonString, Room.class);
            List<Room> result = roomAPI.fetchRoomsByHotel(room.getHotelId());
            return populateHMSResponse(tokenId, "");
        } catch (HMSRuntimeException hrex) {
            return populateHMSErrorResponse(hrex, tokenId);
        } catch (Exception ex) {
            return populateGenericErrorResponse(ex, tokenId);
        }
    }

    private HMSResponse fetchRoomDetails(HMSRequest request) {
        Log.info("in fetchRoomDetails");
        String jsonString = request.getData();
        String tokenId = request.getTokenId();
        RoomAPI roomAPI = new RoomAPI();
        try {
            Room room = (Room) HMSJSONParser.convertJSONToObject(jsonString, Room.class);
            Room result = roomAPI.fetchRoomDetails(room.getRoomId());
            return populateHMSResponse(tokenId,"");
        } catch(HMSRuntimeException hrex){
            return populateHMSErrorResponse(hrex,tokenId);
        }
        catch (Exception ex) {
            return populateGenericErrorResponse(ex,tokenId);
        }
    }
}
