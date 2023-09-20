package io.cocoon.flow.core.service.field;

import java.util.function.Function;

public class FunctionTask<T> implements Executable<T>{

    Function<T,T> function;

    public FunctionTask(Function<T,T> function) {
        this.function = function;
    }

    @Override
    public T run(T t){
        return function.apply(t);
    }
}