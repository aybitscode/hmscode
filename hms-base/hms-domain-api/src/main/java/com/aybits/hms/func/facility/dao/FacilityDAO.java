package com.aybits.hms.func.facility.dao;

import com.aybits.hms.func.facility.beans.Facility;

import java.util.List;

public class FacilityDAO {


    public List<Facility> fetchFacilitiesByType(String facilityType){
        return null;
    }

    public List<Facility> fetchFacilitiesByChargeApplicable(){
        return null;
    }

    public List<Facility> fetchAllFacilities(){
        return null;
    }

    public Boolean addFacility(Facility facility){
        return true;
    }

    public Boolean updateFacility(Facility facility){
        return true;
    }

    public Boolean deleteFacility(Facility facility){
        return true;
    }

    public Boolean updateFacilityStatus(String facilityId,Integer status){
        return true;
    }

}
