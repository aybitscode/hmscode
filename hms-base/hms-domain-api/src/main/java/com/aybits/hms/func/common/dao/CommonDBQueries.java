package com.aybits.hms.func.common.dao;

public class CommonDBQueries {




    protected static final String getNextPrimaryKeyQuery(String primaryColumn,String table){

        String FETCH_NEXT_PRIMARY_KEY  = "(select count("+(primaryColumn)+")+1 as NEXT_ID from "+table+")";

        return FETCH_NEXT_PRIMARY_KEY;

    }
}
