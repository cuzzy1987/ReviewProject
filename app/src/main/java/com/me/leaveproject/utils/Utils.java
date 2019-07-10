package com.me.leaveproject.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

/**
 * @author aband
 * @brief 工具类
 * @date 2018/9/5.
 */
public class Utils {
    private static final String TAG = "Utils";
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * @param context
     * @param num
     * @return
     */
    public static int getScaledValueX(Context context, int num) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        Configuration mConfiguration = context.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
            screenWidth = dm.heightPixels;
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
            screenWidth = dm.widthPixels;
        }
        float scaleX = (float) ((float) screenWidth / 320.0);
        float numtemp = scaleX * num;
        return (int) numtemp;
    }

    public static int getScaledValueY(Context context, int num) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenHeight = dm.heightPixels;
        Configuration mConfiguration = context.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
            screenHeight = dm.widthPixels;
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
            screenHeight = dm.heightPixels;
        }
        float scaleY = (float) ((float) screenHeight / 480.0);
        float numtemp = scaleY * num;
        return (int) numtemp;
    }

    public static int defaultToScreen750(Context context, int value) {
        double resultD = value * 320 / 750.0;
        long resultL = Math.round(resultD);
        return Utils.getScaledValueX(context, (int) resultL);
    }

    public static int getScaledValueHeight(int srcW, int srcH, int targetW) {
        float scale = srcH * 1.0f / srcW;
        return (int) (scale * targetW);
    }

    public static int getScaledValueWidth(int srcW, int srcH, int targetH) {
        float scale = srcH * 1.0f / srcW;
        return (int) (targetH / scale);
    }


    public static String getImageAbsolutePath(Activity context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri
                .getAuthority());
    }

    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static boolean checkActivityFinish(Context context) {
        return (context instanceof Activity) && !((Activity) context).isFinishing();
    }

    // 获取并判断魅族手机Flyme版本号是否低于2.4
    public static boolean getFlymeVersion() {
        try {
            String Version = Build.DISPLAY;
            if (((Build.DEVICE).contains("mx") && (Build.PRODUCT)
                    .contains("meizu_mx"))
                    || (Version != null && Version.contains("Flyme"))) {
                String flymeVersion = Build.DISPLAY;
                flymeVersion = flymeVersion.substring(9, flymeVersion.length());
                if (flymeVersion != null && flymeVersion.length() > 3) {
                    float version = Float.parseFloat(flymeVersion.substring(0,
                            3));
                    return version < 2.5f;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 隐藏魅族手机smartbar
    public static void hide(View decorView) {
        if (!hasSmartBar())
            return;
        try {
            @SuppressWarnings("rawtypes")
            Class[] arrayOfClass = new Class[1];
            arrayOfClass[0] = Integer.TYPE;
            Method localMethod = View.class.getMethod("setSystemUiVisibility",
                    arrayOfClass);
            Field localField = View.class
                    .getField("SYSTEM_UI_FLAG_HIDE_NAVIGATION");
            Object[] arrayOfObject = new Object[1];
            try {
                arrayOfObject[0] = localField.get(null);
            } catch (Exception e) {
            }
            localMethod.invoke(decorView, arrayOfObject);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 判断是否有动态SmartBar
    private static boolean hasSmartBar() {
        try {
            // 新型号可用反射调用Build.hasSmartBar()
            Method method = Class.forName("android.os.Build").getMethod(
                    "hasSmartBar");
            return ((Boolean) method.invoke(null)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 获取图片大小
    public static float getSizeOfBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        long length = baos.toByteArray().length / 1024;
        return length;
    }

    private static InputStream Bitmap2IS(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream stream = null;
        try {
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            stream = new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream;
    }

    public static Bitmap readBitmap(Context context, int resId) {
        InputStream is = null;
        try {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            // 获取资源图片
            is = context.getResources().openRawResource(resId);
            return BitmapFactory.decodeStream(is, null, opt);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getValueByName(String url, String key) {
        String result = "";
        int index = url.indexOf("?");
        String temp = url.substring(index + 1);
        String[] keyValue = temp.split("&");
        for (String str : keyValue) {
            if (str.contains(key)) {
                result = str.replace(key + "=", "");
                break;
            }
        }
        return result;
    }

    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();
        int result[] = {width, height};
        return result;
    }

    public static String getDeviceBrand() {
        return getDeviceBrand_private();
    }

    /**
     * @return
     * @brief 获取手机品牌
     */
    private static String getDeviceBrand_private() {
        String brand2 = null;
        String brand = Build.BRAND;
        if (null != brand) {
            brand = brand.replace(' ', '-');
            if (brand.length() > 8) {
                brand2 = brand.substring(0, 8);
            } else if (brand.length() < 8) {
                brand = brand + "abcdefgh";
                if (brand.length() > 8) {
                    brand2 = brand.substring(0, 8);
                }
            } else if (brand.length() == 8) {
                brand2 = brand;
            }
        } else {
            brand2 = "abcdefgh";
        }
        return brand2;
    }

    // 获取本地保存图片
    public static Bitmap readImages(String imagePath) {
        if (imagePath == null || imagePath.equals("")) {
            return null;
        }
//        String name = parseImgUrl(imagePath);
//        imagePath = AppConfig.SAVE_IMAGE_DIRPATH + name;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(imagePath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    // 解析图片路径
    public static String parseImgUrl(String imgurl) {
        String imgname = "";
        int n = 0;
        if (imgurl != null && !imgurl.equals("")) {
            if (imgurl.contains("/")) {
                for (int i = 0; i < imgurl.length(); i++) {
                    char m = imgurl.charAt(i);
                    String m1 = String.valueOf(m);
                    if (m1 != null && !m1.equals("") && m1.equals("/")) {
                        n = i;
                    }
                }
                imgname = imgurl.substring(n + 1, imgurl.length());
            } else {
                imgname = imgurl;
            }
        }
        return imgname;
    }

    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /*
     * @brief 微信是否安装
     */
    public static boolean isInstallApp(Context context, String packageName) {
        boolean installed = false;
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(packageName, 0);
            if (info != null) {
                installed = true;
            }
        } catch (Exception e) {
            installed = false;
        }
        return installed;
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 压缩图片到指定大小以下
    public static Bitmap compressBitmap(Bitmap bitmap, int size) {
        Bitmap newBitmap = null;
        if (bitmap != null) {
            InputStream is = null;
            try {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inDither = false;
                opt.inPreferredConfig = Bitmap.Config.RGB_565;
                opt.inSampleSize = 1;
                float bitmapSize = Utils.getSizeOfBitmap(bitmap);
                // 压缩图片到指定大小
                while (bitmapSize > (size + size / 3)) {
                    opt.inSampleSize = opt.inSampleSize + 1;
                    is = Bitmap2IS(bitmap);
                    if (is != null) {
                        bitmap = BitmapFactory.decodeStream(is, null, opt);
                        bitmapSize = Utils.getSizeOfBitmap(bitmap);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (RuntimeException rethrown) {
                        throw rethrown;
                    } catch (Exception ignored) {
                    }
                }
            }
            newBitmap = bitmap;
        }
        return newBitmap;
    }


    /**
     * 获取该context对应的app中的所有activity对象
     *
     * @param context
     * @return
     */
    public static ActivityInfo[] getAppActivities(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                return packageInfo.activities;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 判断当前进程是否是主进程
     *
     * @param context
     * @return
     */
    public static boolean isMainProcess(Context context) {
        String processName = getProcessName(context);
        return processName != null && processName.equals(context.getPackageName());
    }

    /*
     * @brief 是否安装
     */
    public static boolean isInstallAPK(Context context, String packageName) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (info != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isRoot() {
        boolean boolRoot = false;
        try {
            boolRoot = !((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boolRoot;
    }

    /**
     * @param context
     * @brief 隐藏statusbar
     */
    public static void hideStatusBar(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = context.getWindow().getAttributes();
            layoutParams.flags = (WindowManager.LayoutParams.FLAG_FULLSCREEN | layoutParams.flags);
        }
    }

    /**
     * @param context
     * @param key
     * @return
     * @throws PackageManager.NameNotFoundException
     * @brief 获取MetaData
     */
    public static String getMetaData(Context context, String key) throws PackageManager.NameNotFoundException {
        ApplicationInfo appInfo = context.getPackageManager()
                .getApplicationInfo(context.getPackageName(),
                        PackageManager.GET_META_DATA);
        if (appInfo.metaData.get(key) != null) {
            String value = appInfo.metaData.get(key).toString();
            return value;
        }
        return null;
    }



    // 生成指定长度随机数字字符串
    public static String getRandom(int length) {
        String radString = null;
        int[] number = new int[length];
        Random random = new Random();
        number[0] = Math.abs(random.nextInt()) % 10;
        radString = String.valueOf(number[0]);
        for (int i = 1; i < length; i++) {
            number[i] = Math.abs(random.nextInt()) % 10;
            radString = radString + String.valueOf(number[i]);
        }
        return radString;
    }

    /**
     * @return
     * @brief 判断某一个类是否存在任务栈里面
     */
    public static boolean isExistMainActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        ComponentName cmpName = intent.resolveActivity(context
                .getPackageManager());
        boolean flag = false;
        if (cmpName != null) {
            ActivityManager am = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.getPackageName().equals(cmpName.getPackageName())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static JSONObject sendResultTrue() throws JSONException {
        return sendResult("true");
    }

    public static JSONObject sendResult(String value) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("result", value);
        return data;
    }

    public static void hideDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public static int getBitmapHeight(Resources res, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //这里必须采用传入options的方法
        BitmapFactory.decodeResource(res, resId, options);
        int height = options.outHeight;
        return height;
    }

    /**
     * @param activity
     * @param waterData
     * @param addwater
     * @return
     * @brief 截屏
     */
/*    public static String screenShot(Activity activity, JSONObject waterData, boolean addwater) {
        // 获取屏幕
        View dView = activity.getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        if (bmp != null) {
            try {
                bmp = Utils.compressBitmap(bmp, 300 - 20);
                if (bmp != null && waterData != null && addwater) {
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setColor(Color.WHITE);
                    // 添加水印
                    bmp = Utils.createWaterMaskTextToBitmap(activity, bmp, "", 15,
                            paint, 5, 0, 0, true, waterData);
                }
                // 向后台传递识别数据
                return Utils.bitmap2String(bmp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }*/
}
