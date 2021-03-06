package com.duccipopi.guildherald.model.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ducci on 28/01/2018.
 */

public class Character {

    private String name;
    private String realm;
    @SerializedName("class") private int cClass;
    private int race;
    private int gender;
    private int level;
    private int faction;
    @SerializedName("achievementPoints") private int achievements;
    @SerializedName("totalHonorableKills") private int honorableKills;
    private String thumbnail;

    private Stats stats;
    private Object guild;
    private Equipment[] equipments;

    public Character(String name, String realm, int cClass, int race, int gender, int level,
                     int faction, int achievements, int honorableKills, String thumbnail) {
        this.name = name;
        this.realm = realm;
        this.cClass = cClass;
        this.race = race;
        this.gender = gender;
        this.level = level;
        this.faction = faction;
        this.achievements = achievements;
        this.honorableKills = honorableKills;
        this.thumbnail = thumbnail;
    }

    public Character(String name, String realm, int cClass, int race, int gender, int level,
                     int faction, int achievements, int honorableKills, String thumbnail,
                     Guild guild, Stats stats) {
        this.name = name;
        this.realm = realm;
        this.cClass = cClass;
        this.race = race;
        this.gender = gender;
        this.level = level;
        this.faction = faction;
        this.achievements = achievements;
        this.honorableKills = honorableKills;
        this.thumbnail = thumbnail;
        this.guild = guild;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public String getRealm() {
        return realm;
    }

    public int getCClass() {
        return cClass;
    }

    public int getRace() {
        return race;
    }

    public int getGender() {
        return gender;
    }

    public int getLevel() {
        return level;
    }

    public int getFaction() {
        return faction;
    }

    public int getAchievements() {
        return achievements;
    }

    public int getHonorableKills() {
        return honorableKills;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Stats getStats() {
        return stats;
    }

    public Object getGuild() {
        return guild;
    }

    public Equipment[] getEquipments() {
        return equipments;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public void setEquipments(Equipment[] equipments) {
        this.equipments = equipments;
    }
}
