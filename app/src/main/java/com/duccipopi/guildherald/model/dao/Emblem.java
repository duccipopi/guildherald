package com.duccipopi.guildherald.model.dao;

/**
 * Created by ducci on 28/01/2018.
 */

public class Emblem {

    private int icon;
    private String iconColor;
    private int iconColorId;
    private int border;
    private String borderColor;
    private int borderColorId;
    private String backgroundColor;
    private int backgroundColorId;

    public Emblem(int icon, String iconColor, int iconColorId, int border, String borderColor,
                  int borderColorId, String backgroundColor, int backgroundColorId) {
        this.icon = icon;
        this.iconColor = iconColor;
        this.iconColorId = iconColorId;
        this.border = border;
        this.borderColor = borderColor;
        this.borderColorId = borderColorId;
        this.backgroundColor = backgroundColor;
        this.backgroundColorId = backgroundColorId;
    }

    public int getIcon() {
        return icon;
    }

    public String getIconColor() {
        return iconColor;
    }

    public int getIconColorId() {
        return iconColorId;
    }

    public int getBorder() {
        return border;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public int getBorderColorId() {
        return borderColorId;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public int getBackgroundColorId() {
        return backgroundColorId;
    }
}
