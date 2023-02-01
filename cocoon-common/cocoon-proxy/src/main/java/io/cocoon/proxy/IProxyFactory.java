package io.cocoon.proxy;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-12-14 18:52
 **/
public interface IProxyFactory<T> {

    T getProxy();

    //T getProxy(ClassLoader classLoader);

    //void initFactory(Class<?> clazz);
}
