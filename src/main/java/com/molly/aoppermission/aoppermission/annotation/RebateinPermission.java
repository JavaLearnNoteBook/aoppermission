package com.molly.aoppermission.aoppermission.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface  RebateinPermission {
    /**
     * 接口的限制的权限编码
     * @return
     */
    String permissionValue() default "";
}
