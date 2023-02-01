package io.cocoon.spi.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface SPI {

    /**
     * 指定扩展名称
     */
    String value() default "";

}
