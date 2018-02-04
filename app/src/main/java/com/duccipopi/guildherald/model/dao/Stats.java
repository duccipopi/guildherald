package com.duccipopi.guildherald.model.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ducci on 28/01/2018.
 */

public class Stats {

    private int health;
    private String powerType;
    private int power;

    @SerializedName("str")
    private int strength;
    @SerializedName("agi")
    private int agility;
    @SerializedName("int")
    private int intelligence;
    @SerializedName("sta")
    private int stamina;

    @SerializedName("cri")
    private float critical;
    @SerializedName("haste")
    private float haste;
    @SerializedName("mastery")
    private float mastery;
    @SerializedName("versatility")
    private float versatility;

    @SerializedName("armor")
    private int armor;
    @SerializedName("dodge")
    private float dodge;
    @SerializedName("parry")
    private float parry;
    @SerializedName("block")
    private float block;

    public Stats(int health, String powerType, int power, int strength, int agility,
                 int intelligence, int stamina, float critical, float haste, float mastery,
                 float versatility, int armor, float dodge, float parry, float block) {
        this.health = health;
        this.powerType = powerType;
        this.power = power;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.critical = critical;
        this.haste = haste;
        this.mastery = mastery;
        this.versatility = versatility;
        this.armor = armor;
        this.dodge = dodge;
        this.parry = parry;
        this.block = block;
    }

    public int getHealth() {
        return health;
    }

    public String getPowerType() {
        return powerType;
    }

    public int getPower() {
        return power;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getStamina() {
        return stamina;
    }

    public float getCritical() {
        return critical;
    }

    public float getHaste() {
        return haste;
    }

    public float getMastery() {
        return mastery;
    }

    public float getVersatility() {
        return versatility;
    }

    public int getArmor() {
        return armor;
    }

    public float getDodge() {
        return dodge;
    }

    public float getParry() {
        return parry;
    }

    public float getBlock() {
        return block;
    }
}
