package com.duccipopi.guildherald.model.implementation;

import android.database.Cursor;

import com.duccipopi.guildherald.model.dao.Guild;

import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.GuildEntry.COLUMN_ACHIEVEMENTS;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.GuildEntry.COLUMN_FACTION;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.GuildEntry.COLUMN_MEMBERS;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.GuildEntry.COLUMN_NAME;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.GuildEntry.COLUMN_REALM;

/**
 * Created by ducci on 29/01/2018.
 */

public class GuildConverter implements CursorConverter<Guild> {
    @Override
    public Guild convert(Cursor cursor) {

        if (cursor == null) return null;

        return new Guild(
                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_REALM)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_FACTION)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_MEMBERS)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_ACHIEVEMENTS)),
                null

        );
    }
}
