package com.example.lio2.lockproject;

/**
 * Created by lio2 on 5/3/2017.
 */

public interface ILockLogicView {

    void displayWarning();

    void hideWarning();

    void startService();

    void stopService();

    String getAppInfo();

    void showHomeView();
}
