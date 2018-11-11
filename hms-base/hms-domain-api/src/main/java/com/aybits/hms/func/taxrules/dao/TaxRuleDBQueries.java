package com.aybits.hms.func.taxrules.dao;

public class TaxRuleDBQueries {

	 /**
     * DML QUERIES
     */
    protected static final String FETCH_TAXRULE_BY_TAXRULE_ID = "select * from hms_taxrule where hotel_id = ? and taxrule_id = ?";
    protected static final String FETCH_TAXRULE_BY_TAXRULE_NAME = "select * from hms_taxrule where hotel_id = ? and taxrule_name = ?";
    protected static final String FETCH_ALL_TAXRULES = "select * from hms_taxrule where hotel_id = ?";

    /**
     * DDL QUERIES
     */
    protected static final String INSERT_NEW_TAXRULE = "INSERT INTO hms_taxrule(hotel_id,taxrule_id,taxrule_name,tax_lower_bound,tax_upper_bound,tax_cgst,tax_sgst,tax_gst," +
 "tax_category,taxrule_description,)" +
            "VALUES(?,?,?,?,?,?,?,?,?,?)";

    protected static final String UPDATE_EXISTING_TAXRULE = "UPDATE hms_taxrule SET hotel_id = ?,taxrule_name = ?,taxrule_description = ?,"+
            " tax_cgst = ?,tax_sgst = ?,tax_gst = ?,tax_upper_bound = ?,"+
            " tax_lower_bound = ? WHERE taxrule_id = ? and hotel_id = ?";






}
