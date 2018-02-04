package com.duccipopi.guildherald.util;

import com.google.android.gms.ads.AdRequest;

/**
 * Created by ducci on 03/02/2018.
 */

public class AdMobManager {

    private static AdRequest sAdRequest;

    // Ensure only one AdRequest at time
    public static AdRequest getAdRequest() {
        if (sAdRequest == null) {
            // Create an ad request.
            sAdRequest = new AdRequest.Builder().build();
        }

        return sAdRequest;
    }

}
