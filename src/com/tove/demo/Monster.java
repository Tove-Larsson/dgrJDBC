package com.tove.demo;

public class Monster extends ACombatant{

    private final int xpReward;

    private int monsterID;


    public Monster(String name, int strength, int intelligence, int agility, int maxHealth, int baseDamage, int xpReward) {
        super(name, strength, agility, maxHealth, intelligence, baseDamage);
        this.xpReward = xpReward;
    }

    public int getXpReward() {
        return xpReward;
    }

    public void endBattle() {
        super.endBattle();
        this.setDead(true);
    }

    public int getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }


}