package com.finance.stockdata.service;

import com.finance.commons.enums.LoggerEnum;
import com.finance.commons.logger.Logger;
import com.finance.stockdata.model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StockService {

    private final RefreshService refreshService;

    public StockWrapper findStock(String ticker) {
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (Exception e) {
            Logger.error(LoggerEnum.STOCK_DATA_SERVICE, "Error", e);
        }
        return null;
    }

    public List<StockWrapper> findStocks(List<String> tickers) {
        return tickers.stream()
                .map(this::findStock)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public BigDecimal findPrice(StockWrapper stock) throws IOException {
        return stock.getStock()
                .getQuote(refreshService.shouldRefresh(stock))
                .getPrice();
    }

    public BigDecimal findLastChangedPercent(StockWrapper stock) throws IOException {
        return stock.getStock()
                .getQuote(refreshService.shouldRefresh(stock))
                .getChangeInPercent();
    }

    public BigDecimal findChangeFrom200DaysMeanPercent(StockWrapper stock) throws IOException {
        return stock.getStock()
                .getQuote(refreshService.shouldRefresh(stock))
                .getChangeFromAvg200InPercent();
    }

}
