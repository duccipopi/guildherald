package com.duccipopi.guildherald.model.implementation;

import android.database.Cursor;

/**
 * Created by ducci on 29/01/2018.
 */

public interface CursorConverter<T> {

    T convert(Cursor cursor);
}
