package net.changeden.annotationproject.annotations.manager.paramsAnnotation;

import android.view.View;
import android.view.ViewGroup;

import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnResize;
import net.changeden.annotationproject.annotations.manager.support.AnnotationManagerInterface;
import net.changeden.annotationproject.annotations.tools.ViewGetter;

import java.lang.reflect.Field;

/**
 * Created by Changeden on 2016/9/5
 * <br>OnResize 注解管理器
 */
public class OnResizeManager implements AnnotationManagerInterface {

    private OnResizeManager() {
    }

    public static AnnotationManagerInterface getInstance() {
        return new OnResizeManager();
    }

    @Override
    public void init(float scale, Object object, Field field) {
        OnResize anno = field.getAnnotation(OnResize.class);
        if (anno != null) {
            int id = anno.id();
            int width = anno.width();
            int height = anno.height();
            try {
                View view = ViewGetter.getView(object, id, field);
                if (view != null) {
                    ViewGroup.LayoutParams lp = view.getLayoutParams();
                    int w = Math.max((int) (scale * width), -2);
                    int h = Math.max((int) (scale * height), -2);
                    if (lp == null) {
                        lp = new ViewGroup.LayoutParams(w, h);
                        view.setLayoutParams(lp);
                    } else {
                        lp.width = w;
                        lp.height = h;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
