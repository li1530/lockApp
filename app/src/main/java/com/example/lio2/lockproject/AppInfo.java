package com.example.lio2.lockproject;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Application package information
 */
class AppInfo implements IAppInfo{
    private String appname;
    private String pname;

    private static AppInfo mInstance;

    public static AppInfo getInstance(){
        if (mInstance == null){
            mInstance = new AppInfo();
        }
        return mInstance;
    }


    public AppInfo getInfoFromPackageName(String pkgName,
                                          Context mContext) {
        AppInfo newInfo = new AppInfo();
        try {
            PackageInfo p = mContext.getPackageManager().getPackageInfo(
                    pkgName, PackageManager.GET_PERMISSIONS);

            newInfo.appname = p.applicationInfo.loadLabel(
                    mContext.getPackageManager()).toString();
            newInfo.pname = p.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return newInfo;
    }

    public String getAppName(){
        return appname;
    }

    public void setAppName(String applicationName){
        appname = applicationName;
    }
}