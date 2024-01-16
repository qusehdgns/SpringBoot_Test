package com.test.boot.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class CustomAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.info("-----[{}][{}]-----", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        log.error(ex.getMessage(), ex);
        log.info(method.getName());
        for (Object param : params) {
            log.info(param.toString());
        }
        log.info("--------------------");
    }
}
