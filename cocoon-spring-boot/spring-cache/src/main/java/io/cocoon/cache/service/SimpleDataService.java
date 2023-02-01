package io.cocoon.cache.service;

import io.cocoon.cache.data.SimpleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-11-11 17:54
 **/
@Slf4j
@Service
public class SimpleDataService {

    @Autowired
    private SimpleData simpleData;

    @Autowired
    private ApplicationContext applicationContext;

    public void simpleDataProc(String args){

        String[] split = args.trim().replaceAll("  ", " ").split(" ");
        try {
            String option = split[0];
            switch (option){
                case "C":{
                    Integer key = Integer.valueOf(split[1]);
                    Integer value = Integer.valueOf(split[2]);
                    simpleData.insertData(key, value);
                    log.info("新增 key : {}, value： {}", key, value);
                    break;
                }
                case "R":{
                    Integer key = Integer.valueOf(split[1]);
                    Integer value = simpleData.getData(key);
                    log.info("查询 key : {}， 查询结果 value {}", key, value);
                    break;
                }
                case "U":{
                    Integer key = Integer.valueOf(split[1]);
                    Integer value = Integer.valueOf(split[2]);
                    simpleData.setData(key, value);
                    log.info("更新 key : {}, value： {}", key, value);
                    break;
                }
                case "D":{
                    Integer key = Integer.valueOf(split[1]);
                    simpleData.removeData(key);
                    log.info("删除 key : {}", key);
                    break;
                }
                default:
                    log.error("无效的操作符 {}", option);
            }
        } catch (Exception e) {
            log.error("异常", e);
        }

    }

}
