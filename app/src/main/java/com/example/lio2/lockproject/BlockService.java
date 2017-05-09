package com.example.lio2.lockproject;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Background service to block the application
 */
public class BlockService extends Service implements ILockLogicView{

    ILockPresenter presenter;
    IAppInfo apkInfo;
    public BlockService(){

        presenter = new LockPresenter(this, new LockLogicModel());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                ActivityManager activityManager = (ActivityManager) getApplication().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningAppProcessInfo> appProcesses= activityManager.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                    try {
                        if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                            apkInfo= AppInfo.getInstance().getInfoFromPackageName(appProcess.pkgList[0], getApplication().getApplicationContext());
                            presenter.displayDialog();
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


    @Override
    public void displayWarning() {
        Intent dialogIntent = new Intent(this, WarningDialog.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//FLAG_ACTIVITY_NEW_TASK
        startActivity(dialogIntent);
    }

    @Override
    public void hideWarning() {
        //No implementation
    }

    @Override
    public void startService() {
        //No implementation
    }

    @Override
    public void stopService() {
        //No implementation
    }

    @Override
    public String getAppInfo() {
        return apkInfo.getAppName();
    }

    @Override
    public void showHomeView() {
        //No implementation
    }
}



