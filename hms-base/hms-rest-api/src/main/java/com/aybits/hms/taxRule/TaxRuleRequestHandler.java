package com.aybits.hms.taxRule;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.arch.util.HMSJsonRequestComponents;
import com.aybits.hms.common.HMSErrorResponse;
import com.aybits.hms.common.HMSRequestHandler;
import com.aybits.hms.common.HMSResponse;
import com.aybits.hms.common.ValidationResult;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.taxrules.api.TaxRuleAPI;

import spark.Request;
import spark.Response;

	public class TaxRuleRequestHandler implements HMSRequestHandler {
	    static Logger Log = Logger.getLogger(TaxRuleRequestHandler.class);

	    
	    HmsAPI hmsAPI = new TaxRuleAPI();
	    
	    @Override
	    public void validateRequestData(JSONObject dataJSON) {
	      hmsAPI.validate(dataJSON);
	    }


	    public String handleRequest(Request request, Response response) {
	        Log.info("TaxRule request handler invoked");
	        ValidationResult validationResult = validateRequest(request);
	        if (validationResult != null) {
	            return validationResult.getMessage();
	        }
	        HMSJsonRequestComponents components = HMSJSONParser.getHmsJsonRequestComponents(request.body());
	        String operation = components.getOperation();
	        String entity = components.getEntity();
	        String data = components.getData();
	        String action = operation + "/" + entity;
	        String tokenId = components.getTokenId();
	        String errorResponse = null;

	        JSONObject dataJSON = null;

	        ValidationResult result = null;
	        try {
	            dataJSON = new JSONObject(data);
	            validateRequestData(dataJSON);
	        } catch (HMSRuntimeException hrex) {
	            errorResponse = populateHMSErrorResponse(hrex, tokenId);
	        } catch (JSONException jex) {
	            errorResponse = populateGenericErrorResponse(jex, tokenId);
	        }
	        if (errorResponse != null) {
	            return errorResponse;
	        }


	        String message = "";

	        switch (action) {
	            case "fetch-all":
	                message = getAllTaxRules(tokenId,data);
	                break;
	            case "fetch-by-phone":
	                message = getTaxRuleByPhone(tokenId,data);
	                break;
	            case "add/employee":
	                message = addTaxRule(tokenId,data);
	                break;
	            case "update/employee":
	                message = updateTaxRule(tokenId,data);
	                break;
	            /*case "getTaxRuleNameByMobile":
	                message = getTaxRuleNameByMobile(request);
	                break;*/
	            case "fetch-id":
	                message = getTaxRuleId(tokenId,data);
	                break;
	            case "fetch-by-id":
	                message = getTaxRuleById(tokenId,data);
	                break;
	        }
	        return message;
	    }

	    public String populateHMSErrorResponse(HMSRuntimeException he, String tokenId) {
	        Log.error(he.getHmsErrorInfo());
	        HMSErrorResponse hmsErrorResponse = new HMSErrorResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, he.getHmsErrorInfo().getErrorMessage(), he.getHmsErrorInfo().getErrorCode());
	        return HMSJSONParser.convertObjectToJSON(hmsErrorResponse);
	    }

	    @Override
	    public String populateGenericErrorResponse(Exception e, String tokenId) {
	        Log.error(e.getCause());
	        HMSErrorResponse hmsErrorResponse = new HMSErrorResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_SYSTEM_ERROR);
	        return HMSJSONParser.convertObjectToJSON(hmsErrorResponse);
	    }


	    private String addTaxRule(String tokenId, String requestData) {
	        Log.info("requestToken:" + tokenId + ",[Entry]::addTaxRule");
	        HMSResponse hmsResponse = null;
	        try {
	            JSONObject inputJSON = new JSONObject(requestData);
	            String responseStr = hmsAPI.process(inputJSON);
	            hmsResponse = populateHmsResponse(tokenId, responseStr);
	        } catch (HMSRuntimeException e) {
	            hmsResponse = new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
	        } finally {
	            Log.info("requestToken:" + tokenId + ",[Exit]::addTaxRule");
	            return HMSJSONParser.convertObjectToJSON(hmsResponse);
	        }
	    }

	    private String updateTaxRule(String tokenId, String requestData) {
	        Log.info("requestToken:" + tokenId + ",[Entry]::updateTaxRule");
	        HMSResponse hmsResponse = null;
	        try {
	            JSONObject inputJSON = new JSONObject(requestData);
	            String responseStr = hmsAPI.update(inputJSON);
	            hmsResponse = populateHmsResponse(tokenId, responseStr);
	        } catch (HMSRuntimeException e) {
	            hmsResponse = new HMSResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
	        } finally {
	            Log.info("requestToken:" + tokenId + ",[Entry]::updateHotel");
	            return HMSJSONParser.convertObjectToJSON(hmsResponse);
	        }
	    }

	    private String getTaxRuleById(String tokenId, String requestData) {
	        Log.info("in getTaxRuleById");
	        HMSResponse hmsResponse = null;
	        String responseStr = null;
	        try {
	            JSONObject inputJSON = new JSONObject(requestData);
	            responseStr = hmsAPI.fetch(inputJSON);
	            hmsResponse = populateHmsResponse(tokenId, responseStr);
	        } catch (HMSRuntimeException e) {
	            Log.info("requestToken:" + tokenId + ",Exception occured in getTaxRuleById" + e.getMessage());
	            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
	        } finally {
	            Log.info("requestToken:" + tokenId + ",[Exit]::fetchHotel");
	            return HMSJSONParser.convertObjectToJSON(hmsResponse);
	        }
	    }

	    private String getTaxRuleByPhone(String tokenId, String requestData) {
	    	Log.info("requestToken:" + tokenId + ",[Entry]::fetchHotel");
	        HMSResponse hmsResponse = null;
	        String responseStr = null;
	        try {
	            JSONObject inputJSON = new JSONObject(requestData);
	            responseStr = hmsAPI.fetch(inputJSON);
	            hmsResponse = populateHmsResponse(tokenId, responseStr);
	        } catch (HMSRuntimeException e) {
	            Log.info("requestToken:" + tokenId + ",Exception occured in getTaxRuleByPhone" + e.getMessage());
	            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
	        } finally {
	            Log.info("requestToken:" + tokenId + ",[Exit]::fetchHotel");
	            return HMSJSONParser.convertObjectToJSON(hmsResponse);
	        }
	    }

	    private String getTaxRuleId(String tokenId, String requestData) {
	    	Log.info("requestToken:" + tokenId + ",[Entry]::getTaxRuleId");
	        HMSResponse hmsResponse = null;
	        String responseStr = null;
	        try {
	            JSONObject inputJSON = new JSONObject(requestData);
	            responseStr = hmsAPI.fetch(inputJSON);
	            hmsResponse = populateHmsResponse(tokenId, responseStr);
	        } catch (HMSRuntimeException e) {
	            Log.info("requestToken:" + tokenId + ",Exception occured in getTaxRuleId" + e.getMessage());
	            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
	        } finally {
	            Log.info("requestToken:" + tokenId + ",[Exit]::getTaxRuleId");
	            return HMSJSONParser.convertObjectToJSON(hmsResponse);
	        }
	    }


	    private String getAllTaxRules(String tokenId, String requestData) {
	    	Log.info("requestToken:" + tokenId + ",[Entry]::fetchAllHotels");
	        HMSResponse hmsResponse = null;
	        String responseStr = null;
	        try {
	            JSONObject inputJSON = new JSONObject(requestData);
	            responseStr = hmsAPI.fetchAll(inputJSON);
	            hmsResponse = populateHmsResponse(tokenId, responseStr);
	        } catch (HMSRuntimeException e) {
	            hmsResponse = getHmsResponse(tokenId, HMSAPIServiceConstants.HMS_RESPONSE_FAILURE, e.getMessage(), HMSAPIServiceConstants.HMS_FAILURE_RESPONSE_DATA);
	        } finally {
	            Log.info("requestToken:" + tokenId + ",[Exit]::fetchAllHotels");
	            return HMSJSONParser.convertObjectToJSON(hmsResponse);
	        }
	    }

	    private HMSResponse getHmsResponse(Object responseData, boolean result) {
	        HMSResponse response = null;
	       /* if (result) {
	            response = new HMSAPIResponse(200, "SUCCESS", responseData, null, null, "SUCCESS");
	        } else {
	            response = new HMSAPIResponse(200, "FAILED", responseData, null, null, "FAILED");
	        }*/
	        return response;
	    }
	

	
	
}
