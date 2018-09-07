package com.aybits.hms.func.common.dao;

public class CommonDBQueries {




    protected static final String getNextPrimaryKeyQuerySQL(String primaryColumn,String table){

        String FETCH_NEXT_PRIMARY_KEY  = "(select count("+(primaryColumn)+")+1 as NEXT_ID from "+table+")";

        return FETCH_NEXT_PRIMARY_KEY;

    }


    protected static final String getNextPrimaryKeyQuerySQL(String primaryKeyColumn1,String primaryKeyColumn2,String table){

        String FETCH_NEXT_PRIMARY_KEY  = "(select count(distinct "+primaryKeyColumn1+", "+primaryKeyColumn2+")+1 as NEXT_ID from "+table+")";

        return FETCH_NEXT_PRIMARY_KEY;

    }


}
