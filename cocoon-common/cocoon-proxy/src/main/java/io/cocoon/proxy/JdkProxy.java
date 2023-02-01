package io.cocoon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class JdkProxy implements InvocationHandler {

    Object target;

    public JdkProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            System.out.println(" 动态代理开始 ");
            return method.invoke(target, args);
        } finally {
            System.out.println(" 动态代理结束 ");
        }
    }
}
