package com.duccipopi.guildherald.model.local.task;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.duccipopi.guildherald.model.implementation.CursorConverter;
import com.duccipopi.guildherald.model.implementation.HeraldCallback;

/**
 * Created by ducci on 29/01/2018.
 */

public class QueryAsyncTask<T> extends AsyncTask<Uri, Void, T> {

    private final ContentResolver mContentResolver;
    private final CursorConverter<T> mConverter;
    private final HeraldCallback<T> mCallback;

    public QueryAsyncTask(ContentResolver contentResolver, CursorConverter<T> converter, HeraldCallback<T> callback) {
        mContentResolver = contentResolver;
        mConverter = converter;
        mCallback = callback;
    }

    @Override
    // Will process all URI, but will return only the result of last one
    protected T doInBackground(Uri... uris) {
        Cursor cursor = null;
        for (Uri uri : uris) {
            cursor = mContentResolver.query(uri, null, null, null, null);
        }

        return mConverter.convert(cursor);
    }

    @Override
    protected void onPostExecute(T t) {

        if (mCallback != null) {
            mCallback.onResponse(t);
        }

    }
}
