package com.aybits.hms.feature;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.common.HMSRequest;
import com.aybits.hms.common.HMSRequestProcessor;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class FeatureRequestProcessor implements HMSRequestProcessor {

    static Logger Log = Logger.getLogger(FeatureRequestProcessor.class);
    HMSRequestProcessor hmsRequestProcessor = null;
    private String[] entities = {HMSJSONConstants.FACILITIES,HMSJSONConstants.AMENITIES,HMSJSONConstants.SERVICES};



    @Override
    public HMSResponse processRequest(HMSRequest request) {


        Log.info("Feature request handler invoked");


        String data = request.getData();
        String tokenId = request.getTokenId();
        HMSResponse hmsResponse = null;

        try {
           JSONObject dataJSON = new JSONObject(data);
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



        for(String entity:entities) {
            try {
                switch (entity) {
                    case HMSJSONConstants.FACILITIES:
                        hmsResponse = setupFacilities(request);
                        break;
                    case HMSJSONConstants.AMENITIES:
                        hmsResponse = setupAmenities(request);
                        break;
                    case HMSJSONConstants.SERVICES:
                        hmsResponse = setupServices(request);
                        break;
                }
            } catch (HMSRuntimeException hrex) {
                hmsResponse = populateHMSErrorResponse(hrex, tokenId);
            } catch(Exception ex){
                hmsResponse = populateGenericErrorResponse(ex,tokenId);
            }
        }

        return hmsResponse;
    }






    @Override
    public void validateRequestData(JSONObject data) throws HMSRuntimeException{
    }

    private HMSResponse setupFacilities(HMSRequest request){
        hmsRequestProcessor = new FacilityRequestProcessor();
        return hmsRequestProcessor.processRequest(request);
    }

    private HMSResponse setupAmenities(HMSRequest request){
        hmsRequestProcessor = new AmenityRequestProcessor();
        return hmsRequestProcessor.processRequest(request);
    }

    private HMSResponse setupServices(HMSRequest request){
        hmsRequestProcessor = new ServiceRequestProcessor();
        return hmsRequestProcessor.processRequest(request);
    }



}
