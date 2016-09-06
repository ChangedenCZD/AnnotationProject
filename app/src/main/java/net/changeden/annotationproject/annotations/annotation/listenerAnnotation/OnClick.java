package net.changeden.annotationproject.annotations.annotation.listenerAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Changeden on 2016/9/6
 * <br>点击事件
 * <br>默认有参方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OnClick {
    int id();
    String methodName();
    boolean hasParams();
}
