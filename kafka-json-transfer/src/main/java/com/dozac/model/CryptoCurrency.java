package com.dozac.model;
import lombok.*;


@Data
public class CryptoCurrency {

    private String currencyName;
    private double price;
    private double marketCap;

    public CryptoCurrency(String currencyName, double price, double marketCap) {
        super();
        this.currencyName = currencyName;
        this.price = price;
        this.marketCap = marketCap;
    }

    public CryptoCurrency() {
        super();
    }
}
