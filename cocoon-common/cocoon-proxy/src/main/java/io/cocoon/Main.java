package io.cocoon;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        User user1 = new User();
        JdkProxy jdkProxy = new JdkProxy(user1);

        Person user = (Person) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{Person.class}, jdkProxy);
        //String name = user.getName();
        //System.out.println("name = " + name);
        String s = user.toString();
        System.out.println("s = " + s);

    }

    @Data
    static class User implements Person{

        @Override
        public String getName() {
            return "haha";
        }

        @Override
        public String toString(){
            return "toSting";
        }
    }
}

interface Person{
    String getName();
}

class JdkProxy implements InvocationHandler{

    public Object target;

    public JdkProxy(Main.User user1) {
        this.target = user1;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this, args);
        } else {
            return method.invoke(target, args);
        }
    }
}