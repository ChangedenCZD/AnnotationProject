package net.changeden.annotationproject.annotations.annotation.paramsAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Changeden on 2016/9/5
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OnSetPadding {
    int id();
    int padding() default 0;
    int paddingL() default 0;
    int paddingT() default 0;
    int paddingR() default 0;
    int paddingB() default 0;
}
