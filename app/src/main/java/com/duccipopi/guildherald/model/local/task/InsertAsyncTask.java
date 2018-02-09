package com.duccipopi.guildherald.model.local.task;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import com.duccipopi.guildherald.model.base.ContentValuesCreator;

/**
 * Created by ducci on 29/01/2018.
 */

public class InsertAsyncTask<T> extends AsyncTask<T, Void, Void> {

    private final ContentResolver mContentResolver;
    private final ContentValuesCreator<T> mCreator;
    private final Uri mUri;

    public InsertAsyncTask(ContentResolver contentResolver, ContentValuesCreator<T> creator, Uri uri) {
        mContentResolver = contentResolver;
        mCreator = creator;
        mUri = uri;
    }

    @Override
    // Will process all URI, but will return only the result of last one
    protected Void doInBackground(T... items) {
        for (T item : items) {
            ContentValues cv = mCreator.createFrom(item);
            mContentResolver.insert(mUri, cv);
        }

        return null;
    }

}
