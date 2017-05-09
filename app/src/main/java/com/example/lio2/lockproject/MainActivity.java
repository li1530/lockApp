package com.example.lio2.lockproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ILockLogicView{

    Button startServiceBtn;
    Button stopServiceBtn;
    ILockPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new LockPresenter(this, new LockLogicModel());
        startServiceBtn = (Button)findViewById(R.id.startbutton);
        stopServiceBtn = (Button)findViewById(R.id.stopButton);
        initButtonListener();
    }

    private void initButtonListener(){
        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startService();
            }
        });

        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.stopService();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayWarning() {
        //No implementation
    }

    @Override
    public void hideWarning() {
        //No implementation
    }

    @Override
    public void startService() {
        startService(new Intent(getBaseContext(), BlockService.class));
    }

    @Override
    public void stopService() {
        stopService(new Intent(getBaseContext(), BlockService.class));
    }

    @Override
    public String getAppInfo() {
        return null;
    }

    @Override
    public void showHomeView() {

    }
}
