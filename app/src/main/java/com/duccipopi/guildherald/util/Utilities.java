package com.duccipopi.guildherald.util;

import android.content.Context;
import android.widget.ImageView;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.dao.Emblem;
import com.duccipopi.guildherald.model.dao.Guild;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ducci on 02/02/2018.
 */

public final class Utilities {

    public static final class Image {

        public static final void loadImage(Context context, String path, int placeholder, int errorImage,
                                           ImageView imageView) {
            Picasso.with(context).load(path).placeholder(placeholder).error(errorImage).into(imageView);
        }
    }

    public static final class Blizzard {
        static String BASE_THUMBNAIL_URL = "http://us.battle.net/static-render/us/";

        public static String getThumbnailFullURL(String thumbnail) {
            return BASE_THUMBNAIL_URL + thumbnail;
        }

        public static int getRaceResId(int race) {

            switch (race) {
                case 1:
                    return R.string.race_human;
                case 2:
                    return R.string.race_orc;
                case 3:
                    return R.string.race_dwarf;
                case 4:
                    return R.string.race_night_elf;
                case 5:
                    return R.string.race_undead;
                case 6:
                    return R.string.race_tauren;
                case 7:
                    return R.string.race_gnome;
                case 8:
                    return R.string.race_troll;
                case 9:
                    return R.string.race_goblin;
                case 10:
                    return R.string.race_blood_elf;
                case 11:
                    return R.string.race_draenei;
                case 22:
                    return R.string.race_worgen;
                case 24:
                    return R.string.race_pandaren;
                case 25:
                    return R.string.race_pandaren;
                case 26:
                    return R.string.race_pandaren;
                case 27:
                    return R.string.race_nightborne;
                case 28:
                    return R.string.race_highmountain_tauren;
                case 29:
                    return R.string.race_void_elf;
                case 30:
                    return R.string.race_lightforged_draenei;
                default:
                    return R.string.undefined;
            }
        }

        public static int getClassResId(int cclass) {

            switch (cclass) {
                case 1: return R.string.class_warrior;
                case 2: return R.string.class_paladin;
                case 3: return R.string.class_hunter;
                case 4: return R.string.class_rogue;
                case 5: return R.string.class_priest;
                case 6: return R.string.class_death_knight;
                case 7: return R.string.class_shaman;
                case 8: return R.string.class_mage;
                case 9: return R.string.class_warlock;
                case 10: return R.string.class_monk;
                case 11: return R.string.class_druid;
                case 12: return R.string.class_demon_hunter;
                default: return R.string.undefined;
            }

        }

        public static int getGenderResId(int gender) {
            return gender == 0 ? R.string.gender_male : R.string.gender_female;
        }

        // TODO: Fix format string
        public static final String BASE_EMBLEM_URL =
                "http://tabard.gnomeregan.info/tabard.php?icon=emblem_%1$d2\\\\&border=border_%2$d2\\\\&iconcolor=%3\\\\&bgcolor=%4\\\\&bordercolor=%5\\\\&faction=%6";

        public static String getEmblemURL(Guild guild) {
            //http://tabard.gnomeregan.info/tabard.php?icon=emblem_00&border=border_00&iconcolor=ffffff&bgcolor=000000&bordercolor=63a300&faction=Alliance
            return String.format(BASE_EMBLEM_URL, guild.getEmblem().getIcon(), guild.getEmblem().getBorder(),
                    guild.getEmblem().getIconColor(), guild.getEmblem().getBackgroundColor(),
                    guild.getEmblem().getBorderColor(), guild.getFaction() == 0 ? "Alliance" : "Horde");
        }
    }


}
