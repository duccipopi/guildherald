package com.duccipopi.guildherald.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.HeraldDAO;
import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.dao.Guild;
import com.duccipopi.guildherald.model.dao.Member;
import com.duccipopi.guildherald.presenter.CharacterViewHolder;
import com.duccipopi.guildherald.presenter.base.GenericRecyclerViewAdapter;
import com.duccipopi.guildherald.util.Utilities;

import java.util.ArrayList;
import java.util.List;

public class GuildDetailsActivity extends AppCompatActivity {

    HeraldDAO api;

    public GuildDetailsActivity() {
        super();
        api = new HeraldDAO(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guild_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra(ActivityContract.EXTRA_NAME);
        String realm = intent.getStringExtra(ActivityContract.EXTRA_REALM);
        setTitle(name + " - " + realm);

        api.getGuildFullInfo(realm, name, callback);

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

    HeraldCallback<Guild> callback = new HeraldCallback<Guild>() {
        @Override
        public void onResponse(Guild guild) {

            ImageView emblem;
            TextView achievements;

            emblem = findViewById(R.id.app_bar_emblem);
            achievements = findViewById(R.id.achievements);

            Utilities.Image.loadImage(GuildDetailsActivity.this,
                    Utilities.Blizzard.getEmblemURL(guild), R.drawable.default_emblem,
                    R.drawable.error_portrait,
                    emblem);

            achievements.setText(Integer.toString(guild.getAchievements()));

            RecyclerView recyclerView = findViewById(R.id.members_list);

            List<Character> items = new ArrayList<>(guild.getNumMembers());

            for (Member member : guild.getMembers()) {
                items.add(member.getCharacter());
            }

            recyclerView.setAdapter(
                    new GenericRecyclerViewAdapter<>(items,
                            new CharacterViewHolder(new View(GuildDetailsActivity.this)),
                            R.layout.item_character,
                            onClickListener));

        }

        @Override
        public void onFailure(Guild Guild) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Error loading guild details", Snackbar.LENGTH_LONG).show();
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(GuildDetailsActivity.this, CharacterDetailsActivity.class);
            Character character = (Character) view.getTag();
            intent.putExtra(ActivityContract.EXTRA_NAME, character.getName());
            intent.putExtra(ActivityContract.EXTRA_REALM, character.getRealm());

            startActivity(intent);
        }
    };
}
