package com.duccipopi.guildherald.model;

import android.content.Context;

import com.duccipopi.guildherald.model.base.ICacheDAO;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;
import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.base.IServiceDAO;
import com.duccipopi.guildherald.model.local.LocalDAO;
import com.duccipopi.guildherald.model.remote.RemoteDAO;

/**
 * Created by ducci on 28/01/2018.
 */

public class HeraldDAO implements IServiceDAO {

    private final Context mContext;

    public HeraldDAO(Context context) {
        mContext = context;
    }

    @Override
    public void getCharacterBaseInfo(String realm, String name, HeraldCallback<Character> callback) {
        getDelegate(true).getCharacterBaseInfo(realm, name, callback);
    }

    @Override
    public void getCharacterFullInfo(String realm, String name, HeraldCallback<Character> callback) {
        getDelegate(true).getCharacterFullInfo(realm, name, callback);
    }

    @Override
    public void getGuildBaseInfo(String realm, String name, HeraldCallback<Guild> callback) {
        getDelegate(true).getGuildBaseInfo(realm, name, callback);
    }

    @Override
    public void getGuildFullInfo(String realm, String name, HeraldCallback<Guild> callback) {
        getDelegate(true).getGuildFullInfo(realm, name, callback);
    }

    private IServiceDAO getDelegate(boolean remote) {
        if (remote)
            return RemoteDAO.getInstance();
        else
            return LocalDAO.getInstance(mContext.getContentResolver());
    }

    private ICacheDAO getCacheDAO() {
        return LocalDAO.getInstance(mContext.getContentResolver());
    }


    private enum METHOD {
        GETCHARACTERBASEINFO,
        GETGUILDBASEINFO,
        SAVEINFORMATION
    }


    private class InterceptorCallback extends HeraldCallback {

        private HeraldCallback mCallback;
        private METHOD mMethod;
        private String mName;
        private String mRealm;

        public InterceptorCallback(METHOD method, String name, String realm, HeraldCallback callback) {
            super();
            mMethod = method;
            mName = name;
            mRealm = realm;
            mCallback = callback;
        }

        @Override
        public void onResponse(Object o) {
            if (o == null) {
                switch (mMethod) {
                    case GETCHARACTERBASEINFO:
                        getDelegate(true).getCharacterBaseInfo(mRealm, mName, getSavingInterceptor());
                        break;
                    case GETGUILDBASEINFO:
                        getDelegate(true).getGuildBaseInfo(mRealm, mName, getSavingInterceptor());
                        break;
                }
            } else {
                if (mMethod == METHOD.SAVEINFORMATION) {
                    if (o instanceof Character)
                        getCacheDAO().saveCharacter((Character) o);
                    else if (o instanceof Guild)
                        getCacheDAO().saveGuild((Guild) o);
                }

                mCallback.onResponse(o);
            }
        }

        @Override
        public void onFailure(Object o) {
            mCallback.onFailure(o);
        }

        private InterceptorCallback getSavingInterceptor() {
            mMethod = METHOD.SAVEINFORMATION;
            return this;
        }
    }


}
