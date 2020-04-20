package com.testproject.stockservice.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "quotes_history")
public class Quote {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Hello, PostgreSQL
    private Long id;

    @Column(name = "DATE_VALUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String isin;

    private float bid;

    private float ask;

    public String getIsin() {
        return isin;
    }

    public Quote setIsin(String isin) {
        this.isin = isin;
        return this;
    }

    public float getBid() {
        return bid;
    }

    public Quote setBid(float bid) {
        this.bid = bid;
        return this;
    }

    public float getAsk() {
        return ask;
    }

    public Quote setAsk(float ask) {
        this.ask = ask;
        return this;
    }

    public Long getId() {
        return id;
    }

}
