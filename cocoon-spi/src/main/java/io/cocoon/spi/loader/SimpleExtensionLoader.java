package io.cocoon.spi.loader;

import io.cocoon.spi.Exception.BaseCode;
import io.cocoon.spi.Exception.BaseException;
import io.cocoon.spi.annotation.SPI;
import io.cocoon.spi.util.ClassUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-08-30 19:06
 **/
public class SimpleExtensionLoader {


    private static final String DEFAULT_CONFIG_DIR = "META-INF/cocoon";

    private static final ConcurrentHashMap<String, Class<?>> cachedClass = new ConcurrentHashMap<>();

    /**
     * 0、 按约定文件目录加载
     */
    public static void loadResource() {
        loadResource(DEFAULT_CONFIG_DIR);
    }

    /**
     * 1、加载配置文件
     */
    public static void loadResource(String url) {
        try {
            Enumeration<URL> config = ClassLoader.getSystemResources(url);
              ClassLoader cl = ClassUtil.getClassLoader(SPI.class);

            while(config.hasMoreElements()){
                parseConfig(cl, config.nextElement());
            }

        } catch (IOException e) {
            throw new BaseException(BaseCode.S0001, e);
        }
    }

    /**
     * 2、按约定格式 解析配置文件, 并缓存解析结果
     */
    public static void parseConfig(ClassLoader cl, URL url) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));

            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] split = line.split("=");
                System.out.println("line = " + line);
                String beanName = split[0];
                String classPath = split[1];
                loadClass(classPath, cl);
            }
        } catch (Throwable e) {
            throw new BaseException(BaseCode.S0001, e);
        }
    }

    /**
     * 3、按约定格式 解析配置文件, 并缓存解析结果
     */
    public static void loadClass(String name, ClassLoader cl){

        try {
            Class<?> aClass = Class.forName(name, true, cl);

            cachedClass.putIfAbsent(name, aClass);
        } catch (Throwable e) {
            throw new BaseException(BaseCode.S0001, e);
        }

    }

    public static Object getExtension(String name){
        try {
            Class<?> aClass = cachedClass.get(name);
            return aClass.newInstance();
        } catch (Throwable e) {
            throw new BaseException(BaseCode.S0001, e);
        }
    }

    public static void main(String[] args) {

        String name = "io.cocoon.spi.loader.SimpleExtensionLoader";
        //SimpleExtensionLoader.loadClass(name, ClassUtil.getClassLoader(SPI.class));
        SimpleExtensionLoader.loadResource();

        Object extension = SimpleExtensionLoader.getExtension(name);

        System.out.println("extension = " + extension.getClass());

    }

}
