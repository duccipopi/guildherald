package com.duccipopi.guildherald.view.fragments;

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
import com.duccipopi.guildherald.model.dao.Guild;
import com.duccipopi.guildherald.model.implementation.HeraldCallback;
import com.duccipopi.guildherald.presenter.GuildViewHolder;
import com.duccipopi.guildherald.presenter.base.GenericRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducci on 01/02/2018.
 */

public class GuildListFragment extends Fragment {
    List<Guild> items = new ArrayList<>();
    RecyclerView recyclerView;
    HeraldDAO api;

    public GuildListFragment() {
        super();
        api = new HeraldDAO(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        // TODO: Use Loader and real values from DAO
        for (Pair<String, String> pair : Dummy.getGuildList()) {
            api.getGuildInfo(pair.second, pair.first, callback);
        }

        recyclerView = rootView.findViewById(R.id.recyclerView);
        setupRecycler(items);

        return rootView;
    }

    private void setupRecycler(List<Guild> items) {
        recyclerView.setAdapter(
                new GenericRecyclerViewAdapter<>(items,
                        new GuildViewHolder(new View(getContext())),
                        R.layout.item_guild));
    }

    HeraldCallback<Guild> callback = new HeraldCallback<Guild>() {
        @Override
        public void onResponse(Guild guild) {
            items.add(guild);
            recyclerView.getAdapter().notifyDataSetChanged();
        }

        @Override
        public void onFailure(Guild guild) {
            Snackbar.make(getView(), "Error loading guild", Snackbar.LENGTH_LONG);
        }
    };
}
