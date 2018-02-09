package com.duccipopi.guildherald.model.firebase;

import android.app.Activity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ducci on 03/02/2018.
 */

public class Authenticator {

    public static final int REQUEST_SIGNING = 101;

    public static void startAuthenticationUI(Activity context) {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        // Create and launch sign-in intent
        context.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                REQUEST_SIGNING);
    }

    public static boolean isUserAuthenticated() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public static String getUser() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
