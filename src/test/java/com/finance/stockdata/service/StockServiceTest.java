package com.finance.stockdata.service;

import com.finance.stockdata.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockServiceTest {

    private static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(StockServiceTest.class);
    }

    @Autowired
    private StockService stockService;

    @Test
    public void invoke() {
        final StockWrapper stock = stockService.findStock("UU.L");
        logger.info(stock.getStock().toString());
    }

}
