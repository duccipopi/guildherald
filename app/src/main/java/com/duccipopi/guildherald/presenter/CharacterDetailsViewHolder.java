package com.duccipopi.guildherald.presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.dao.Character;
import com.duccipopi.guildherald.presenter.base.GenericViewHolder;
import com.duccipopi.guildherald.util.Utilities;

/**
 * Created by ducci on 04/02/2018.
 */

public class CharacterDetailsViewHolder extends GenericViewHolder<Character> {

    public CharacterDetailsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Character character) {
        ImageView toolbarBackgroud = itemView.findViewById(R.id.app_bar_background);
        Utilities.Image.loadImage(itemView.getContext(),
                Utilities.Blizzard.getInsetFullURL(character.getThumbnail()),
                R.drawable.default_portrait, R.drawable.error_portrait, toolbarBackgroud);

        // Character base info
        TextView race = itemView.findViewById(R.id.race);
        TextView gender = itemView.findViewById(R.id.gender);
        TextView charClass = itemView.findViewById(R.id.char_class);
        TextView level = itemView.findViewById(R.id.level);

        race.setText(Utilities.Blizzard.getRaceResId(character.getRace()));
        gender.setText(Utilities.Blizzard.getGenderResId(character.getGender()));
        charClass.setText(Utilities.Blizzard.getClassResId(character.getCClass()));
        level.setText(Integer.toString(character.getLevel()));

        // Primary and secondary stats
        TextView health = itemView.findViewById(R.id.health_value);
        TextView powerName = itemView.findViewById(R.id.power);
        TextView power = itemView.findViewById(R.id.power_value);
        TextView achievement = itemView.findViewById(R.id.achievements_value);
        TextView honorableKills = itemView.findViewById(R.id.honorablekills_value);
        TextView strength = itemView.findViewById(R.id.strength_value);
        TextView agility = itemView.findViewById(R.id.agility_value);
        TextView intellect = itemView.findViewById(R.id.intellect_value);
        TextView stamina = itemView.findViewById(R.id.stamina_value);

        health.setText(Integer.toString(character.getStats().getHealth()));
        powerName.setText(character.getStats().getPowerType());
        power.setText(Integer.toString(character.getStats().getPower()));
        achievement.setText(Integer.toString(character.getHonorableKills()));
        honorableKills.setText(Integer.toString(character.getHonorableKills()));
        strength.setText(Integer.toString(character.getStats().getStrength()));
        agility.setText(Integer.toString(character.getStats().getAgility()));
        intellect.setText(Integer.toString(character.getStats().getIntelligence()));
        stamina.setText(Integer.toString(character.getStats().getStamina()));

        // Tertiary and defense stats
        TextView critical = itemView.findViewById(R.id.critical_value);
        TextView haste = itemView.findViewById(R.id.haste_value);
        TextView mastery = itemView.findViewById(R.id.mastery_value);
        TextView versatility = itemView.findViewById(R.id.versatility_value);
        TextView armor = itemView.findViewById(R.id.armor_value);
        TextView dodge = itemView.findViewById(R.id.dodge_value);
        TextView parry = itemView.findViewById(R.id.parry_value);
        TextView block = itemView.findViewById(R.id.block_value);

        critical.setText(Float.toString(character.getStats().getCritical()));
        haste.setText(Float.toString(character.getStats().getHaste()));
        mastery.setText(Float.toString(character.getStats().getMastery()));
        versatility.setText(Float.toString(character.getStats().getVersatility()));
        armor.setText(Integer.toString(character.getStats().getArmor()));
        dodge.setText(Float.toString(character.getStats().getDodge()));
        parry.setText(Float.toString(character.getStats().getParry()));
        block.setText(Float.toString(character.getStats().getBlock()));
    }
}
