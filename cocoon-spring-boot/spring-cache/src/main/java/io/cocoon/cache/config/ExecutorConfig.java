package io.cocoon.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-11-11 17:05
 **/
@Slf4j
@Configuration
public class ExecutorConfig {

    // 获取当前机器 cpu 核数
    private final static int AVAILABLE_PROCESSOR = Runtime.getRuntime().availableProcessors();

    @Bean("cacheExecutor")
    public Executor cacheExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //设置线程池参数信息
        taskExecutor.setCorePoolSize(AVAILABLE_PROCESSOR);
        taskExecutor.setMaxPoolSize(AVAILABLE_PROCESSOR * 2);
        taskExecutor.setQueueCapacity(1024);
        taskExecutor.setKeepAliveSeconds(60 * 10);
        taskExecutor.setThreadNamePrefix("cacheThreadPool--");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);

        //修改拒绝策略为使用当前线程执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化线程池
        taskExecutor.initialize();
        return taskExecutor;
    }

}
