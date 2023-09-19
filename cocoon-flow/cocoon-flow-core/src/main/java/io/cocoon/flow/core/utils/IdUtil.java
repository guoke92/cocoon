package io.cocoon.flow.core.utils;

import java.util.UUID;

/**
 * @author 15420
 */
public class IdUtil {

    public static String getId(){
        // TODO: 2020/12/2 15420 生成ID的方式需要优化
        return UUID.randomUUID().toString();
    }

    public static String getId(String prefix){
        return prefix + getId();
    }
}
