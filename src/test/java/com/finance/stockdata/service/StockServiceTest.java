package com.finance.stockdata.service;

import com.finance.commons.enums.LoggerEnum;
import com.finance.commons.logger.Logger;
import com.finance.stockdata.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    public void invoke() throws IOException {
        StockWrapper stock = stockService.findStock("UU.L");
        Logger.info(LoggerEnum.STOCK_DATA_TEST, stock.getStock().toString());
        BigDecimal price = stockService.findPrice(stock);
        Logger.info(LoggerEnum.STOCK_DATA_TEST, price.toString());
        BigDecimal lastChangedPercent = stockService.findLastChangedPercent(stock);
        Logger.info(LoggerEnum.STOCK_DATA_TEST, lastChangedPercent.toString());
        BigDecimal changeFrom200DaysMeanPercent = stockService.findChangeFrom200DaysMeanPercent(stock);
        Logger.info(LoggerEnum.STOCK_DATA_TEST, changeFrom200DaysMeanPercent.toString());
    }

    @Test
    public void multiple() throws InterruptedException {
        List<StockWrapper> stocks = stockService.findStocks(Arrays.asList("GOOG", "AMZN"));
        findPrices(stocks);
        Thread.sleep(20000);
        //StockWrapper aa = stockService.findStock("AA.L");
        //stocks.add(aa);
        //Logger.info(LoggerEnum.STOCK_DATA_TEST, stockService.findPrice(aa).toString());
        findPrices(stocks);
    }

    private void findPrices(List<StockWrapper> stocks) {
        stocks.forEach(stock -> {
            try {
                Logger.info(LoggerEnum.STOCK_DATA_TEST, stockService.findPrice(stock).toString());
            } catch (IOException ioe) {
                Logger.error(LoggerEnum.STOCK_DATA_TEST, "Error", ioe);
            }
        });
    }

}
