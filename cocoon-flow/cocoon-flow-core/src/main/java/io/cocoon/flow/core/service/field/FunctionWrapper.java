package io.cocoon.flow.core.service.field;

import java.util.function.Function;

public class FunctionWrapper<T> extends Wrapper<T>{

    public FunctionWrapper(Function<T, T> function) {
        this.executable = new FunctionTask<>(function);
    }

    @Override
    public T run(T t) {
        return executable.run(t);
    }
}