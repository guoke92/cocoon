package io.cocoon.redisson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-11-14 14:00
 **/
@Slf4j
@SpringBootApplication
public class SpringRedissonApplication {

    public static void main(String[] args) {
        //new SpringApplicationBuilder(SpringRedissonApplication.class).run(args);
        //SpringApplication.run(SpringRedissonApplication.class, args);
        new SpringApplication(SpringRedissonApplication.class).run(args);
    }
}
