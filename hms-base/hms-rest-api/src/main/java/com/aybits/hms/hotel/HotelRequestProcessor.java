package com.aybits.hms.hotel;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.HMSRequest;
import com.aybits.hms.common.HMSRequestProcessor;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.func.common.api.HMSAPI;
import com.aybits.hms.func.hotel.api.HotelAPI;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class HotelRequestProcessor implements HMSRequestProcessor {
    static Logger Log = Logger.getLogger(HotelRequestProcessor.class);

    @Autowired
    HotelAPI hotelAPI;


    /**
     *
     * @param request
     * @return
     */
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
                case "fetch-all/hotel":
                    hmsResponse = fetchAllHotels(tokenId, data);
                    break;
                case "fetch/hotel":
                    hmsResponse = fetchHotel(tokenId, data);
                    break;
                case "add/hotel":
                    hmsResponse = addHotel(tokenId, data);
                    break;
                case "update/hotel":
                    hmsResponse = updateHotel(tokenId, data);
                    break;
                case "disable/hotel":
                    hmsResponse = disableHotel(tokenId, data);
                    break;
            }
        }catch(Exception ex){
            hmsResponse = populateGenericErrorResponse(ex,tokenId);
        } finally{
            return hmsResponse;
        }
    }

    @Override
    public void validateRequestData(JSONObject data) throws HMSRuntimeException {
        hotelAPI.validate(data);
    }


    private HMSResponse addHotel(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::addHotel");
        HMSResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hotelAPI.process(inputJSON);
            hmsResponse = populateHMSResponse(tokenId, responseStr);
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        } finally{
            Log.info("requestToken:" + tokenId + ",[Exit]::addHotel");
            return hmsResponse;
        }
    }


    /**
     * @param tokenId
     * @param requestData
     * @return
     */
    private HMSResponse updateHotel(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
        HMSResponse hmsResponse = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            String responseStr = hotelAPI.update(inputJSON);
            hmsResponse = populateHMSResponse(tokenId, responseStr);
        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        }  finally{
            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
            return hmsResponse;
        }
    }


    /**
     * @param tokenId
     * @param requestData
     * @return JSONObject converted to String
     */

    private HMSResponse fetchHotel(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchHotel");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hotelAPI.fetch(inputJSON);
            hmsResponse = populateHMSResponse(tokenId, responseStr);
        } catch (HMSRuntimeException hrex) {
            Log.info("requestToken:" + tokenId + ",Exception occured in fetchHotel" + hrex.getMessage());
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::fetchHotel");
            return hmsResponse;
        }
    }


    /**
     * @param tokenId
     * @param requestData
     * @return JSONObject converted to String
     */
    private HMSResponse fetchAllHotels(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::fetchAllHotels");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hotelAPI.fetchAll(inputJSON);
            hmsResponse = populateHMSResponse(tokenId, responseStr);

        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::fetchAllHotels");
            return hmsResponse;
        }
    }


    /**
     * @param tokenId
     * @param requestData
     * @return JSONObject converted to String
     */
    private HMSResponse disableHotel(String tokenId, String requestData) {
        Log.info("requestToken:" + tokenId + ",[Entry]::disableHotel");
        HMSResponse hmsResponse = null;
        String responseStr = null;
        try {
            JSONObject inputJSON = new JSONObject(requestData);
            responseStr = hotelAPI.disable(inputJSON);
            hmsResponse = populateHMSResponse(tokenId, responseStr);

        } catch (HMSRuntimeException hrex) {
            hmsResponse = populateHMSErrorResponse(hrex,tokenId);
        } finally {
            Log.info("requestToken:" + tokenId + ",[Exit]::disableHotel");
            return hmsResponse;
        }
    }
}
