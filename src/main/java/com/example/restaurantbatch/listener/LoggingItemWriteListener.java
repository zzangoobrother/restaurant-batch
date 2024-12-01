package com.example.restaurantbatch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingItemWriteListener implements ItemWriteListener {

    @Override
    public void onWriteError(Exception exception, Chunk items) {
        log.error("쓰기 에러 발생 : {}", exception.getMessage());
    }
}
