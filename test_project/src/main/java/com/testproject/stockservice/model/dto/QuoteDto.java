package com.testproject.stockservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testproject.stockservice.model.validator.BidShouldBeLessThanAskConstraint;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//@Validated
@BidShouldBeLessThanAskConstraint
public class QuoteDto {
    @NotEmpty
    @Size(min = 12, max = 12, message = "isin should be 12 characters long")
    @JsonProperty("isin")
    private String isin;

    private Float bid;

    private Float ask;

    public String getIsin() {
        return isin;
    }

    public QuoteDto setIsin(String isin) {
        this.isin = isin;
        return this;
    }

    public Float getBid() {
        return bid;
    }

    public QuoteDto setBid(float bid) {
        this.bid = bid;
        return this;
    }

    public Float getAsk() {
        return ask;
    }

    public QuoteDto setAsk(float ask) {
        this.ask = ask;
        return this;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
