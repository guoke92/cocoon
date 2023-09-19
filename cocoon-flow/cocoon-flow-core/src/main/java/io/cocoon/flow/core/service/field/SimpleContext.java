package io.cocoon.flow.core.service.field;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 15420
 */
public class SimpleContext {

    private static final Map<String,Object> GLOBAL_MAP = new ConcurrentHashMap<>();

    public void put(String key, Object value){
        GLOBAL_MAP.put(key, value);
    }

    public Object get(String key){
        return GLOBAL_MAP.get(key);
    }
}
