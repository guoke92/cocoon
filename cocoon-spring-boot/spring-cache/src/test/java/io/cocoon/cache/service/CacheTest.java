package io.cocoon.cache.service;

import io.cocoon.cache.SpringCacheApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-11-11 17:59
 **/
@Slf4j
@SpringBootTest(classes = {SpringCacheApplication.class})
public class CacheTest {

    @Autowired
    private SimpleDataService simpleDataService;

    @Test
    public void test(){

        Scanner scanner = new Scanner(System.in);
        while(true){
            String readStr = scanner.nextLine();
            simpleDataService.simpleDataProc(readStr);
        }

    }

}
