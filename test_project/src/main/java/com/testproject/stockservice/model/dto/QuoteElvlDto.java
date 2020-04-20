package com.testproject.stockservice.model.dto;

public class QuoteElvlDto {
    private String isin;

    private float elvl;

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public float getElvl() {
        return elvl;
    }

    public void setElvl(float elvl) {
        this.elvl = elvl;
    }
}
