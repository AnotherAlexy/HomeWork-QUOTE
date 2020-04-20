package com.testproject.stockservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "quotes_energy_level")
public class QuoteElvl {
    @Id
    @Column(name = "isin", nullable = false)
    private String isin;

    private float elvl;

    public String getIsin() {
        return isin;
    }

    public QuoteElvl setIsin(String isin) {
        this.isin = isin;
        return this;
    }

    public float getElvl() {
        return elvl;
    }

    public QuoteElvl setElvl(float elvl) {
        this.elvl = elvl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuoteElvl quoteElvl = (QuoteElvl) o;
        return Float.compare(quoteElvl.elvl, elvl) == 0 &&
                isin.equals(quoteElvl.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isin, elvl);
    }

    public QuoteElvl(String isin) {
        this.isin = isin;
    }

    public QuoteElvl(String isin, float elvl) {
        this.isin = isin;
        this.elvl = elvl;
    }

    public QuoteElvl() {
    }
}
