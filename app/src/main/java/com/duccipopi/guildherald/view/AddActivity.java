package com.duccipopi.guildherald.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.HeraldDAO;
import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;
import com.duccipopi.guildherald.model.firebase.FirebaseDB;
import com.duccipopi.guildherald.presenter.CharacterViewHolder;
import com.duccipopi.guildherald.presenter.GuildViewHolder;
import com.duccipopi.guildherald.util.AdMobManager;
import com.duccipopi.guildherald.util.Utilities;
import com.google.android.gms.ads.AdView;

public class AddActivity extends AppCompatActivity {

    HeraldDAO api;
    View resultView;
    TextView editName;
    TextView editRealm;
    ProgressBar progressBar;

    Object currentResult = null;

    public AddActivity() {
        super();
        api = new HeraldDAO(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Define Activity title and result view
        int titleResId = R.string.title_activity_add;
        int resultLayout = R.layout.empty_result;
        int logoResId = R.drawable.fab_add;
        if (ActivityContract.ACTION_ADD_CHARACTER.equals(getIntent().getAction())) {
            titleResId = R.string.title_add_character;
            resultLayout = R.layout.item_character;
            logoResId = R.drawable.logo_portrait;
        } else if (ActivityContract.ACTION_ADD_GUILD.equals(getIntent().getAction())) {
            titleResId = R.string.title_add_guild;
            resultLayout = R.layout.item_guild;
            logoResId = R.drawable.logo_emblem;
        } else {
            finish();
        }

        // Set title
        setTitle(getString(titleResId));
        toolbar.setLogo(logoResId);

        // Set result view
        FrameLayout frameLayout = findViewById(R.id.resultView);
        resultView = LayoutInflater.from(this).inflate(resultLayout, frameLayout);

        // Get other views
        editName = findViewById(R.id.name);
        editRealm = findViewById(R.id.realm);
        progressBar = findViewById(R.id.progress);

        ImageButton buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityContract.ACTION_ADD_CHARACTER.equals(getIntent().getAction())) {
                    FirebaseDB.save((Character) currentResult);
                } else if (ActivityContract.ACTION_ADD_GUILD.equals(getIntent().getAction())) {
                    FirebaseDB.save((Guild) currentResult);
                }

                finish();
            }
        });

        FloatingActionButton fabSearch = findViewById(R.id.fabSearch);
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utilities.SystemServices.hideKeyboard(AddActivity.this);

                if (editName == null || editRealm == null
                        || TextUtils.isEmpty(editName.getText())
                        || TextUtils.isEmpty(editRealm.getText())) {
                    Snackbar.make(view, "Please, fill all fields", Snackbar.LENGTH_LONG).show();
                } else {

                    progressBar.setVisibility(View.VISIBLE);
                    resultView.setVisibility(View.GONE);

                    if (ActivityContract.ACTION_ADD_CHARACTER.equals(getIntent().getAction())) {
                        api.getCharacterBaseInfo(editRealm.getText().toString(),
                                editName.getText().toString(), callbackCharacter);
                    } else if (ActivityContract.ACTION_ADD_GUILD.equals(getIntent().getAction())) {
                        api.getGuildBaseInfo(editRealm.getText().toString(), editName.getText().toString(),
                                callbackGuild);
                    }
                }
            }
        });

        // Ad View setup
        // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
        AdView adView = findViewById(R.id.adView);

        // Start loading the ad.
        adView.loadAd(AdMobManager.getAdRequest());
    }

    HeraldCallback<Character> callbackCharacter = new HeraldCallback<Character>() {
        @Override
        public void onResponse(Character character) {
            CharacterViewHolder viewHolder = new CharacterViewHolder(resultView);
            viewHolder.bind(character);
            resultView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            currentResult = character;
        }

        @Override
        public void onFailure(Character character) {
            progressBar.setVisibility(View.GONE);
            Snackbar.make(resultView, "Error loading character", Snackbar.LENGTH_LONG).show();

            currentResult = null;
        }
    };

    HeraldCallback<Guild> callbackGuild = new HeraldCallback<Guild>() {
        @Override
        public void onResponse(Guild guild) {
            GuildViewHolder viewHolder = new GuildViewHolder(resultView);
            viewHolder.bind(guild);
            resultView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            currentResult = guild;
        }

        @Override
        public void onFailure(Guild guild) {
            progressBar.setVisibility(View.GONE);
            Snackbar.make(resultView, "Error loading guild", Snackbar.LENGTH_LONG).show();

            currentResult = null;
        }
    };

}
