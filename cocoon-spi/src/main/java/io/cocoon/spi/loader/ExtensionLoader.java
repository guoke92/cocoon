package io.cocoon.spi.loader;

import io.cocoon.spi.Exception.BaseCode;
import io.cocoon.spi.Exception.BaseException;
import io.cocoon.spi.annotation.SPI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-08-24 17:20
 **/
public class ExtensionLoader<T> {

    private final static ConcurrentHashMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADER = new ConcurrentHashMap();

    private final static ConcurrentHashMap<Class<?>, ExtensionLoader<?>> CACHE_INSTANCE = new ConcurrentHashMap();

    private Class<?> type;

    private ExtensionLoader(Class<?> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {

        if (Objects.isNull(type)) {
            throw new BaseException(BaseCode.S0001);
        }


        if (!type.isInterface()) {
            throw new BaseException(BaseCode.S0001);
        }

        if (!type.isAnnotationPresent(SPI.class)) {
            throw new BaseException(BaseCode.S0001);
        }

        ExtensionLoader<T> extensionLoader = (ExtensionLoader<T>) EXTENSION_LOADER.get(type);
        if (Objects.isNull(extensionLoader)) {
            EXTENSION_LOADER.putIfAbsent(type, new ExtensionLoader<T>(type));
            extensionLoader = (ExtensionLoader<T>) EXTENSION_LOADER.get(type);
        }

        String[] a = {""};

        Arrays.sort(a, Comparator.comparing( x -> x));
        return extensionLoader;
    }
}
