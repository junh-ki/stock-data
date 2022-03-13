package com.finance.stockdata.service;

import com.finance.commons.enums.LoggerEnum;
import com.finance.commons.logger.Logger;
import com.finance.stockdata.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

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
    }

}
