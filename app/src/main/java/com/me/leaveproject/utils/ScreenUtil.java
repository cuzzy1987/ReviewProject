package com.me.leaveproject.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class ScreenUtil {

    public static String TAG = "ScreenUtil";

    public static void setFullScreen(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = activity.getWindow();
        window.setFlags(flag,flag);
    }

    public static void cleanFullScreen(Activity activity){
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void setStatusBarTextDark(Activity activity){
        View decor = activity.getWindow().getDecorView();
        decor.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    // TODO: 2019-07-17
    /* 沉浸式状态栏 即透明状态栏原名Translucent Bars 即打开后通知栏和app顶部融为一体*/
    /* 使用时需在布局文件中配合 android:fitsSystemWindows="true" 属性 */
    public static void setTranslucent(Activity activity,boolean isDarkStatus){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            Log.e(TAG,"setTranslucent: 当前版本不支持沉浸式状态栏 version code = "+Build.VERSION.SDK_INT);
            return;
        }

        transparentStatusBar(activity);

        if (isDarkStatus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            setStatusBarTextDark(activity);
        }
        setRootView(activity);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个flag contentView才能延伸到状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //状态栏覆盖在contentView上面，设置透明使contentView的背景透出来
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            //让contentView延伸到状态栏并且设置状态栏颜色透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private static void setRootView(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }

}
