package com.aybits.hms.func.amenity.api;

import com.aybits.hms.func.amenity.cache.AmenityCache;
import com.aybits.hms.func.amenity.dao.AmenityDAO;
import com.aybits.hms.func.common.api.HMSAPIProviderImpl;
import org.apache.log4j.Logger;

public class AmenityAPI extends HMSAPIProviderImpl {

    static Logger log = Logger.getLogger(AmenityAPI.class);
    AmenityCache amenityCache = new AmenityCache();
    AmenityDAO amenityDAO = new AmenityDAO();




}
