package com.duccipopi.guildherald.model.base;

import android.database.Cursor;

import com.duccipopi.guildherald.model.dao.Character;

import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_ACHIEVEMENTS;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_CLASS;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_FACTION;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_GENDER;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_HONORABLE_KILLS;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_LEVEL;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_NAME;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_RACE;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_REALM;
import static com.duccipopi.guildherald.model.local.provider.HeraldProviderContract.CharacterEntry.COLUMN_THUMBNAIL;

/**
 * Created by ducci on 29/01/2018.
 */

public class CharacterConverter implements CursorConverter<Character> {
    @Override
    public Character convert(Cursor cursor) {

        if (cursor == null) return null;

        return new Character(
                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_REALM)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_CLASS)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_RACE)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_GENDER)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_LEVEL)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_FACTION)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_ACHIEVEMENTS)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_HONORABLE_KILLS)),
                cursor.getString(cursor.getColumnIndex(COLUMN_THUMBNAIL))

        );
    }
}
