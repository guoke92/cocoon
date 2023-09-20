package io.cocoon.flow.core.service.field;

import java.util.function.DoubleToIntFunction;
import java.util.function.Function;

/**
 * @author 15420
 */
public class TestFlow {
    public static void main(String[] args) {


        Function<Integer,Integer> function = a -> a + 1;
        Integer result = Flow.next(function)
                .next(function)
                .next(function)
                .next(function)
                .next(function)
                .start(1);

        System.out.println("result = " + result);

    }
}
