package net.changeden.annotationproject.annotations.manager.paramsAnnotation;

import android.view.View;

import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnSetPadding;
import net.changeden.annotationproject.annotations.manager.support.AnnotationManagerInterface;
import net.changeden.annotationproject.annotations.tools.ViewGetter;

import java.lang.reflect.Field;

/**
 * Created by Changeden on 2016/9/5
 */
public class OnSetPaddingManager implements AnnotationManagerInterface {
    private OnSetPaddingManager() {
    }

    public static AnnotationManagerInterface getInstance() {
        return new OnSetPaddingManager();
    }

    @Override
    public void init(float scale, Object object, Field field) {
        OnSetPadding anno = field.getAnnotation(OnSetPadding.class);
        if (anno != null) {
            int id = anno.id();
            int padding = anno.padding();
            int paddingL = anno.paddingL();
            int paddingT = anno.paddingT();
            int paddingR = anno.paddingR();
            int paddingB = anno.paddingB();
            try {
                View view = ViewGetter.getView(object, id, field);
                if (view != null) {
                    int p = Math.max((int) (scale * padding), 0);
                    int pl = (int) (scale * paddingL);
                    int pt = (int) (scale * paddingT);
                    int pr = (int) (scale * paddingR);
                    int pb = (int) (scale * paddingB);
                    if (p > 0) {
                        view.setPadding(p, p, p, p);
                    } else {
                        view.setPadding(pl, pt, pr, pb);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
