package com.finance.stockdata.service;

import com.finance.commons.enums.LoggerEnum;
import com.finance.commons.logger.Logger;
import com.finance.stockdata.model.StockWrapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class RefreshService {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final Duration refreshPeriod = Duration.ofSeconds(15);
    private final Map<StockWrapper, Boolean> stocksToRefresh = new HashMap<>();

    public RefreshService() {
        setRefreshEvery15Seconds();
    }

    public boolean shouldRefresh(StockWrapper stock) {
        if (!stocksToRefresh.containsKey(stock)) {
            stocksToRefresh.put(stock, false);
            return true;
        }
        return stocksToRefresh.get(stock);
    }

    private void setRefreshEvery15Seconds() {
        scheduler.scheduleAtFixedRate(() -> stocksToRefresh.forEach((stock, value) -> {
            if (stock.getLastAccessed().isBefore(LocalDateTime.now().minus(refreshPeriod))) {
                Logger.info(LoggerEnum.REFRESH_SERVICE, "Setting should refresh " + stock.getStock().getSymbol());
                stocksToRefresh.remove(stock);
                stocksToRefresh.put(stock.withLastAccessed(LocalDateTime.now()), true);
            }
        }), 0, 15, TimeUnit.SECONDS);
    }

}
