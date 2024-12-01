package com.example.restaurantbatch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingItemReadListener implements ItemReadListener {

    @Override
    public void onReadError(Exception ex) {
        log.error("읽기 에러 발생 : {}", ex.getMessage());
    }
}
