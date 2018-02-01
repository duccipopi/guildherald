package com.duccipopi.guildherald.dummy;

import android.support.annotation.IntRange;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducci on 31/01/2018.
 */

public class Dummy {

    public static final List<Pair<String,String>> getCharList() {
        List<Pair<String,String>> pairs = new ArrayList<>();

        pairs.add(new Pair<>("Thugal", "Scilla"));
        pairs.add(new Pair<>("Lüna", "Scilla"));
        pairs.add(new Pair<>("Zehzin", "Scilla"));
        pairs.add(new Pair<>("Yuling", "Scilla"));

        return pairs;

    }

    public static final List<Pair<String,String>> getGuildList() {
        List<Pair<String,String>> pairs = new ArrayList<>();

        pairs.add(new Pair<>("Get Along Gang", "Scilla"));
        pairs.add(new Pair<>("Nemesis", "Azralon"));

        return pairs;

    }
}
