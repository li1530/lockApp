package com.example.lio2.lockproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

/**
 * Warning dialog to show the user to study. lol
 */
public class WarningDialog extends Activity implements ILockLogicView{

    private AlertDialog mAlertDialog;
    private boolean mIsPause = false;
    private ILockPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LockPresenter(this, new LockLogicModel());
        mAlertDialog = new AlertDialog.Builder(this).create();
        mAlertDialog.setTitle(getResources().getString(R.string.dialog_title));
        mAlertDialog.setMessage(getResources().getString(R.string.dialog_message));
        mAlertDialog.setIcon(R.drawable.icon);
        mAlertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAlertDialog.show();
    }
    @Override
    public void onPause(){
        super.onPause();
        mIsPause = true;

    }

    @Override
    public void onResume(){
        super.onResume();
        if(mIsPause) {
            presenter.hideDialog();
        }
    }

    @Override
    public void displayWarning() {
        mAlertDialog.show();
    }

    @Override
    public void hideWarning() {
        mAlertDialog.hide();
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
        return null;
    }

    @Override
    public void showHomeView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
