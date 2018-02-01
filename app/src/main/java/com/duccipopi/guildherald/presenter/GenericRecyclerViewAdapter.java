package com.duccipopi.guildherald.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ducci on 31/01/2018.
 */

public class GenericRecyclerViewAdapter<T> extends RecyclerView.Adapter<GenericViewHolder<T>> {

    private GenericViewHolder<T> mViewHolder;
    private List<T> mItems;

    public GenericRecyclerViewAdapter(@NonNull List<T> items, @NonNull GenericViewHolder viewHolder) {
        mItems = items;
        mViewHolder = viewHolder;
    }

    @Override
    public GenericViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return mViewHolder.newInstance(parent);
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
