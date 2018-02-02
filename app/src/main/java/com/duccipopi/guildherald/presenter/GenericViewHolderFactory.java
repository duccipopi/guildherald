package com.duccipopi.guildherald.presenter;

import android.view.View;

/**
 * Created by ducci on 01/02/2018.
 */

public interface GenericViewHolderFactory<T extends GenericViewHolder> {

    T create(View itemView);
}
