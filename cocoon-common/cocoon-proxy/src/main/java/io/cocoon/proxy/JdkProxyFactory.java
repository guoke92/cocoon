package io.cocoon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class JdkProxyFactory<T> implements IProxyFactory<T>{

    private T target;
    private Class<T> targetClass;

    public JdkProxyFactory(T target){
        this.target = target;
        this.targetClass = (Class<T>) target.getClass();
    }

    @Override
    public T getProxy() {
        return getProxy(targetClass);
    }

    public T getProxy(Class<T> targetClass){
        Object o = newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), new JdkProxy(target));
        return (T) o;
    }

    private Object newProxyInstance(ClassLoader classLoader,Class<?>[] interfaces, InvocationHandler invocationHandler){
        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }

}
