package com.duccipopi.guildherald.presenter;

import android.view.View;
import android.view.ViewGroup;

import com.duccipopi.guildherald.model.dao.Character;

/**
 * Created by ducci on 01/02/2018.
 */

public class CharacterViewHolder extends GenericViewHolder<Character> {
    private View itemView;

    public CharacterViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    @Override
    public CharacterViewHolder newInstance(ViewGroup parent) {
        parent.addView(itemView);
        return new CharacterViewHolder(itemView);
    }

    @Override
    public void bind(Character item) {
        // TODO bind values to views
    }

}
