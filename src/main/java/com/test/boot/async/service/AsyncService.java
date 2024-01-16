package com.test.boot.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    @Async
    public void asyncLogger(int i) throws Exception {
        this.logger(i);
    }

    @Async("customExecutor")
    public void asyncLogger1(int i) throws Exception {
        this.logger(i);
    }

    public void logger(int i) throws Exception {
        Thread thread = Thread.currentThread();
        thread.sleep(1000);

        if (i == 8) {
            throw new Exception();
        }

        log.info("[{}][{}][{}]", thread.getStackTrace()[2].getMethodName(), thread.getName(), i);
    }
}
