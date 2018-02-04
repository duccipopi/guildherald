package com.duccipopi.guildherald.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.HeraldDAO;
import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.presenter.CharacterDetailsViewHolder;
import com.duccipopi.guildherald.util.Utilities;

public class CharacterDetailsActivity extends AppCompatActivity {

    HeraldDAO api;

    // TODO: Add progress view
    public CharacterDetailsActivity() {
        super();
        api = new HeraldDAO(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra(ActivityContract.EXTRA_NAME);
        String realm = intent.getStringExtra(ActivityContract.EXTRA_REALM);
        setTitle(name + " - " + realm);

        api.getCharacterFullInfo(realm, name, callback);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    HeraldCallback<Character> callback = new HeraldCallback<Character>() {
        @Override
        public void onResponse(Character character) {

            CharacterDetailsViewHolder viewHolder =
                    new CharacterDetailsViewHolder(findViewById(android.R.id.content));

            viewHolder.bind(character);

        }

        @Override
        public void onFailure(Character character) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Error loading character details", Snackbar.LENGTH_LONG).show();
        }
    };
}