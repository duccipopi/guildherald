package com.duccipopi.guildherald.model.base;

import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;

/**
 * Created by ducci on 29/01/2018.
 */

public interface IServiceDAO {

    // Get base character information (no guild, no stats and no equipment)
    void getCharacterBaseInfo(String realm, String name, HeraldCallback<Character> callback);

    // Get full character information (guild, stats and equipment)
    void getCharacterFullInfo(String realm, String name, HeraldCallback<Character> callback);

    // Get guild info (without members)
    void getGuildInfo(String realm, String name, HeraldCallback<Guild> callback);
}
