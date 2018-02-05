package com.duccipopi.guildherald.model.remote.blizzard;

import com.duccipopi.guildherald.BuildConfig;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ducci on 28/01/2018.
 */

// Retrofit service definition
public interface BlizzardAPIService {

    String API_KEY = "&apikey=psb9d7c6k3j9bhqe7pemc2g6rbrp2es9";
    String LOCALE = "locale=en_US";

    String BASE_URL = "https://us.api.battle.net/wow/";


    @GET("character/{realm}/{charname}?" + LOCALE + API_KEY)
    Call<Character> getCharacterBaseInfo(@Path("realm") String realm, @Path("charname") String name);

    @GET("character/{realm}/{charname}?fields=items%2Cstats%2Cguild" + LOCALE + API_KEY)
    Call<Character> getCharacterFullInfo(@Path("realm") String realm, @Path("charname") String name);

    @GET("guild/{realm}/{guildname}?" + LOCALE + API_KEY)
    Call<Guild> getGuildBaseInfo(@Path("realm") String realm, @Path("guildname") String name);


    @GET("guild/{realm}/{guildname}?fields=members%2C" + LOCALE + API_KEY)
    Call<Guild> getGuildFullInfo(@Path("realm") String realm, @Path("guildname") String name);

}
