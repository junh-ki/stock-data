package com.finance.stockdata.service;

import com.finance.commons.enums.LoggerEnum;
import com.finance.commons.logger.Logger;
import com.finance.stockdata.model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class StockService {

    public StockWrapper findStock(String ticker) {
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (Exception e) {
            Logger.error(LoggerEnum.STOCK_DATA_SERVICE, "Error", e);
        }
        return null;
    }

    public BigDecimal findPrice(StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(true).getPrice();
    }

}
