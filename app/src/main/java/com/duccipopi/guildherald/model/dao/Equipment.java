package com.duccipopi.guildherald.model.dao;

/**
 * Created by ducci on 28/01/2018.
 */

public class Equipment {

    private String name;
    private String icon;
    private int quality;
    private int itemLevel;
    private String slot;

    public Equipment(String name, String icon, int quality, int itemLevel, String slot) {
        this.name = name;
        this.icon = icon;
        this.quality = quality;
        this.itemLevel = itemLevel;
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public int getQuality() {
        return quality;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public String getSlot() {
        return slot;
    }
}
