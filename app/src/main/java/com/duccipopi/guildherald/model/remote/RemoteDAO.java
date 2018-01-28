package com.duccipopi.guildherald.model.remote;

import android.support.v4.util.ArrayMap;

import com.duccipopi.guildherald.model.dao.Guild;
import com.duccipopi.guildherald.model.remote.blizzard.BlizzardAPIService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ducci on 28/01/2018.
 */

// Acess to remote services via Retrofit, return values via callbacks
public class RemoteDAO {

    // Keep services instances for reuse based in service URL
    static Map<String, Object> servicesInstancesMap = new ArrayMap<>();

    // Singleton handle for each service
    private static Object getServiceInstance(String baseURL, Class serviceClass) {
        if (!servicesInstancesMap.containsKey(baseURL)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            servicesInstancesMap.put(baseURL, retrofit.create(serviceClass));
        }

        return servicesInstancesMap.get(baseURL);
    }

    // Return Blizzard API service
    private static final BlizzardAPIService getBlizzardAPIService() {
        return (BlizzardAPIService) getServiceInstance(BlizzardAPIService.BASE_URL, BlizzardAPIService.class);
    }

    // Get base character information (no guild, no stats and no equipment)
    public static void getCharacterBaseInfo(String realm, String name, Callback callback) {
        BlizzardAPIService service = getBlizzardAPIService();

        Call<Character> characterCall = service.getCharacterBaseInfo(realm, name);
        characterCall.enqueue(callback);
    }

    // Get full character information (guild, stats and equipment)
    public static void getCharacterFullInfo(String realm, String name, Callback callback) {
        BlizzardAPIService service = getBlizzardAPIService();

        Call<Character> characterCall = service.getCharacterFullInfo(realm, name);
        characterCall.enqueue(callback);
    }

    // Get guild info (without members)
    public static void getGuildInfo(String realm, String name, Callback callback) {
        BlizzardAPIService service = getBlizzardAPIService();

        Call<Guild> guildCall = service.getGuildInfo(realm, name);
        guildCall.enqueue(callback);
    }

}
