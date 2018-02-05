package com.duccipopi.guildherald.model.local;

import android.content.ContentResolver;

import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.base.ICacheDAO;
import com.duccipopi.guildherald.model.base.IServiceDAO;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;
import com.duccipopi.guildherald.model.base.CharacterConverter;
import com.duccipopi.guildherald.model.base.GuildConverter;
import com.duccipopi.guildherald.model.local.provider.HeraldProviderContract;
import com.duccipopi.guildherald.model.local.task.InsertAsyncTask;
import com.duccipopi.guildherald.model.local.task.QueryAsyncTask;

/**
 * Created by ducci on 29/01/2018.
 */

public class LocalDAO implements IServiceDAO, ICacheDAO {

    private static LocalDAO instance;
    private ContentResolver mContentResolver;

    private LocalDAO(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    public static LocalDAO getInstance(ContentResolver contentResolver) {
        if (instance == null) {
            instance = new LocalDAO(contentResolver);
        }

        return instance;
    }


    // Get base character information (no guild, no stats and no equipment)
    @Override
    public void getCharacterBaseInfo(String realm, String name, HeraldCallback<Character> callback) {
        QueryAsyncTask<Character> queryAsyncTask =
                new QueryAsyncTask<>(mContentResolver, new CharacterConverter(), callback);

        queryAsyncTask.execute(HeraldProviderContract.CharacterEntry.CONTENT_URI
                .buildUpon().appendPath(realm).appendPath(name).build());
    }

    // Get full character information (guild, stats and equipment)
    @Override
    public void getCharacterFullInfo(String realm, String name, HeraldCallback<Character> callback) {
        QueryAsyncTask<Character> queryAsyncTask =
                new QueryAsyncTask<>(mContentResolver, new CharacterConverter(), callback);

        queryAsyncTask.execute(HeraldProviderContract.CharacterEntry.CONTENT_URI
                .buildUpon().appendPath(realm).appendPath(name).build());
    }

    // Get guild info (without members)
    @Override
    public void getGuildBaseInfo(String realm, String name, HeraldCallback<Guild> callback) {
        QueryAsyncTask<Guild> queryAsyncTask =
                new QueryAsyncTask<>(mContentResolver, new GuildConverter(), callback);

        queryAsyncTask.execute(HeraldProviderContract.GuildEntry.CONTENT_URI
                .buildUpon().appendPath(realm).appendPath(name).build());
    }

    // Get guild info (without members)
    @Override
    public void getGuildFullInfo(String realm, String name, HeraldCallback<Guild> callback) {
        QueryAsyncTask<Guild> queryAsyncTask =
                new QueryAsyncTask<>(mContentResolver, new GuildConverter(), callback);

        queryAsyncTask.execute(HeraldProviderContract.GuildEntry.CONTENT_URI
                .buildUpon().appendPath(realm).appendPath(name).build());
    }

    @Override
    public void saveCharacter(Character character) {
        InsertAsyncTask<Character> insertAsyncTask =
                new InsertAsyncTask<>(mContentResolver, new CharacterConverter(),
                        HeraldProviderContract.CharacterEntry.CONTENT_URI);

        insertAsyncTask.execute(character);
    }

    @Override
    public void saveGuild(Guild guild) {
        InsertAsyncTask<Guild> insertAsyncTask =
                new InsertAsyncTask<>(mContentResolver, new GuildConverter(),
                        HeraldProviderContract.GuildEntry.CONTENT_URI);

        insertAsyncTask.execute(guild);
    }
}
