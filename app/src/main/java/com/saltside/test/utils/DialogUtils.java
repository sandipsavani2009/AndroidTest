package com.saltside.test.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.saltside.test.R;

/**
 * Created by sandip on 1/2/17.
 */

public class DialogUtils {

    private static ProgressDialog mProgressDialog;

    public static synchronized void showProgressDialog(Context context) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(context, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
            }
            if (context != null && !mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void cancelProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A common dialog for internet connection message
     * @param context - callers context
     */
    public static synchronized void showNoInternetDialog(Context context) {
        SaltSideAlertDialog.showAlert(context, context.getString(R.string.title),
                context.getString(R.string.no_internet),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }

    /**
     * A common dialog for anything went wrong for IO
     * @param context - callers context
     */
    public static synchronized void showNetworkFailureDialog(Context context) {
        SaltSideAlertDialog.showAlert(context, context.getString(R.string.title),
                context.getString(R.string.network_failure_msg),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }

    /**
     * A common error message for unknown things
     * @param context - callers context
     */
    public static synchronized void showUnknownErrorDialog(Context context) {
        SaltSideAlertDialog.showAlert(context, context.getString(R.string.title),
                context.getString(R.string.common_unknown_error_dialog),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }
}
