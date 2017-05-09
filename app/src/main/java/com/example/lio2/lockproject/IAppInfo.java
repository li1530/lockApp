package com.example.lio2.lockproject;

import android.content.Context;

/**
 * Created by lio2 on 5/3/2017.
 */

public interface IAppInfo {
    AppInfo getInfoFromPackageName(String pkgName,
                                   Context mContext);

    String getAppName();

    void setAppName(String applicationName);
}
