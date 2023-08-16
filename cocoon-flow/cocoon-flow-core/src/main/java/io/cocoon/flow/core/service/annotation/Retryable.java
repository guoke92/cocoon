package io.cocoon.flow.core.service.annotation;

import java.lang.annotation.*;

/**
 * @author 15420
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Retryable {

//    int value() default 3;

    int retryTimes() default 3;

    boolean retryable() default false;

}
