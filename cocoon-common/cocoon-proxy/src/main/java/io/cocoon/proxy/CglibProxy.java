package io.cocoon.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class CglibProxy implements MethodInterceptor {

    Object target;

    public CglibProxy(Object target){
        this.target = target;
    }


    /**
     *
     * @param proxy 表示要进行增强的对象
     * @param method 表示拦截的方法
     * @param objects 数组表示参数列表，基本数据类型需要传入其包装类型，如int-->Integer、long-Long、double-->Double
     * @param methodProxy 表示对方法的代理，invokeSuper方法表示对被代理对象方法的调用
     * @return 执行结果
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        try {
            System.out.println(" cglib动态代理开始 " + proxy.getClass() + "-----"+ method.getName() + "-----"+ System.currentTimeMillis());
            // 注意这里是调用invokeSuper而不是invoke，否则死循环;
            // methodProxy.invokeSuper执行的是原始类的方法;
            // method.invoke执行的是子类的方法;
            // 代理对象
            return methodProxy.invoke(target, objects);
            // 代理类，cglib默认生成对象，enhancer.create 时需要传入创建对象需要的参数
            //return methodProxy.invokeSuper(proxy, objects);
            /*
             * //ERROR
             *  System.out.println(proxy.toString());
             * //ERROR
             *  return methodProxy.invoke(proxy,args);
             */
        } finally {
            System.out.println(" cglib动态代理结束 " + proxy.getClass() + "-----"+ method.getName() + "-----"+ System.currentTimeMillis());
        }
    }

}
