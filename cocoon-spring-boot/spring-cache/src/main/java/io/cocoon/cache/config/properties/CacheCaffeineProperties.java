package io.cocoon.cache.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static io.cocoon.cache.config.properties.CacheCaffeineProperties.CACHE_PROPERTIES_PREFIX;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-11-11 17:24
 **/
@Data
@ConfigurationProperties(prefix = CACHE_PROPERTIES_PREFIX)
public class CacheCaffeineProperties {

    public static final String CACHE_PROPERTIES_PREFIX = "cocoon.cache.caffeine";

    // 失效时间
    private Long expireSecond = 10L;

}
