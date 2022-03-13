package com.finance.stockdata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;

@Getter
@With
@AllArgsConstructor
public class StockWrapper {

    private final Stock stock;
    private final LocalDateTime lastAccessed;

    public StockWrapper(Stock stock) {
        this.stock = stock;
        this.lastAccessed = LocalDateTime.now();
    }

}
