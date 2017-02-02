package com.saltside.test.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.saltside.test.R;

/**
 * Created by sandip on 1/2/17.
 *
 * A single access point for all types of dialogs
 */

public class SaltSideAlertDialog {

    /* Need to maintain single instance as at a time only once alert can be present logically */
    private static AlertDialog mAlertDialog;

    private static synchronized void showDialogue(Context context,
                                                  @NonNull String title,
                                                  @NonNull String message,
                                                  DialogInterface.OnClickListener positiveClickListener,
                                                  DialogInterface.OnClickListener negativeClickListener) {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(context.getString(R.string.ok), positiveClickListener)
                    .setNegativeButton(context.getString(R.string.cancel), negativeClickListener).create();
        }
        if (context != null && mAlertDialog != null && !mAlertDialog.isShowing()) {
            mAlertDialog.show();
        }
    }

    /**
     *
     * @param context - Caller context
     * @param title - Alert Title
     * @param message - Alert message
     * @param onClickListener - ok Click callback
     */
    public static void showAlert(Context context,
                                 @NonNull String title,
                                 @NonNull String message,
                                 DialogInterface.OnClickListener onClickListener) {

        showDialogue(context, title, message, onClickListener, null);
    }

    /**
     *
     * @param context - Caller context
     * @param title - Alert title
     * @param message - Alert message
     * @param positiveListener - ok click callback
     * @param negativeListener - cancel click callback
     */
    public static void showAlert(Context context,
                                 @NonNull String title,
                                 @NonNull String message,
                                 DialogInterface.OnClickListener positiveListener,
                                 DialogInterface.OnClickListener negativeListener) {
        showDialogue(context, title, message, positiveListener, negativeListener);
    }

}
