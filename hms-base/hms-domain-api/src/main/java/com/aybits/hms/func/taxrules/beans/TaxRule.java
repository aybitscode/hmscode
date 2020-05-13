package com.aybits.hms.func.taxrules.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxRule {

    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("tax_rule_id")
    private String taxRuleId;
    @JsonProperty("tax_rule_name")
    private String taxRuleName;
    @JsonProperty("tax_lower_bound")
    private Double lowerBound;
    @JsonProperty("tax_upper_bound")
    private Double upperBound;
    @JsonProperty("cgst")
    private Double cgst;
    @JsonProperty("sgst")
    private Double sgst;
    @JsonProperty("gst")
    private Double gst;
    @JsonProperty("tax_category")
    private String taxCategory;
    @JsonProperty("tax_rule_description")
    private String taxRuleDescription;

    public TaxRule(){

    }

    public TaxRule(String hotelId, String taxRuleId, String taxRuleName, Double lowerBound, Double upperBound, Double cgst, Double sgst, Double gst, String taxCategory, String taxRuleDescription) {
        this.hotelId = hotelId;
        this.taxRuleId = taxRuleId;
        this.taxRuleName = taxRuleName;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.cgst = cgst;
        this.sgst = sgst;
        this.gst = gst;
        this.taxCategory = taxCategory;
        this.taxRuleDescription = taxRuleDescription;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getTaxRuleId() {
        return taxRuleId;
    }

    public void setTaxRuleId(String taxRuleId) {
        this.taxRuleId = taxRuleId;
    }

    public String getTaxRuleName() {
        return taxRuleName;
    }

    public void setTaxRuleName(String taxRuleName) {
        this.taxRuleName = taxRuleName;
    }

    public Double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    public Double getCgst() {
        return cgst;
    }

    public void setCgst(Double cgst) {
        this.cgst = cgst;
    }

    public Double getSgst() {
        return sgst;
    }

    public void setSgst(Double sgst) {
        this.sgst = sgst;
    }

    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }

    public String getTaxRuleDescription() {
        return taxRuleDescription;
    }

    public void setTaxRuleDescription(String taxRuleDescription) {
        this.taxRuleDescription = taxRuleDescription;
    }
}
