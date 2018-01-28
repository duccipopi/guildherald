package com.duccipopi.guildherald.model.local;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ducci on 28/01/2018.
 */

public interface HeraldProviderContract {

    String AUTHORITY = "com.duccipopi.guildherald.provider";
    Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Mime types
    String MIME_DIR = "vnd.android.cursor.dir";
    String MIME_ITEM = "vnd.android.cursor.item";

    String MIME_CHARACTER_DIR = MIME_DIR + "/character";
    String MIME_CHARACTER_ITEM = MIME_ITEM + "/character";

    String MIME_GUILD_DIR = MIME_DIR + "/guild";
    String MIME_GUILD_ITEM = MIME_ITEM + "/guild";

    // Characters local repository path
    String PATH_CHARACTERS_DIRECTORY = "character";
    String PATH_CHARACTERS_ITEM = PATH_CHARACTERS_DIRECTORY + "/*/*";

    // Guild local repository path
    String PATH_GUILD_DIRECTORY = "guild";
    String PATH_GUILD_ITEM = PATH_GUILD_DIRECTORY + "/*/*";

    final class CharacterEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CHARACTERS_DIRECTORY).build();

        public static final String TABLE_NAME = "characters";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_REALM = "realm";
        public static final String COLUMN_CLASS = "class";
        public static final String COLUMN_RACE = "race";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_FACTION = "faction";
        public static final String COLUMN_ACHIEVEMENTS = "achievements";
        public static final String COLUMN_HONORABLE_KILLS = "honorable_kills";
        public static final String COLUMN_GUILD = "guild";
        public static final String COLUMN_THUMBNAIL = "thumbnail";

        public static final String[] COLUMNS = {
                COLUMN_NAME,
                COLUMN_REALM,
                COLUMN_CLASS,
                COLUMN_RACE,
                COLUMN_GENDER,
                COLUMN_LEVEL,
                COLUMN_FACTION,
                COLUMN_ACHIEVEMENTS,
                COLUMN_HONORABLE_KILLS,
                COLUMN_GUILD,
                COLUMN_THUMBNAIL
        };
    }

    final class GuildEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GUILD_DIRECTORY).build();

        public static final String TABLE_NAME = "guild";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_REALM = "realm";
        public static final String COLUMN_MEMBERS = "members";
        public static final String COLUMN_FACTION = "faction";
        public static final String COLUMN_ACHIEVEMENTS = "achievements";
        public static final String COLUMN_EMBLEM = "emblem";


        public static final String[] COLUMNS = {
                COLUMN_NAME,
                COLUMN_REALM,
                COLUMN_FACTION,
                COLUMN_ACHIEVEMENTS,
                COLUMN_EMBLEM
        };
    }

}
