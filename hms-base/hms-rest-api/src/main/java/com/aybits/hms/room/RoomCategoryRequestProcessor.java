package com.aybits.hms.room;

import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.*;
import com.aybits.hms.func.common.api.HMSAPI;
import com.aybits.hms.func.room.api.RoomCategoryAPI;
import com.aybits.hms.func.room.beans.RoomCategory;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import spark.Request;
import spark.Response;

public class RoomCategoryRequestProcessor implements HMSRequestProcessor {

    static Logger Log = Logger.getLogger(RoomCategoryRequestProcessor.class);

    @Autowired
    RoomCategoryAPI roomCategoryAPI;



    @Override
    public void validateRequestData(JSONObject dataJson) throws HMSRuntimeException {
        roomCategoryAPI.validate(dataJson);
    }

    @Override
    public HMSResponse processRequest(HMSRequest request) {
        Log.info("Hotel request handler invoked");

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
        }catch(Exception ex){
            hmsResponse = populateGenericErrorResponse(ex,tokenId);
        }finally{
            if (hmsResponse != null) {
                return hmsResponse;
            }
        }

        try {
            switch (action) {
                case "fetch/all/room-categories":
                    hmsResponse = fetchAllRoomCategories(request);
                    break;
                case "fetch/room-category":
                    hmsResponse = fetchRoomCategoryById(request);
                    break;
                case "add/room-category":
                    hmsResponse = setupRoomCategory(request);
                    break;
                case "update/room-category":
                    hmsResponse = updateRoomCategory(request);
                    break;

            }
        }catch(Exception ex){
            hmsResponse = populateGenericErrorResponse(ex,tokenId);
        } finally{
            return hmsResponse;
        }
    }





    private HMSResponse fetchAllRoomCategories(HMSRequest request){
        return null;
    }

    private HMSResponse fetchRoomCategoryById(HMSRequest request){
        return null;
    }

    private HMSResponse setupRoomCategory(HMSRequest request){
        Log.info("[Enter]::setupRoomCateogry");
        Boolean isRoomCategoryAdded = false;
        HMSResponse response = null;
        String tokenId = request.getTokenId();
        try {
            JSONObject jsonObject = new JSONObject(request.getData());
            JSONObject roomCategoryJSON = jsonObject.getJSONObject("room_category");
            RoomCategory roomCategory = (RoomCategory) HMSJSONParser.convertJSONToObject(roomCategoryJSON.toString(), RoomCategory.class);

            isRoomCategoryAdded = roomCategoryAPI.addRoomCategory(roomCategory);

        } catch (HMSRuntimeException hrex) {
            response = populateHMSErrorResponse(hrex,tokenId);
        }finally{
            Log.info("[Exit]::setupRoomCateogry");
            return response;
        }
    }

    private HMSResponse updateRoomCategory(HMSRequest hmsRequest){
        return null;
    }


}
