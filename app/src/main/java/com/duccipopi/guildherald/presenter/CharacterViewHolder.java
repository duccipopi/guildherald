package com.duccipopi.guildherald.presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.presenter.base.GenericViewHolder;
import com.duccipopi.guildherald.presenter.base.GenericViewHolderFactory;
import com.duccipopi.guildherald.util.Utilities;

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
        portrait = itemView.findViewById(R.id.portrait);
        charName = itemView.findViewById(R.id.char_name);
        realm = itemView.findViewById(R.id.realm);
        race = itemView.findViewById(R.id.race);
        gender = itemView.findViewById(R.id.gender);
        charClass = itemView.findViewById(R.id.char_class);
        level = itemView.findViewById(R.id.level);


        Utilities.Image.loadImage(itemView.getContext(),
                Utilities.Blizzard.getThumbnailFullURL(item.getThumbnail()),
                R.drawable.default_portrait, R.drawable.error_portrait, portrait);
        charName.setText(item.getName());
        realm.setText(item.getRealm());
        race.setText(Utilities.Blizzard.getRaceResId(item.getRace()));
        gender.setText(Utilities.Blizzard.getGenderResId(item.getGender()));
        charClass.setText(Utilities.Blizzard.getClassResId(item.getCClass()));
        level.setText(Integer.toString(item.getLevel()));
    }

    @Override
    public CharacterViewHolder create(View itemView) {
        return new CharacterViewHolder(itemView);
    }
}
