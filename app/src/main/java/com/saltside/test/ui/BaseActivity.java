package com.saltside.test.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.saltside.test.R;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
