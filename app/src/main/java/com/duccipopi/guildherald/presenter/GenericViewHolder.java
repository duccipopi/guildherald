package com.duccipopi.guildherald.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ducci on 31/01/2018.
 */

public abstract class GenericViewHolder<T> extends RecyclerView.ViewHolder {

    public GenericViewHolder(View itemView) {
        super(itemView);
    }

    public abstract GenericViewHolder<T> newInstance(ViewGroup parent);
    public abstract void bind(T item);
}
