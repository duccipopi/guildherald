package com.duccipopi.guildherald.model.local.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ducci on 28/01/2018.
 */

public class HeraldDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "herald.db";
    private static final int DATABASE_VERSION = 1;

    // Character table creation
    private static final String SQL_CREATE_CHARACTER_TABLE = "CREATE TABLE" +
            HeraldProviderContract.CharacterEntry.TABLE_NAME + " (" +
            HeraldProviderContract.CharacterEntry.COLUMN_NAME + " TEXT PRIMARY KEY, " +
            HeraldProviderContract.CharacterEntry.COLUMN_REALM + " TEXT PRIMARY KEY, " +
            HeraldProviderContract.CharacterEntry.COLUMN_CLASS + " INTEGER NOT NULL, " +
            HeraldProviderContract.CharacterEntry.COLUMN_GENDER + " INTEGER NOT NULL, " +
            HeraldProviderContract.CharacterEntry.COLUMN_LEVEL + " INTEGER NOT NULL, " +
            HeraldProviderContract.CharacterEntry.COLUMN_FACTION + " INTEGER NOT NULL, " +
            HeraldProviderContract.CharacterEntry.COLUMN_ACHIEVEMENTS + " INTEGER NTO NULL, " +
            HeraldProviderContract.CharacterEntry.COLUMN_HONORABLE_KILLS + " INTEGER NOT NULL, " +
            HeraldProviderContract.CharacterEntry.COLUMN_GUILD + " INTEGER FOREIGN KEY, " +
            HeraldProviderContract.CharacterEntry.COLUMN_THUMBNAIL + " TEXT);";

    // Guild table creation
    private static final String SQL_CREATE_GUILD_TABLE = "CREATE TABLE" +
            HeraldProviderContract.GuildEntry.TABLE_NAME + " (" +
            HeraldProviderContract.GuildEntry.COLUMN_NAME + " TEXT PRIMARY KEY, " +
            HeraldProviderContract.GuildEntry.COLUMN_REALM + " TEXT PRIMARY KEY, " +
            HeraldProviderContract.GuildEntry.COLUMN_FACTION + " INTEGER NOT NULL, " +
            HeraldProviderContract.GuildEntry.COLUMN_ACHIEVEMENTS + " INTEGER NTO NULL, " +
            HeraldProviderContract.GuildEntry.COLUMN_EMBLEM + " TEXT);";


    public HeraldDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CHARACTER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GUILD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Do nothing, first db version
    }
}
