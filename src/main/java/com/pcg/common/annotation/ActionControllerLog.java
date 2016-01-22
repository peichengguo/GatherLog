package com.pcg.common.annotation;

import java.lang.annotation.*;

/**
 * Created by pcg on 16/1/21.
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionControllerLog {

    String title() default "";
}
