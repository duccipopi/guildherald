package com.duccipopi.guildherald.model.base;

import android.content.ContentValues;

/**
 * Created by ducci on 04/02/2018.
 */

public interface ContentValuesCreator<T> {

    ContentValues createFrom(T t);
}
