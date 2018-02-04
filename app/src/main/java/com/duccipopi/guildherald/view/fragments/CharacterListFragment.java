package com.duccipopi.guildherald.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.dummy.Dummy;
import com.duccipopi.guildherald.model.HeraldDAO;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.model.base.HeraldCallback;
import com.duccipopi.guildherald.model.firebase.FirebaseDB;
import com.duccipopi.guildherald.presenter.CharacterViewHolder;
import com.duccipopi.guildherald.presenter.base.GenericRecyclerViewAdapter;
import com.duccipopi.guildherald.view.ActivityContract;
import com.duccipopi.guildherald.view.CharacterDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducci on 31/01/2018.
 */

public class CharacterListFragment extends Fragment {

    List<Character> items = new ArrayList<>();
    RecyclerView recyclerView;
    HeraldDAO api;


    public CharacterListFragment() {
        super();
        api = new HeraldDAO(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        FirebaseDB.loadCharacters(listCallback);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        setupRecycler(items);

        return rootView;
    }

    private void setupRecycler(List<Character> items) {
        recyclerView.setAdapter(
                new GenericRecyclerViewAdapter<>(items,
                        new CharacterViewHolder(new View(getContext())),
                        R.layout.item_character,
                        onClickListener));
    }

    HeraldCallback<Character> callback = new HeraldCallback<Character>() {
        @Override
        public void onResponse(Character character) {
            items.add(character);
            recyclerView.getAdapter().notifyDataSetChanged();
        }

        @Override
        public void onFailure(Character character) {
            Snackbar.make(getView(), "Error loading character", Snackbar.LENGTH_LONG).show();
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
            Intent intent = new Intent(getContext(), CharacterDetailsActivity.class);
            Character character = (Character) view.getTag();
            intent.putExtra(ActivityContract.EXTRA_NAME, character.getName());
            intent.putExtra(ActivityContract.EXTRA_REALM, character.getRealm());

            startActivity(intent);
        }
    };
}
