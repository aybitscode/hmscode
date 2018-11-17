package com.aybits.hms.func.taxrules.api;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.api.HmsAPI;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import com.aybits.hms.func.taxrules.beans.TaxRule;

public class TaxRuleAPI implements HmsAPI {
	
	static Logger Log = Logger.getLogger(TaxRuleAPI.class);
	TaxRuleAPIValidator taxRuleAPIValidator = new TaxRuleAPIValidator();
	TaxRuleAPIHelper taxRuleAPIHelper= new TaxRuleAPIHelper();
	
	public String process(JSONObject data) throws HMSRuntimeException{
        Log.info("[Entry]::taxRuleAPI.process");
        String status = null;
        String message = null;
        String taxRuleId = null;
        JSONObject responseData = new JSONObject();

        TaxRule taxRule = null;
        try {
            taxRule = (TaxRule) HMSJSONParser.convertJSONToObject(data.getJSONObject(HMSJSONConstants.TAXRULE).toString(), TaxRule.class);
            taxRuleId = taxRuleAPIHelper.addTaxRule(taxRule);
            responseData.put(HMSJSONConstants.TAXRULE_ID,data);
            status = HMSAPIServiceConstants.HMS_RESPONSE_SUCCESS;
            message = "TaxRule with ["+taxRuleId+"] created successfully";
            Log.info(message);
        }catch (HMSRuntimeException e) {
            throw e;
        }catch(Exception e){
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.TAX_RULE_FAILED,"TaxRule setup failed due to :"+e.getMessage()));
        }finally{
            Log.info("[Exit]::taxRuleAPI.process");
            return createHMSAPIResponse(status,message,responseData);
        }
    }
	
	
	
	
	
	
    /*public Boolean addTaxRule(TaxRule taxRule){
        return true;
    }

    public Boolean updateTaxRule(TaxRule taxRule){
        return true;
    }

    public Boolean updateGST(Double gst){
        return true;
    }
*/
    
    @Override
    public void validate(JSONObject object) throws HMSRuntimeException {
    	
    	TaxRule taxRule = null;
         try {
             taxRule = (TaxRule) HMSJSONParser.convertJSONToObject(object.getJSONObject(HMSJSONConstants.TAXRULE).toString(), TaxRule.class);
         }catch(Exception e){
             throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.INVALID_EMPLOYEE_DETAILS,"Invalid TaxRule details provided"));
         }
         taxRuleAPIValidator.validateTaxRule(taxRule);

    }






	@Override
	public Object init(JSONObject object) throws HMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public String fetch(JSONObject json) throws HMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public String fetchAll(JSONObject json) throws HMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public String update(JSONObject json) throws HMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public String disable(JSONObject json) throws HMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public String delete(JSONObject json) throws HMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

    

}
