package com.finance.stockdata.service;

import com.finance.stockdata.model.StockWrapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class StockService {

    private static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(StockService.class);
    }

    public StockWrapper findStock(final String ticker) {
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (Exception e) {
            logger.error("Error");
        }
        return null;
    }

    public BigDecimal findPrice(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(true).getPrice();
    }

}
