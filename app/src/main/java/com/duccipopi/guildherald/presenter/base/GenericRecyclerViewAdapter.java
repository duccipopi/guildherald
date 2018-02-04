package com.duccipopi.guildherald.presenter.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ducci on 31/01/2018.
 */

public class GenericRecyclerViewAdapter<T> extends RecyclerView.Adapter<GenericViewHolder<T>> {

    private List<T> mItems;
    private GenericViewHolderFactory<? extends GenericViewHolder<T>> mViewHolderFactory;
    private int mItemLayout;
    private View.OnClickListener mOnClickListener;

    public GenericRecyclerViewAdapter(@NonNull List<T> items,
                                      @NonNull GenericViewHolderFactory<? extends GenericViewHolder<T>> viewHolderFactory,
                                      int itemLayout,
                                      @Nullable View.OnClickListener onClickListener) {
        mItems = items;
        mViewHolderFactory = viewHolderFactory;
        mItemLayout = itemLayout;
        mOnClickListener = onClickListener;
    }

    @Override
    public GenericViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        return mViewHolderFactory.create(view);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder<T> holder, int position) {
        holder.bind(mItems.get(position));
        holder.itemView.setTag(mItems.get(position));

        if (mOnClickListener != null) {
            holder.itemView.setOnClickListener(mOnClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
