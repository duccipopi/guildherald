package com.duccipopi.guildherald.presenter.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ducci on 31/01/2018.
 */

public abstract class GenericViewHolder<T> extends RecyclerView.ViewHolder {

    public GenericViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T item);

}
