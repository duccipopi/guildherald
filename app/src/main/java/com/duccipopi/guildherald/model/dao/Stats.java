package com.duccipopi.guildherald.model.dao;

/**
 * Created by ducci on 28/01/2018.
 */

public class Stats {

    private int health;
    private String powerType;
    private int power;

    private int strength;
    private int agility;
    private int intelligence;
    private int stamina;

    private float critical;
    private float haste;
    private float mastery;
    private float versatility;

    public Stats(int health, String powerType, int power, int strength, int agility,
                 int intelligence, int stamina, float critical, float haste, float mastery,
                 float versatility) {
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
}
