package com.duccipopi.guildherald.model.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ducci on 28/01/2018.
 */

public class Guild {

    private String name;
    private String realm;
    @SerializedName("side") private int faction;
    private int numMembers;
    private int achievements;
    private Emblem emblem;

    public Guild(String name, String realm, int faction, int numMembers, int achievements, Emblem emblem) {
        this.name = name;
        this.realm = realm;
        this.faction = faction;
        this.numMembers = numMembers;
        this.achievements = achievements;
        this.emblem = emblem;
    }

    public String getName() {
        return name;
    }

    public String getRealm() {
        return realm;
    }

    public int getFaction() {
        return faction;
    }

    public int getNumMembers() {
        return numMembers;
    }

    public int getAchievements() {
        return achievements;
    }

    public Emblem getEmblem() {
        return emblem;
    }
}
