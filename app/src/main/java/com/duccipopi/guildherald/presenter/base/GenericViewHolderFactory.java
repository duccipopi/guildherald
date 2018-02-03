package com.duccipopi.guildherald.presenter.base;

import android.view.View;

/**
 * Created by ducci on 01/02/2018.
 */

public interface GenericViewHolderFactory<T extends GenericViewHolder> {

    T create(View itemView);
}
