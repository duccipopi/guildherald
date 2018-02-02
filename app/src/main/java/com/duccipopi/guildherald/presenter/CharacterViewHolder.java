package com.duccipopi.guildherald.presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.dao.Character;

/**
 * Created by ducci on 01/02/2018.
 */

public class CharacterViewHolder extends GenericViewHolder<Character>
        implements GenericViewHolderFactory<CharacterViewHolder> {

    ImageView portrait;
    TextView charName;
    TextView realm;
    TextView race;
    TextView gender;
    TextView charClass;
    TextView level;

    public CharacterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Character item) {
        charName = itemView.findViewById(R.id.char_name);
        realm = itemView.findViewById(R.id.realm);
        race = itemView.findViewById(R.id.race);
        gender = itemView.findViewById(R.id.gender);
        charClass = itemView.findViewById(R.id.char_class);
        level = itemView.findViewById(R.id.level);


        // portrait = use Image lib to load portrait
        charName.setText(item.getName());
        realm.setText(item.getRealm());
        race.setText(Integer.toString(item.getRace()));
        gender.setText(Integer.toString(item.getGender()));
        charClass.setText(Integer.toString(item.getcClass()));
        level.setText(Integer.toString(item.getLevel()));

    }

    @Override
    public CharacterViewHolder create(View itemView) {
        return new CharacterViewHolder(itemView);
    }
}
