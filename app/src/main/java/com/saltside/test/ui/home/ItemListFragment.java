package com.saltside.test.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saltside.test.R;
import com.saltside.test.SaltSideApplication;
import com.saltside.test.network.NetworkResponseListener;
import com.saltside.test.network.WebServiceController;
import com.saltside.test.network.model.Item;
import com.saltside.test.ui.BaseFragment;
import com.saltside.test.ui.adapters.ListItemRecyclerAdapter;
import com.saltside.test.ui.listeners.ActivityActionListener;
import com.saltside.test.ui.listeners.ListItemListener;
import com.saltside.test.utils.Constants;
import com.saltside.test.utils.DialogUtils;
import com.saltside.test.utils.Logger;
import com.saltside.test.utils.NetworkUtils;

import java.util.List;

public class ItemListFragment extends BaseFragment implements ListItemListener {

    private static final String TAG = ItemListFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ActivityActionListener mActionListener;

    public ItemListFragment() {
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
    public void onResume() {
        super.onResume();
        changeScreenTitle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        downloadDataFile();
    }

    private void changeScreenTitle() {
        if (mActionListener != null) {
            mActionListener.setScreenTitle(getString(R.string.list));
        }
    }

    private void initViews(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                changeScreenTitle();
            }
        });
    }


    private void downloadDataFile() {
        if (NetworkUtils.isInternetOn(getActivity())) {

            DialogUtils.showProgressDialog(getActivity());
            WebServiceController.getInstance().downloadJsonDataFile(new NetworkResponseListener() {
                @Override
                public void onSuccess(int statusCode, Object result) {
                    DialogUtils.cancelProgressDialog();
                    onSuccessResponse(statusCode, result);
                }

                @Override
                public void onFailure(int statusCode, Object errResult) {
                    DialogUtils.cancelProgressDialog();

                    Logger.d(TAG, "Failure status [Server Response]");
                    DialogUtils.showNetworkFailureDialog(getActivity());
                }
            });

        } else {
            DialogUtils.showNoInternetDialog(getActivity());
        }
    }

    private void onSuccessResponse(int statusCode, Object result) {
        if (statusCode == Constants.NetworkCodes.NETWORK_SUCCESS && result != null) {
            /* Save file into memory to get content later stage
            * This is not used currently at any state.
            * */
            SaltSideApplication.getStateHolder().setServerJsonFile((String) result);

            showDetailAsList((String) result);
        } else {
            Logger.d(TAG, "Not Success status [Server Response]");
        }
    }

    private void showDetailAsList(String resultFile) {
        if (!TextUtils.isEmpty(resultFile)) {
            List<Item> itemList = getItemListFromString(resultFile);

            if (itemList != null && !itemList.isEmpty()) {
                ListItemRecyclerAdapter adapter = new ListItemRecyclerAdapter(getActivity(), itemList, this);
                if (mRecyclerView != null && getActivity() != null) {
                    mRecyclerView.setAdapter(adapter);
                }
            }
        } else {
            DialogUtils.showUnknownErrorDialog(getActivity());
        }

    }

    private List<Item> getItemListFromString(String resultFile) {
        List<Item> itemList = null;
        try {
            itemList = new Gson().fromJson(resultFile, new TypeToken<List<Item>>() {
            }.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return itemList;
    }

    @Override
    public void onListItemClicked(int position, Item item) {
        if (mActionListener != null) {
            Bundle args = new Bundle();
            args.putParcelable(Constants.Args.SELECTED_ITEM, item);
            mActionListener.launchItemDetails(args);
        }
    }

}
