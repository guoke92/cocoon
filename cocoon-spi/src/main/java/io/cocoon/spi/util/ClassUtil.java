package io.cocoon.spi.util;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-08-30 19:41
 **/
public class ClassUtil {

    public static ClassLoader getClassLoader() {
        return getClassLoader(ClassUtil.class);
    }

    public static ClassLoader getClassLoader(Class<?> type) {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            // do nothing
        }
        if (cl == null) {
            cl = type.getClassLoader();
            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
        return cl;
    }

}
