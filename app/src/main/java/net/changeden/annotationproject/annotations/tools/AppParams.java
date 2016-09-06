package net.changeden.annotationproject.annotations.tools;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Changeden on 2016/9/5
 */
public class AppParams {
    private static float scale=0;
    private static DisplayMetrics dm;

    public static void init(Context context, float srcWidth) {
        if(checkInit())return;
        dm = context.getResources().getDisplayMetrics();
        scale = dm.widthPixels / srcWidth;
        System.out.println("AppParams 初始化成功 scale="+scale);
    }

    public static float getScale() throws Exception {
        if (!checkInit())
            throw new Exception("AppParams 尚未初始化");
        return scale;
    }

    public static float getScale(float srcWidth) throws Exception {
        if (!checkInit())
            throw new Exception("AppParams 尚未初始化");
        return dm.widthPixels / srcWidth;
    }

    public static int getWidth() throws Exception {
        if (!checkInit())
            throw new Exception("AppParams 尚未初始化");
        return dm.widthPixels;
    }

    public static float getDensity() throws Exception {
        if (!checkInit())
            throw new Exception("AppParams 尚未初始化");
        return dm.density;
    }

    private static boolean checkInit(){
        if (scale <= 0) {
            return false;
        }
        return true;
    }
}
