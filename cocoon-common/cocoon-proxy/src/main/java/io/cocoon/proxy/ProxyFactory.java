package io.cocoon.proxy;

import io.cocoon.bean.IUser;
import io.cocoon.bean.User;
import net.sf.cglib.core.DebuggingClassWriter;

import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class ProxyFactory<T> {

    private static final ConcurrentHashMap<String, IProxyFactory<?>> knownProxyFactories = new ConcurrentHashMap<>();

    public T getProxy(T target){
        Class<?> targetClass = target.getClass();
        String typeName = targetClass.getTypeName();
        IProxyFactory<T> proxyFactory;
        if(knownProxyFactories.containsKey(typeName)){
            proxyFactory = (IProxyFactory<T>) knownProxyFactories.get(typeName);
        } else {
            if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
                proxyFactory = new JdkProxyFactory<T>(target);
            } else {
                proxyFactory = new CglibProxyFactory<T>(target);
            }
            knownProxyFactories.put(typeName, proxyFactory);
        }
        return (T) proxyFactory.getProxy();
    }

    public static void main(String[] args) {
        // 生成jdk 动态代理class 文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        // 生成cglib 动态代理class文件
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\fanjunwei\\IdeaProjects\\cocoon\\cocoon-common\\cocoon-proxy\\target\\classes\\io\\cocoon\\bean");
        ProxyFactory<IUser> proxyFactory = new ProxyFactory<>();
        User user = new User(10L, "张三");
        user.setAge(20L);
        user.init("121111");
        IUser proxy = proxyFactory.getProxy(user);
        proxy.saySth();

        //ProxyFactory<People> proxyFactory1 = new ProxyFactory<>();
        //People people = proxyFactory1.getProxy(new People(18L, "李四"));
        //people.saySth();

    }
}


