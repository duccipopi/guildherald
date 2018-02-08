package com.duccipopi.guildherald.view.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.HeraldDAO;
import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.firebase.Authenticator;
import com.duccipopi.guildherald.model.firebase.FirebaseDB;
import com.duccipopi.guildherald.presenter.CharacterViewHolder;
import com.duccipopi.guildherald.presenter.base.GenericRecyclerViewAdapter;
import com.duccipopi.guildherald.util.AdMobManager;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


public class CharacterWidgetConfigureActivity extends AppCompatActivity {

    private AdView mAdView;
    private HeraldDAO api;

    RecyclerView recyclerView;
    List<Character> items = new ArrayList<>();

    int mAppWidgetId;

    public CharacterWidgetConfigureActivity() {
        api = new HeraldDAO(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Authenticator.REQUEST_SIGNING) {
            if (resultCode != RESULT_OK) finish();
            else initActivity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!Authenticator.isUserAuthenticated()) {
            Authenticator.startAuthenticationUI(this);
        } else {
            initActivity();
        }

    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) mAdView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mAdView != null) mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) mAdView.resume();
    }

    private void initActivity() {
        setContentView(R.layout.activity_widget_configure);

        Intent intent = getIntent();
        mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
        if (AppWidgetManager.ACTION_APPWIDGET_CONFIGURE.equals(intent.getAction())) {

            Bundle extras = intent.getExtras();
            if (extras != null) {
                mAppWidgetId = extras.getInt(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);
            }
        }

        recyclerView = findViewById(R.id.recyclerView);
        setupRecycler(items);

        FirebaseDB.loadCharacters(listCallback);

        // Ad View setup
        // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
        mAdView = findViewById(R.id.adView);

        // Start loading the ad.
        mAdView.loadAd(AdMobManager.getAdRequest());
    }

    private void setupRecycler(List<Character> items) {
        recyclerView.setAdapter(
                new GenericRecyclerViewAdapter<>(items,
                        new CharacterViewHolder(new View(this)),
                        R.layout.item_character,
                        onClickListener));
    }

    HeraldCallback<Character> callback = new HeraldCallback<Character>() {
        @Override
        public void onResponse(Character character) {
            items.add(character);
            recyclerView.getAdapter().notifyItemInserted(items.size() - 1);
        }

        @Override
        public void onFailure(Character character) {
            Snackbar.make(getCurrentFocus(), "Error loading character", Snackbar.LENGTH_LONG).show();
        }
    };

    HeraldCallback<Pair<String, String>> listCallback = new HeraldCallback<Pair<String, String>>() {
        @Override
        public void onResponse(Pair<String, String> pair) {
            api.getCharacterBaseInfo(pair.second, pair.first, callback);
        }

        @Override
        public void onFailure(Pair<String, String> stringStringPair) {

        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CharacterWidget.updateAppWidget(getApplicationContext(),
                    AppWidgetManager.getInstance(getApplicationContext()), mAppWidgetId, (Character) view.getTag());

            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

}


