package com.duccipopi.guildherald.model.base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ducci on 29/01/2018.
 */

public abstract class HeraldCallback<T> implements Callback<T> {

    public abstract void onResponse(T t);
    public abstract void onFailure(T t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponse(response.body());
        } else {
            onFailure(null);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(null);
    }
}
