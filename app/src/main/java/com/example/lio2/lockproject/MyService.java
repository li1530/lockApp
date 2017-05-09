package com.example.lio2.lockproject;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lio2 on 4/26/2017.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showDialog(){
        Intent dialogIntent = new Intent(this, MyDialog.class);
        //dialogIntent.setAction(Intent.ACTION_VIEW);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//FLAG_ACTIVITY_NEW_TASK
        //dialogIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(dialogIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                ActivityManager activityManager = (ActivityManager) getApplication().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningAppProcessInfo> appProcesses= activityManager.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                    try {
                        if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                            //if (!lastFrontAppPkg.equals((String) appProcess.pkgList[0])) {
                                ApkInfo apkInfo = ApkInfo.getInfoFromPackageName(appProcess.pkgList[0], getApplication().getApplicationContext());
                            if(apkInfo.appname.equals("YouTube")){
                                showDialog();
                                activityManager.killBackgroundProcesses(apkInfo.pname);
                            }
//                                if (apkInfo == null || (apkInfo.getP().applicationInfo.flags && ApplicationInfo.FLAG_SYSTEM) == 1) {
//                                    // System app                                             continue;
//                                } else if (((apkInfo.getP().versionName == null)) || (apkInfo.getP().requestedPermissions == null)) {
//                                    //Application that comes preloaded with the device
//                                    continue;
//                                } else {
//                                    lastFrontAppPkg = (String) appProcess.pkgList[0];
//                                }
                                //kill the app
                                //Here do the pupop with password to launch the lastFrontAppPkg if the pass is correct
                            //}
                        }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }, 0, 1000);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }



}


class ApkInfo {
    String appname;
    String pname;
    ApkInfo() {
    }


    public static ApkInfo getInfoFromPackageName(String pkgName,
                                                 Context mContext) {
        ApkInfo newInfo = new ApkInfo();
        try {
            PackageInfo p = mContext.getPackageManager().getPackageInfo(
                    pkgName, PackageManager.GET_PERMISSIONS);

            newInfo.appname = p.applicationInfo.loadLabel(
                    mContext.getPackageManager()).toString();
            newInfo.pname = p.packageName;
//            newInfo.versionName = p.versionName;
//            newInfo.versionCode = p.versionCode;
//            newInfo.icon = p.applicationInfo.loadIcon(mContext
//                    .getPackageManager());
//            newInfo.setP(p);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return newInfo;
    }
}
