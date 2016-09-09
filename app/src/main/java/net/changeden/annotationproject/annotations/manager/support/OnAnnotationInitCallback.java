package net.changeden.annotationproject.annotations.manager.support;

import android.content.Context;

/**
 * Created by Changeden on 2016/9/9
 *
 * <br>用于 AnnotationManager 中初始化方法的代理
 */
public interface OnAnnotationInitCallback<T> {
    void onInit(Context context,T t,float scale);
}
