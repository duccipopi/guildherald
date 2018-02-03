package com.duccipopi.guildherald.presenter.base;

import android.support.annotation.NonNull;
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

    public GenericRecyclerViewAdapter(@NonNull List<T> items,
                                      @NonNull GenericViewHolderFactory<? extends GenericViewHolder<T>> viewHolderFactory,
                                      int itemLayout) {
        mItems = items;
        mViewHolderFactory = viewHolderFactory;
        mItemLayout = itemLayout;
    }

    @Override
    public GenericViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        return mViewHolderFactory.create(view);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder<T> holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
