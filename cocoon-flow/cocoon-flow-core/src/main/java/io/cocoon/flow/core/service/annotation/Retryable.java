package io.cocoon.flow.core.service.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Retryable {

    int value() default 3;

    int times() default 3;

    boolean retryable() default true;

}
