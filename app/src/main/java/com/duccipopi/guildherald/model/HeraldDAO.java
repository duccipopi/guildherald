package com.duccipopi.guildherald.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;
import com.duccipopi.guildherald.model.implementation.HeraldCallback;
import com.duccipopi.guildherald.model.implementation.IServiceDAO;
import com.duccipopi.guildherald.model.local.LocalDAO;
import com.duccipopi.guildherald.model.remote.RemoteDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ducci on 28/01/2018.
 */

// TODO: Save recovered information locally
// TODO: Save Quota information
/* TODO: Choose wisely between Remote or local information
            Check if exist locally
            Check renew date
*/
public class HeraldDAO implements IServiceDAO {

    private final Context mContext;

    public HeraldDAO(Context context) {
        mContext = context;
    }

    @Override
    public void getCharacterBaseInfo(String realm, String name, HeraldCallback<Character> callback) {
        getDelegate().getCharacterBaseInfo(realm, name, callback);
    }

    @Override
    public void getCharacterFullInfo(String realm, String name, HeraldCallback<Character> callback) {
        getDelegate().getCharacterFullInfo(realm, name, callback);
    }

    @Override
    public void getGuildInfo(String realm, String name, HeraldCallback<Guild> callback) {
        getDelegate().getGuildInfo(realm, name, callback);
    }

    private IServiceDAO getDelegate() {
        if (isQuotaRenewed(getQuotaRenewDate(mContext)))
            return RemoteDAO.getInstance();
        else
            return LocalDAO.getInstance(mContext.getContentResolver());
    }

    private Date getQuotaRenewDate(Context context) {
        //Tuesday, January 30, 2018 2:00:00 AM GMT
        // TODO: Get from shared preference
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy K:mm:ss a z");
        try {
            return simpleDateFormat.parse("Tuesday, January 30, 2018 2:00:00 AM GMT");
        } catch (ParseException e) {
            return null;
        }
    }

    private boolean isQuotaRenewed(Date date) {
        Date now = new Date();
        return date == null || now.after(date);
    }

}
