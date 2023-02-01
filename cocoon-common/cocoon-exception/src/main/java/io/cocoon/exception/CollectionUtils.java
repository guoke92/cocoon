package io.cocoon.exception;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-10-08 20:05
 **/
public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection){
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map){
        return Objects.isNull(map) || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map){
        return !isEmpty(map);
    }

}
