package net.changeden.annotationproject.annotations.manager;

import android.content.Context;
import android.util.Log;

import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnResize;
import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnSetMargin;
import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnSetPadding;
import net.changeden.annotationproject.annotations.annotation.paramsAnnotation.OnSetTextSize;
import net.changeden.annotationproject.annotations.manager.paramsAnnotation.OnResizeManager;
import net.changeden.annotationproject.annotations.manager.paramsAnnotation.OnSetMarginManager;
import net.changeden.annotationproject.annotations.manager.paramsAnnotation.OnSetPaddingManager;
import net.changeden.annotationproject.annotations.manager.paramsAnnotation.OnSetTextSizeManager;
import net.changeden.annotationproject.annotations.manager.support.AnnotationViewGetterInterface;
import net.changeden.annotationproject.annotations.tools.AppParams;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by Changeden on 2016/9/5
 * <br>自定义注解管理器
 */
public class AnnotationManager {
    private static boolean debug = false;

    private android.app.Activity activity;
    private android.app.Fragment app_fragment;
    private android.support.v4.app.Fragment v4_fragment;
    private AnnotationViewGetterInterface viewGetterInterface;

    private static float scale = 0;

    /**
     * 私有化构造函数
     */
    private AnnotationManager() {
    }

    /**
     * 获取 注解管理器 实例
     */
    public static AnnotationManager getInstance() {
        return new AnnotationManager();
    }

    /**
     * 私有化 公用 初始化方法
     */
    private void init(Context context) {
        if (scale <= 0) {
            AppParams.init(context, 640f);
            try {
                scale = AppParams.getScale();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fixAnyViews();
    }

    /**
     * <br>初始化
     * <br>可在 android.app.Activity的onCreate方法中调用
     * @see android.app.Activity
     */
    public void init(android.app.Activity activity) {
        this.activity = activity;
        init(activity.getBaseContext());
    }

    /**
     * <br>初始化
     * <br>可在 android.app.Fragment的onViewCreated方法中调用
     *
     * @see android.app.Fragment
     */
    public void init(android.app.Fragment fragment) {
        if (fragment != null) {
            app_fragment = fragment;
            init(fragment.getActivity());
        }
    }

    /**
     * <br>初始化
     * <br>可在 android.support.v4.app.Fragment的onViewCreated方法调用
     *
     * @see android.support.v4.app.Fragment
     */
    public void init(android.support.v4.app.Fragment fragment) {
        if (fragment != null) {
            v4_fragment = fragment;
            init(fragment.getActivity());
        }
    }

    /**
     * <br>初始化
     * <br>使用前，必须实现 AnnotationViewGetterInterface 接口
     *
     * @see AnnotationViewGetterInterface
     */
    public void init(Context context, AnnotationViewGetterInterface viewGetterInterface) {
        if (context != null && viewGetterInterface != null) {
            this.viewGetterInterface = viewGetterInterface;
            init(context);
        }
    }

    /**
     * <br>开始根据参数进行自动化设置宽高参数
     */
    private void fixAnyViews() {
        Object object = null;
        if (app_fragment != null) {
            object = app_fragment;
        } else if (v4_fragment != null) {
            object = v4_fragment;
        } else if (activity != null) {
            object = activity;
        } else if (viewGetterInterface != null) {
            object = viewGetterInterface;
        }
        d(AnnotationManager.class.getName() + " 进入初始化 目标类型为 = " + (object != null ? object.getClass().getName() : "空"));
        if (object != null) {
            Field[] fields = object.getClass().getFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    if (field != null) {
                        parseAnnotation(object, field);
                    }
                }
            } else {
                d(AnnotationManager.class.getName() + " 初始化 暂无成员列表");
            }
        } else {
            d(AnnotationManager.class.getName() + " 初始化失败");
        }
    }

    /**
     * <br>开始解释注解
     */
    private void parseAnnotation(Object object, Field field) {
        Annotation[] array = field.getAnnotations();
        if (array != null && array.length > 0) {
            for (Annotation annotation : array) {
                if (annotation != null) {
                    if (annotation instanceof OnResize) {
                        OnResizeManager.getInstance().init(scale, object, field);
                    } else if (annotation instanceof OnSetMargin) {
                        OnSetMarginManager.getInstance().init(scale, object, field);
                    } else if (annotation instanceof OnSetPadding) {
                        OnSetPaddingManager.getInstance().init(scale, object, field);
                    } else if (annotation instanceof OnSetTextSize) {
                        OnSetTextSizeManager.getInstance().init(scale, object, field);
                    }
                }
            }
        }
    }

    /**
     * 设置是否可以 Debug
     */
    public static void debugable(boolean debug) {
        AnnotationManager.debug = debug;
    }

    /**
     * 输出 Debug 内容
     */
    protected static void d(Object object) {
        if (debug) {
            if (object != null) {
                Log.d(AnnotationManager.class.getSimpleName(), String.valueOf(object));
            } else {
                Log.d(AnnotationManager.class.getSimpleName(), "参数为空");
            }
        }
    }
}
