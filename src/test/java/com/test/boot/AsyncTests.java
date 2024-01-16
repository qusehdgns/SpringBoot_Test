package com.test.boot;

import com.test.boot.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;

/**
 * @see <a href="https://dkswnkk.tistory.com/706">https://dkswnkk.tistory.com/706</a>
 */
@Slf4j
@SpringBootTest
class AsyncTests {

    @Autowired
    private AsyncService asyncService;

    /**
     * @deprecated @Async Exception 발생 시 처리하는 방법을 구현했습니다.
     * try-catch 문을 통해 들어오지 않고
     * AsyncConfig.getAsyncUncaughtExceptionHandler()를 통해 처리되는 것을 확인할 수 있습니다.
     *
     * @see com.test.boot.common.handler.CustomAsyncUncaughtExceptionHandler
     * @throws InterruptedException
     */
    @Test
    void asyncUncaughtExceptionTest() throws InterruptedException {
        log.info("asyncUncaughtExceptionTest start");

        try {
            for (int i = 0; i < 10; i++) {
                asyncService.asyncLogger(i);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.info("NO ACTION");
        }

        Thread.sleep(5000);

        log.info("asyncUncaughtExceptionTest end");
    }

    /**
     * @deprecated
     * 특정 Executor를 지정하여 처리하는 방법을 구현했습니다.
     * 여러 Executor가 정의되었을 때 @Async(executorName)을 통해 지정 처리가 가능한 것을 확인할 수 있습니다.
     *
     * @throws InterruptedException
     */
    @Test
    void taskExecutorTest() throws InterruptedException {
        log.info("taskExecutorTest start");

        try {
            for (int i = 0; i < 10; i++) {
                asyncService.asyncLogger1(i);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.info("NO ACTION");
        }

        Thread.sleep(5000);

        log.info("taskExecutorTest end");
    }

    /**
     * @deprecated
     * 같은 클래스 내부 @Async 메소드 호출 시 비동기가 동작하지 않는 것을 구현했습니다.
     * 동일 Thread에서 처리되며 비동기 처리가 되지 않는 것을 확인할 수 있습니다.
     *
     * @throws InterruptedException
     */
    @Test
    void sameClassAsyncTest() throws InterruptedException {
        log.info("sameClassAsyncTest start");

        try {
            for (int i = 0; i < 10; i++) {
                    this.asyncLogger(i);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.info("NO ACTION");
        }

        Thread.sleep(5000);

        log.info("sameClassAsyncTest end");
    }

    @Async
    public void asyncLogger(int i) throws Exception {
        asyncService.logger(i);
    }

}
