package com.duccipopi.guildherald.model.dao;

/**
 * Created by ducci on 04/02/2018.
 */

public class Member {

    private int rank;
    private Character character;

    public Member(int rank, Character character) {
        this.rank = rank;
        this.character = character;
    }

    public int getRank() {
        return rank;
    }
    public Character getCharacter() {
        return character;
    }
}
