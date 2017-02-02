package com.saltside.test.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

import com.saltside.test.R;

/**
 * Created by sandip on 1/2/17.
 */

public class DialogUtils {

    private static ProgressDialog sProgressDialog;

    public static synchronized void showProgressDialog(Activity activity) {
        if (sProgressDialog != null && sProgressDialog.isShowing())
            return;

        if (activity != null) {
            sProgressDialog = new ProgressDialog(activity);
            sProgressDialog.setMessage(activity.getString(R.string.loading));
            sProgressDialog.setCanceledOnTouchOutside(false);
            sProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            sProgressDialog.setCancelable(false);
            try {
                sProgressDialog.show();
            } catch (WindowManager.BadTokenException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void cancelProgressDialog() {
        try {
            if (sProgressDialog != null && sProgressDialog.isShowing()) {
                sProgressDialog.dismiss();
                sProgressDialog = null;
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
