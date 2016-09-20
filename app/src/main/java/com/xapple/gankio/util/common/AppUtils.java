package com.xapple.gankio.util.common;

import com.xapple.gankio.APP;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * 项目名称：railtool
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：15/11/16 下午10:34
 * 修改人：wengyiming
 * 修改时间：15/11/16 下午10:34
 * 修改备注：
 */
public class AppUtils {

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public static String getVersionName() {
        try {
            PackageManager packageManager = APP.getInstance().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    APP.getInstance().getPackageName(), 0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "unknown version";
    }


    /**
     * @return the version code of the application
     */
    public static int getVersionCode() {
        if (APP.getInstance() == null) {
            return -1;
        }

        int versionCode = -1;
        try {
            PackageManager packageMng = APP.getInstance().getPackageManager();
            if (packageMng != null) {
                PackageInfo packageInfo = packageMng.getPackageInfo(APP.getInstance().getPackageName(), 0);
                if (packageInfo != null) {
                    versionCode = packageInfo.versionCode;
                }
            }
        } catch (NameNotFoundException ignored) {
        }

        return versionCode;
    }

    public static boolean checkVersion(String vc) {
        String localVc = AppUtils.getVersionName();
        if (TextUtils.equals("unknown version", localVc)) {
            return false;
        }
        L.e("localVc" + localVc);
        if (!TextUtils.isEmpty(localVc)) {
            return false;
        }
        if (localVc.contains(".")) {
            localVc = localVc.replace(".", "");
        }
        try {
            int newVcInt = Integer.parseInt(vc);
            int localVcInt = Integer.parseInt(localVc);
            return newVcInt > localVcInt;
        } catch (NumberFormatException ignored) {

        }
        return false;
    }


    /**
     * 获取机器唯一标识
     *
     * @return String
     */
    public static String getLocaldeviceId() {
        TelephonyManager tm = (TelephonyManager) APP.getInstance()
                .getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (deviceId == null
                || deviceId.trim().length() == 0) {
            deviceId = String.valueOf(System
                    .currentTimeMillis());
        }
        return deviceId;
    }

    /**
     * 获取mac地址
     *
     * @return String
     */
    public static String getLocalMacAddress() {
        WifiManager wifi = (WifiManager) APP.getInstance().getSystemService(
                Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }


    /**
     * @return the ANDROID_ID that identify the device, or the "emulator" string
     * on the emulator.
     */
    public static String getAndroidId() {
        String androidId = Settings.Secure.getString(APP.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
        if (androidId == null || androidId.length() <= 0) {
            androidId = "emulator";
        }
        return androidId;
    }


    public static HashMap<String, String> getMap(String key, String value) {
        HashMap<String, String> map = new HashMap<>();
        map.put(key, value);
        L.e(map.toString());
        return map;
    }

    /**
     * 远程图片	http://, https://	HttpURLConnection 或者参考 使用其他网络加载方案
     * 本地文件	file://	FileInputStream
     * Content provider	content://	ContentResolver
     * asset目录下的资源
     *
     * @param res https://github.com/facebook/fresco/issues/257
     * @return Uri
     */
    public static Uri getResFrescoUri(int res) {
        String path = "res:/" + res;
        L.e(path);
        return Uri.parse(path);
    }

    public static Uri getFileFrescoUri(String fileName) {
        String path = SDCardUtils.getSDCardPath() + fileName;
//        L.e(path);
        return Uri.parse("file://" + path);
    }

    public static Uri getContentResolverFrescoUri(String contentResolver) {
        return Uri.parse("content://" + contentResolver);
    }

    public static Uri getassetFrescoUri(int asset) {
        return Uri.parse("asset://" + asset);
    }

    public static String getVideoPath(String fileName) {
        return SDCardUtils.getSDCardPath() + fileName;
    }

}
