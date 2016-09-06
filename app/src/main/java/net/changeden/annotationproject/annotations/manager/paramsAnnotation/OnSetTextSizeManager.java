package net.changeden.annotationproject.annotations.manager.paramsAnnotation;

import android.view.View;
import android.widget.TextView;

import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnSetTextSize;
import net.changeden.annotationproject.annotations.manager.support.AnnotationManagerInterface;
import net.changeden.annotationproject.annotations.tools.ViewGetter;

import java.lang.reflect.Field;

/**
 * Created by Changeden on 2016/9/5
 */
public class OnSetTextSizeManager implements AnnotationManagerInterface{
    private OnSetTextSizeManager(){}
    public static OnSetTextSizeManager getInstance(){
        return new OnSetTextSizeManager();
    }
    @Override
    public void init(float scale, Object object, Field field) {
        OnSetTextSize anno = field.getAnnotation(OnSetTextSize.class);
        if (anno != null) {
            int id = anno.id();
            float size = anno.size();
            int unit=anno.unit();
            try {
                View view = ViewGetter.getView(object, id, field);
                if (view != null&& view instanceof TextView) {
                    ((TextView)view).setTextSize(unit,scale*size);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
