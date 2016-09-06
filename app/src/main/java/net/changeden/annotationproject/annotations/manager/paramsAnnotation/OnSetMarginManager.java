package net.changeden.annotationproject.annotations.manager.paramsAnnotation;

import android.view.View;
import android.view.ViewGroup;

import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnSetMargin;
import net.changeden.annotationproject.annotations.manager.support.AnnotationManagerInterface;
import net.changeden.annotationproject.annotations.tools.ViewGetter;

import java.lang.reflect.Field;

/**
 * Created by Changeden on 2016/9/5
 */
public class OnSetMarginManager implements AnnotationManagerInterface{
    private OnSetMarginManager(){}
    public static AnnotationManagerInterface getInstance(){
        return new OnSetMarginManager();
    }
    @Override
    public void init(float scale, Object object, Field field) {
        OnSetMargin anno = field.getAnnotation(OnSetMargin.class);
        if(anno!=null){
            int id = anno.id();
            int margin=anno.margin();
            int marginL=anno.marginL();
            int marginT=anno.marginT();
            int marginR=anno.marginR();
            int marginB=anno.marginB();
            try {
                View view = ViewGetter.getView(object, id, field);
                if (view != null) {
                    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int m=Math.max((int) (scale*margin),0);
                    int ml=(int) (scale*marginL);
                    int mt=(int) (scale*marginT);
                    int mr=(int) (scale*marginR);
                    int mb=(int) (scale*marginB);
                    if(lp!=null){
                        if(m>0){
                            lp.setMargins(m,m,m,m);
                        }else{
                            lp.setMargins(ml,mt,mr,mb);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
