package com.aybits.hms.arch.util;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class HMSJSONParser {



    private static final ObjectMapper objMapper = new ObjectMapper();

    private HMSJSONParser(){ }


    public static String convertObjectToJSON(Object obj) {

        String jsonString = null;
        try
        {
            jsonString = objMapper.writeValueAsString(obj);

            //Use pretty print for printing the output
            //String beutifulJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            //System.out.println(beutifulJson);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public static HMSJsonRequestComponents getHmsJsonRequestComponents(String inputJsonString) {
        JsonObject jobj = new Gson().fromJson(inputJsonString, JsonObject.class);

        if (jobj.get("data") == null || jobj.get("entity") == null
                || jobj.get("operation") == null || jobj.get("request_id") == null) {
            return null;
        }

        HMSJsonRequestComponents components = new HMSJsonRequestComponents();
        components.setData(jobj.get("data").toString());
        components.setEntity(jobj.get("entity").toString());
        components.setOperation(jobj.get("operation").toString());
        components.setRequestId(jobj.get("request_id").toString());

        return components;
    }

    
    public static Object convertJSONToObject(String jsonString,Class clazz){
        Object obj = null;
        try
        {
            obj = objMapper.readValue(jsonString,clazz);

        }
        catch (JsonGenerationException e)
        {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
