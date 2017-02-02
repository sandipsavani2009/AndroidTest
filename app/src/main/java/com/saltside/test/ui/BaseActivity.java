package com.saltside.test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.saltside.test.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
    }

    protected void setLayout(int layoutId) {
        FrameLayout activityContainer = (FrameLayout) findViewById(R.id.activityContainer);
        activityContainer.addView(getLayoutInflater().inflate(layoutId, null));
    }

    protected void setPageTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            TextView titleTextView = (TextView) findViewById(R.id.screen_title_textView);
            titleTextView.setText(title);
        }
    }
}
