package com.aybits.hms.func.taxrules.api;

import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSJSONParser;
import com.aybits.hms.func.common.api.HMSAPIResponse;
import com.aybits.hms.func.common.util.HMSAPIServiceConstants;
import com.aybits.hms.func.common.util.HMSJSONConstants;
import com.aybits.hms.func.hotel.api.HotelAPI;
import com.aybits.hms.func.hotel.api.HotelAPIHelper;
import com.aybits.hms.func.hotel.api.HotelAPIValidator;
import com.aybits.hms.func.hotel.beans.Hotel;
import com.aybits.hms.func.hotel.beans.HotelRegistrationData;
import com.aybits.hms.func.taxrules.beans.TaxRule;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class TaxAPI {


    public Boolean addTaxRule(TaxRule taxRule){
        return true;
    }

    public Boolean updateTaxRule(TaxRule taxRule){
        return true;
    }

    public Boolean updateGST(Double gst){
        return true;
    }

    static Logger Log = Logger.getLogger(TaxAPI.class);

}
