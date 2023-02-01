package io.cocoon.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class CglibProxyFactory<T> implements IProxyFactory<T>{

    private final T target;
    Class<T> targetClass;

    public CglibProxyFactory(T target){
        this.target = target;
        this.targetClass = (Class<T>) target.getClass();
    }

    @Override
    public T getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(targetClass.getClassLoader());
        enhancer.setSuperclass(targetClass);
        Callback callback = new CglibProxy(target);
        Callback noOp = NoOp.INSTANCE;
        Callback[] callbacks = new Callback[]{callback, noOp};
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(method -> {
            // idea debug时会调用对象的toString 方法用来在debugger界面显示调试信息， Object.toString 默认又会调用hashCode
            if("toString".equals(method.getName()) || "hashCode".equals(method.getName())){
                return 1;
            }
            return 0;
        });
        // 配合 methodProxy.invokeSuper(proxy, objects) 使用，由cglib创建对象实例作为被代理的委托对象实例
        //return (T) enhancer.create(new Class[]{Long.class, String.class}, new Object[]{20L, "123"});
        // 配合 methodProxy.invoike(target, objects) 使用，直接代理传入的委托对象实例，cglib 不创建新的实例
        return (T) enhancer.create();
    }

}
