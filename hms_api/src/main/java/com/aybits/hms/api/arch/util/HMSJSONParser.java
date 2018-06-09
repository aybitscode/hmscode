package com.aybits.hms.api.arch.util;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
