package com.saltside.test.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saltside.test.R;
import com.saltside.test.network.model.Item;
import com.saltside.test.ui.listeners.ListItemListener;

import java.util.List;

/**
 * List data adapter for data binding & UI item management.
 */
public class ListItemRecyclerAdapter extends RecyclerView.Adapter<ListItemRecyclerAdapter.ListItemViewHolder> {

    private Context mContext;
    private List<Item> mItemList;
    private ListItemListener mListItemListener;

    public ListItemRecyclerAdapter(Context context,
                                   List<Item> itemList,
                                   ListItemListener listItemListener) {
        mContext = context;
        mItemList = itemList;
        mListItemListener = listItemListener;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ListItemViewHolder viewHolder = new ListItemViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        if (mItemList != null && mItemList.size() > position && holder != null) {
            Item item = mItemList.get(position);
            holder.titleTextView.setText(item.getTitle());
            holder.descriptionTextView.setText(item.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return  (mItemList != null) ? mItemList.size() : 0;
    }

    /**
     * Recycler ViewHolder class
     */
    public class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView descriptionTextView;
        private CardView cardView;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            if (itemView != null) {
                titleTextView = (TextView) itemView.findViewById(R.id.item_title_textView);
                descriptionTextView = (TextView) itemView.findViewById(R.id.item_description_textView);
                cardView = (CardView) itemView.findViewById(R.id.cardItem);
                cardView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cardItem:
                    onCardViewClicked();
                    break;
            }
        }

        private void onCardViewClicked() {
            if (mListItemListener != null) {
                int pos = getAdapterPosition();
                if (pos < mItemList.size()) {
                    mListItemListener.onListItemClicked(pos, mItemList.get(pos));
                }
            }
        }
    }

}
