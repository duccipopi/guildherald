package com.duccipopi.guildherald.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.dummy.Dummy;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.presenter.CharacterViewHolder;
import com.duccipopi.guildherald.presenter.GenericRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducci on 31/01/2018.
 */

public class CharacterListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        List<Character> items = new ArrayList<>();

        // TODO: Use Loader and real values from DAO
        for (Pair<String, String> pair : Dummy.getCharList()) {
            items.add(new Character(pair.first, pair.second, 0, 0, 0, 0, 0, 0, 0, null));
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(
                new GenericRecyclerViewAdapter<>(items,
                        new CharacterViewHolder(
                                getLayoutInflater().inflate(R.layout.item_character, null))));

        return rootView;
    }
}
