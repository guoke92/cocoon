package io.cocoon.service;

import io.cocoon.bean.User;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class TestService {

    public void saySth(){
        System.out.println("sth = " + "sth");
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        InterfaceMaker interfaceMaker =new InterfaceMaker();
        //抽取某个类的方法生成接口方法
        interfaceMaker.add(User.class);
        Class<?> targetInterface=interfaceMaker.create();
        for(Method method : targetInterface.getMethods()){
            System.out.println(method.getName());
        }

        //接口代理并设置代理接口方法拦截
        Object object = Enhancer.create(Object.class, new Class[]{targetInterface}, new MethodInterceptor(){
            @Override
            public Object intercept(Object obj, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                if(method.getName().equals("saySth")){
                    System.out.println("filter method1 ");
                    return "mmmmmmmmm";
                }
                if(method.getName().equals("getAge")){
                    System.out.println("filter method2 ");
                    return 1111111;
                }
                if(method.getName().equals("setAge")){
                    System.out.println("filter method3 ");
                    return 3333;
                }
                return "default";
            }});
        Method targetMethod1=object.getClass().getMethod("saySth",new Class[]{int.class});
        int i=(int)targetMethod1.invoke(object, new Object[]{33});
        Method targetMethod=object.getClass().getMethod("getAge",new Class[]{String.class});
        System.out.println(targetMethod.invoke(object, new Object[]{"sdfs"}));
    }

}
