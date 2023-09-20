package io.cocoon.flow.core.service.field;

import java.util.function.Function;

public abstract class Wrapper<T> implements Executable<T>{


    Wrapper next;

    Wrapper pre;

    Executable<T> executable;

    public <T> Wrapper<T> next(Wrapper<T> wrapper){
        this.next = wrapper;
        wrapper.pre = this;
        return wrapper;
    }

    public <T> Wrapper<T> next(Function<T,T> function) {
        Wrapper<T> wrapper = new FunctionWrapper<>(function);
        return next(wrapper);
    }

    public T start(T t) {
        T result = t;
        Wrapper head = this;
        while(head.pre != null && head != head.pre){
            head = head.pre;
        }
        Wrapper curr = head;
        while (curr != null){
            result = (T) curr.run(result);
            curr = curr.next;
        }
        return result;
    }
}
