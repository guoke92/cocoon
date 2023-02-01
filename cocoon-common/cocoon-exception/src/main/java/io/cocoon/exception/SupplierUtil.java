package io.cocoon.exception;

import com.sun.istack.internal.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-10-08 19:52
 **/
public class SupplierUtil {

    public static Object nullSafeGet(@NotNull Supplier<? extends Object> supplier){
        return Objects.nonNull(supplier) ? supplier.get() : null;
    }

}
