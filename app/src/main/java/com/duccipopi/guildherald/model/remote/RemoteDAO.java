package com.duccipopi.guildherald.model.remote;

import android.support.v4.util.ArrayMap;

import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.base.IServiceDAO;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;
import com.duccipopi.guildherald.model.remote.blizzard.BlizzardAPIService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ducci on 28/01/2018.
 */

// Access to remote services via Retrofit, return values via callbacks
public class RemoteDAO implements IServiceDAO {

    private static RemoteDAO instance;

    // Keep services instances for reuse based in service URL
    private static Map<String, Object> servicesInstancesMap = new ArrayMap<>();

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

    private RemoteDAO() {}

    public static RemoteDAO getInstance() {
        if (instance == null) instance = new RemoteDAO();

        return instance;
    }

    // Return Blizzard API service
    private static final BlizzardAPIService getBlizzardAPIService() {
        return (BlizzardAPIService) getServiceInstance(BlizzardAPIService.BASE_URL, BlizzardAPIService.class);
    }

    // Get base character information (no guild, no stats and no equipment)
    @Override
    public void getCharacterBaseInfo(String realm, String name, HeraldCallback<Character> callback) {
        BlizzardAPIService service = getBlizzardAPIService();

        Call<Character> characterCall = service.getCharacterBaseInfo(realm, name);
        characterCall.enqueue(callback);
    }

    // Get full character information (guild, stats and equipment)
    @Override
    public void getCharacterFullInfo(String realm, String name, HeraldCallback<Character> callback) {
        BlizzardAPIService service = getBlizzardAPIService();

        Call<Character> characterCall = service.getCharacterFullInfo(realm, name);
        characterCall.enqueue(callback);
    }

    // Get guild info (without members)
    @Override
    public void getGuildBaseInfo(String realm, String name, HeraldCallback<Guild> callback) {
        BlizzardAPIService service = getBlizzardAPIService();

        Call<Guild> guildCall = service.getGuildBaseInfo(realm, name);
        guildCall.enqueue(callback);
    }

    // Get guild info (without members)
    @Override
    public void getGuildFullInfo(String realm, String name, HeraldCallback<Guild> callback) {
        BlizzardAPIService service = getBlizzardAPIService();

        Call<Guild> guildCall = service.getGuildFullInfo(realm, name);
        guildCall.enqueue(callback);
    }

}
