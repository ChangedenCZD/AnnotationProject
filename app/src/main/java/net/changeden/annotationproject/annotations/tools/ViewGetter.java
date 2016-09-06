package net.changeden.annotationproject.annotations.tools;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;

import net.changeden.annotationproject.annotations.manager.support.AnnotationViewGetterInterface;

import java.lang.reflect.Field;

/**
 * Created by Changeden on 2016/9/5
 * <p/>
 * <br> View 获取工具
 */
public class ViewGetter {
    /**
     * 获取 View 不可赋值
     * */
    @Nullable
    public static View getView(Object object, int id) {
        View view = null;
        if (object instanceof Activity) {
            view = ((Activity) object).findViewById(id);
        } else if (object instanceof android.app.Fragment) {
            view = getView(id, ((android.app.Fragment) object).getView());
        } else if (object instanceof android.support.v4.app.Fragment) {
            view = getView(id, ((android.support.v4.app.Fragment) object).getView());
        } else if (object instanceof AnnotationViewGetterInterface) {
            view = getView(id, ((AnnotationViewGetterInterface) object).getView());
        }
        return view;
    }

    /**
     * 获取 View 可赋值
     * */
    @Nullable
    public static View getView(Object object, int id, Field field) {
        View view = getView(object, id);
        try {
            if (view != null && field.get(object) == null) {
                field.set(object, view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private static View getView(int id, View view) {
        if (view != null) {
            view = view.findViewById(id);
        }
        return view;
    }
}
