
package com.saltside.test.ui.customViews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Commonly used custom Views can be more beneficial for single point changes in future.
 */
public class TestTextView extends TextView {

    public TestTextView(Context context) {
        super(context);
        init(context);
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
        init(context);
    }

    private void init(Context context) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Regular.ttf");
        this.setTypeface(type);
    }


}
