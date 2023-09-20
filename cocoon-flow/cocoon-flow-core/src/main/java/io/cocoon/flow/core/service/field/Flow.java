package io.cocoon.flow.core.service.field;

import java.util.function.Function;

/**
 * @author 15420
 */
public class Flow{

    static Wrapper head;

    public static <T> Wrapper<T> head(Wrapper<T> wrapper) {
        head = wrapper;
        return wrapper;
    }

    public static <T> Wrapper<T> next(Function<T,T> function) {
        Wrapper<T> wrapper = new FunctionWrapper<>(function);
        return next(wrapper);
    }

    public static <T> Wrapper<T> next(Wrapper<T> wrapper) {
        if (null == head){
            return head(wrapper);
        }
        head.next(wrapper);
        return wrapper;
    }

    public static <T> T start(T t){
        return (T) head.start(t);
    }

}