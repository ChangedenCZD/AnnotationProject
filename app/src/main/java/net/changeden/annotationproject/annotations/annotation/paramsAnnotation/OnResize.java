package net.changeden.annotationproject.annotations.annotation.paramsAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Changeden on 2016/9/5
 *
 * <br>宽高注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OnResize {
    int id();
    int width() default -2;
    int height() default -2;
}
