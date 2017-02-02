package com.saltside.test.ui.launch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.saltside.test.R;
import com.saltside.test.ui.BaseActivity;
import com.saltside.test.ui.home.HomeActivity;
import com.saltside.test.utils.Constants;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spalsh);
        setScreenTimeOut();
    }

    /**
     * Handler thread to launch Home screen once a specified time is passed.
     */
    private void setScreenTimeOut() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                launchHome();
            }
        }, Constants.SplashScreen.TIME_OUT);
    }

    private void launchHome() {
        Intent homeIntent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
