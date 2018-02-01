package com.duccipopi.guildherald.model.local.provider;

import android.content.UriMatcher;
import android.net.Uri;

/**
 * Created by ducci on 28/01/2018.
 */

class UriMatcherHelper {

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    // Matches ID for Characters queries
    static final int CHARACTER_DIRECTORY = 100;
    static final int CHARACTER_ITEM = 101;

    // Matches ID for Guild queries
    static final int GUILD_DIRECTORY = 200;
    static final int GUILD_ITEM = 201;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // URI for Character queries
        uriMatcher.addURI(HeraldProviderContract.AUTHORITY,
                HeraldProviderContract.PATH_CHARACTERS_DIRECTORY, CHARACTER_DIRECTORY);
        uriMatcher.addURI(HeraldProviderContract.AUTHORITY,
                HeraldProviderContract.PATH_CHARACTERS_ITEM, CHARACTER_ITEM);

        // URI for Guild queries
        uriMatcher.addURI(HeraldProviderContract.AUTHORITY,
                HeraldProviderContract.PATH_GUILD_DIRECTORY, GUILD_DIRECTORY);
        uriMatcher.addURI(HeraldProviderContract.AUTHORITY,
                HeraldProviderContract.PATH_GUILD_ITEM, GUILD_ITEM);

        return uriMatcher;
    }

    static int match(Uri uri) {
        return sUriMatcher.match(uri);
    }
}
