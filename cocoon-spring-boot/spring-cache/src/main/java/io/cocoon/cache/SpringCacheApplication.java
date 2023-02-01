package io.cocoon.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-11-11 17:35
 **/
@EnableCaching
@SpringBootApplication
public class SpringCacheApplication {

    public static void main(String[] args) {
        //new SpringApplicationBuilder(SpringCacheApplicationService.class).run(args);

        SpringApplication.run(SpringCacheApplication.class, args);
    }
}
