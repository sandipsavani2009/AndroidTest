package com.saltside.test.ui.home;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.saltside.test.R;
import com.saltside.test.network.NetworkResponseListener;
import com.saltside.test.network.WebServiceController;
import com.saltside.test.network.model.Item;
import com.saltside.test.ui.BaseFragment;
import com.saltside.test.ui.listeners.ActivityActionListener;
import com.saltside.test.utils.Constants;
import com.saltside.test.utils.DialogUtils;
import com.saltside.test.utils.Logger;
import com.saltside.test.utils.NetworkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailsFragment extends BaseFragment {

    private static final String TAG = ItemDetailsFragment.class.getSimpleName();
    private ActivityActionListener mActionListener;

    public ItemDetailsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActivityActionListener) {
            mActionListener = (ActivityActionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ActivityActionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  rootView = inflater.inflate(R.layout.fragment_item_details, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void downloadImage(String url) {
        if (NetworkUtils.isInternetOn(getActivity())) {

            WebServiceController.getInstance().downloadImage(url,
                    new NetworkResponseListener() {
                        @Override
                        public void onSuccess(int statusCode, Object result) {
                            DialogUtils.cancelProgressDialog();
                            onImageDownloaded(statusCode, (Bitmap) result);
                        }

                        @Override
                        public void onFailure(int statusCode, Object errResult) {
                            Logger.d(TAG, "Failure status [Server Response]");
                            DialogUtils.showNetworkFailureDialog(getActivity());
                        }
                    });

        } else {
            DialogUtils.showNoInternetDialog(getActivity());
        }
    }

    private void onImageDownloaded(int statusCode, Bitmap bmpImage) {
        if (statusCode == Constants.NetworkCodes.NETWORK_SUCCESS &&  bmpImage != null &&
                getActivity() != null && getView() != null) {
            ImageView imageView = (ImageView) getView().findViewById(R.id.details_imageView);
            imageView.setImageBitmap(bmpImage);
            imageView.setVisibility(View.VISIBLE);

            ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.imageProgressBar);
            progressBar.setVisibility(View.GONE);
        } else {
            DialogUtils.showUnknownErrorDialog(getActivity());
        }
    }

    private void init() {
        Bundle args = getArguments();
        if (args != null) {
            Item selectedItem = args.getParcelable(Constants.Args.SELECTED_ITEM);
            View rootView = getView();

            if (selectedItem != null && rootView != null) {
                TextView titleTextView = (TextView) rootView.findViewById(R.id.details_title_textView);
                TextView descTextView = (TextView) rootView.findViewById(R.id.details_description_textView);

                titleTextView.setText(selectedItem.getTitle());
                descTextView.setText(selectedItem.getDescription());
                String url = selectedItem.getImageUrl();
                if (!TextUtils.isEmpty(url)) {
                    downloadImage(url);
                }
            }
        }
    }
}
