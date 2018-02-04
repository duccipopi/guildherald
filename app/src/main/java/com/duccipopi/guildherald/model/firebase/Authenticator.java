package com.duccipopi.guildherald.model.firebase;

import android.content.Context;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ducci on 03/02/2018.
 */

public class Authenticator {

    public static void startAuthenticationUI(Context context) {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        context.startActivity(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build());
    }

    public static boolean isUserAuthenticated() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public static String getUser() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
