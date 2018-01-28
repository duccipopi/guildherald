package com.duccipopi.guildherald.model.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.List;

public class HeraldContentProvider extends ContentProvider {
    private SQLiteOpenHelper mDBHelper;

    public HeraldContentProvider() {
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new HeraldDBHelper(getContext());

        return true;
    }

    @Override
    public String getType(Uri uri) {

        switch (UriMatcherHelper.match(uri)) {
            case UriMatcherHelper.CHARACTER_DIRECTORY:
                return HeraldProviderContract.MIME_CHARACTER_DIR;
            case UriMatcherHelper.CHARACTER_ITEM:
                return HeraldProviderContract.MIME_CHARACTER_ITEM;
            case UriMatcherHelper.GUILD_DIRECTORY:
                return HeraldProviderContract.MIME_GUILD_DIR;
            case UriMatcherHelper.GUILD_ITEM:
                return HeraldProviderContract.MIME_GUILD_ITEM;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName;
        Uri contentUri;
        String realm, name;

        // Accept only directory URI
        switch (UriMatcherHelper.match(uri)) {
            case UriMatcherHelper.CHARACTER_DIRECTORY:
                tableName = HeraldProviderContract.CharacterEntry.TABLE_NAME;
                contentUri = HeraldProviderContract.CharacterEntry.CONTENT_URI;
                realm = HeraldProviderContract.CharacterEntry.COLUMN_REALM;
                name = HeraldProviderContract.CharacterEntry.COLUMN_NAME;
                break;

            case UriMatcherHelper.GUILD_DIRECTORY:
                tableName = HeraldProviderContract.GuildEntry.TABLE_NAME;
                contentUri = HeraldProviderContract.GuildEntry.CONTENT_URI;
                realm = HeraldProviderContract.CharacterEntry.COLUMN_REALM;
                name = HeraldProviderContract.CharacterEntry.COLUMN_NAME;
                break;

            default:
                throw new UnsupportedOperationException("Invalid URI");
        }

        db.insert(tableName, null, values);

        // URI do not use ID, use pattern "AUTHORITY/TABLE/REALM/NAME"
        contentUri = contentUri.buildUpon()
                .appendPath(values.getAsString(realm))
                .appendPath(values.getAsString(name))
                .build();

        getContext().getContentResolver().notifyChange(contentUri, null);

        return contentUri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String table;
        String realm, name;

        // Accept directory and item URI
        switch (UriMatcherHelper.match(uri)) {
            case UriMatcherHelper.CHARACTER_DIRECTORY:
            case UriMatcherHelper.CHARACTER_ITEM:
                table = HeraldProviderContract.CharacterEntry.TABLE_NAME;
                realm = HeraldProviderContract.CharacterEntry.COLUMN_REALM;
                name = HeraldProviderContract.CharacterEntry.COLUMN_NAME;
                break;

            case UriMatcherHelper.GUILD_DIRECTORY:
            case UriMatcherHelper.GUILD_ITEM:
                table = HeraldProviderContract.GuildEntry.TABLE_NAME;
                realm = HeraldProviderContract.GuildEntry.COLUMN_REALM;
                name = HeraldProviderContract.GuildEntry.COLUMN_NAME;
                break;

            default:
                throw new UnsupportedOperationException("Invalid URI");
        }

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        // Selection is always by realm and name
        selection = realm + "=? AND " + name + "=?";

        // Extract selection arguments from URI
        List<String> pathSegments = uri.getPathSegments();
        selectionArgs = new String[] {
                pathSegments.get(pathSegments.size()-2),
                pathSegments.get(pathSegments.size()-1)
        };

        Cursor cursor = db.query(table,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        // TODO: DB cleanup of old information
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
