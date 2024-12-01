package com.example.restaurantbatch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingChunkListener implements ChunkListener {

    @Override
    public void afterChunkError(ChunkContext context) {
        StepContext stepContext = context.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();

        log.error("에러 발생 read count : {}", stepExecution.getReadCount());
        log.error("에러 발생 commit count : {}", stepExecution.getCommitCount());
        log.error("에러 발생 rollback count : {}", stepExecution.getRollbackCount());
    }
}
