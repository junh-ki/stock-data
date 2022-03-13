package com.finance.stockdata.service;

import com.finance.stockdata.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest
public class StockServiceTest {

    private static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(StockServiceTest.class);
    }

    @Autowired
    private StockService stockService;

    @Test
    public void invoke() throws IOException {
        StockWrapper stock = stockService.findStock("UU.L");
        logger.info(stock.getStock().toString());
        BigDecimal price = stockService.findPrice(stock);
        logger.info(price.toString());
    }

}
