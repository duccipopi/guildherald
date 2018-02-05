package com.duccipopi.guildherald.model.base;

import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;

/**
 * Created by ducci on 04/02/2018.
 */

public interface ICacheDAO {

    // Save character information
    void saveCharacter(Character character);

    // Save guild information
    void saveGuild(Guild guild);
}
