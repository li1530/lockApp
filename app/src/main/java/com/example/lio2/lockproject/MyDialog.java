package com.example.lio2.lockproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

/**
 * Created by lio2 on 4/26/2017.
 */

public class MyDialog extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Time to Study!");
        alertDialog.setMessage("Be Productive");
        alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();
    }
}
