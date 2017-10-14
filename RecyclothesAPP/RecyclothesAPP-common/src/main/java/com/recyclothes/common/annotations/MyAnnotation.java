package com.recyclothes.common.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Cesar on 15-08-2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String   value() default "Valor 100";
    String   name() default  "Cesar";
    int      age() default  41;
    String[] newNames() default {"Criss","Yamilet"};
}
