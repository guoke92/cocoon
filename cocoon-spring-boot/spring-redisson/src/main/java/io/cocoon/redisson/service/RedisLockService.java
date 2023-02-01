package io.cocoon.redisson.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-11-14 15:12
 **/
@Slf4j
@Service
public class RedisLockService {

    @Autowired
    private RedissonClient redissonClient;

    private static Integer inCre = 0;

    public void inCre(String key){
        RLock lock = redissonClient.getLock(key);
        try {
            lock.lock();

            Thread.sleep(1000);
            inCre = inCre + 1;
            log.info("key: {}, value :{}", key, inCre);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
