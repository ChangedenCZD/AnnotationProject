package net.changeden.annotationproject.annotations.manager.listenerAnnotation;

import android.text.TextUtils;
import android.view.View;

import net.changeden.annotationproject.annotations.annotation.listenerAnnotation.OnClick;
import net.changeden.annotationproject.annotations.manager.support.AnnotationManagerInterface;
import net.changeden.annotationproject.annotations.tools.ViewGetter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Changeden on 2016/9/6
 *
 * <br>OnClick 注解管理器
 */
public class OnClickManager implements AnnotationManagerInterface {
    private OnClickManager() {
    }

    public static OnClickManager getInstance() {
        return new OnClickManager();
    }

    @Override
    public void init(float scale, Object object, Field field) {
        OnClick anno = field.getAnnotation(OnClick.class);
        if (anno != null) {
            int id = anno.id();
            String methodName = anno.methodName();
            boolean hasParams = anno.hasParams();
            try {
                View view = ViewGetter.getView(object, id, field);
                if (view != null && !TextUtils.isEmpty(methodName)) {
                    Method[] methods = object.getClass().getMethods();
                    if (methods != null) {
                        for (Method method : methods) {
                            if (method != null && methodName.equals(method.getName())) {
                                Class<?>[] params = method.getParameterTypes();
                                if(hasParams){
                                    if(params!=null&&params.length>0){
                                        if(View.class.equals(params[0])){
                                            view.setOnClickListener(new OnClickL(MODE_1, method, object));
                                        }
                                    }
                                }else{
                                    if (params != null&&params.length==0) {
                                        view.setOnClickListener(new OnClickL(MODE_0, method, object));
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final int MODE_E = -1;
    private static final int MODE_0 = 0;
    private static final int MODE_1 = 1;

    class OnClickL implements View.OnClickListener {
        private Method method;
        private Object object;
        private int mode = MODE_E;

        public OnClickL(int mode, Method method, Object object) {
            this.mode = mode;
            this.method = method;
            this.object = object;
        }

        public void onClick(View view) {
            if (method != null && object != null) {
                try {
                    switch (mode) {
                        case MODE_0:
                            method.invoke(object);
                            break;
                        case MODE_1:
                            method.invoke(object, view);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
