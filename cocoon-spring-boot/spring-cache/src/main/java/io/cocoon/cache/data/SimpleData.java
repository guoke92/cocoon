package io.cocoon.cache.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-11-11 17:51
 **/
@Slf4j
@Service
public class SimpleData {

    public static final Map<Integer,Integer> simpleDataMap = new HashMap<>();

    static{
        simpleDataMap.put(1, 0);
        simpleDataMap.put(2, 0);
        simpleDataMap.put(3, 0);
        simpleDataMap.put(4, 0);
        simpleDataMap.put(5, 0);
        simpleDataMap.put(6, 0);
        simpleDataMap.put(7, 0);
    }

    @Cacheable(cacheNames = "simple:data", key = "#key", unless = "#result == null")
    public Integer getData(Integer key){
        log.info("getData");
        return simpleDataMap.get(key);
    }

    @CachePut(cacheNames = "simple:data", key = "#key")
    public Integer setData(Integer key, Integer value){
        log.info("setData");
        simpleDataMap.put(key, value);

        return value;
    }

    @CacheEvict(cacheNames = "simple:data", key = "#key")
    public void removeData(Integer key){
        log.info("removeData");
        simpleDataMap.remove(key);
    }

    @CachePut(cacheNames = "simple:data", key = "#key")
    public Integer insertData(Integer key, Integer value){

        log.info("insertData");
        simpleDataMap.put(key, value);
        return value;
    }

}
