package com.duccipopi.guildherald.model.firebase;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ducci on 03/02/2018.
 */

public class FirebaseDB {

    private static final String CHILD_CHARACTERS = "chars";
    private static final String CHILD_GUILDS = "guilds";

    static {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static void save(@NonNull Character character) {
        FirebaseEntry entry = new FirebaseEntry(character.getName(), character.getRealm());

        save(CHILD_CHARACTERS, entry);
    }

    public static void save(@NonNull Guild guild) {
        FirebaseEntry entry = new FirebaseEntry(guild.getName(), guild.getRealm());

        save(CHILD_GUILDS, entry);
    }

    private static void save(String child, FirebaseEntry entry) {
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference()
                .child(Authenticator.getUser()).child(child);

        dbReference.push().setValue(entry);

    }

    public static void loadCharacters(@NonNull HeraldCallback<Pair<String, String>> callback) {
        load(CHILD_CHARACTERS, callback);
    }

    public static void loadGuilds(@NonNull HeraldCallback<Pair<String, String>> callback) {
        load(CHILD_GUILDS, callback);
    }

    private static void load(String child, final HeraldCallback callback) {
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference()
                .child(Authenticator.getUser()).child(child);

        ChildEventListener listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FirebaseEntry entry = dataSnapshot.getValue(FirebaseEntry.class);
                callback.onResponse(new Pair<>(entry.name, entry.realm));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };

        dbReference.addChildEventListener(listener);

    }

}
