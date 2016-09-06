package net.changeden.annotationproject.annotations.annotation.paramsAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Changeden on 2016/9/5
 *
 * <br>设置外间距
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OnSetMargin {
    int id();
    int margin() default 0;
    int marginL() default 0;
    int marginT() default 0;
    int marginR() default 0;
    int marginB() default 0;
}
