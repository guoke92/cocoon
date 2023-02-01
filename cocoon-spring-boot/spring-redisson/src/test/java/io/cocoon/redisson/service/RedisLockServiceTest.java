package io.cocoon.redisson.service;

import io.cocoon.redisson.SpringRedissonApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = SpringRedissonApplication.class)
public class RedisLockServiceTest {

    @Autowired
    private RedisLockService redisLockService;

    @Test
    public void test() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> redisLockService.inCre("haha")).start();
        }

        for (int i = 0; i < 1000; i++) {
            Thread.sleep(200);
        }
    }

}