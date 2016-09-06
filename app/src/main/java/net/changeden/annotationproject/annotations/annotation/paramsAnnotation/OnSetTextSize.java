package net.changeden.annotationproject.annotations.annotation.paramsAnnotation;

import android.util.TypedValue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Changeden on 2016/9/5
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OnSetTextSize {
    int id();
    float size();
    int unit() default TypedValue.COMPLEX_UNIT_PX;
}
