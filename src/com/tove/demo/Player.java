package com.tove.demo;

import static com.tove.demo.Colors.*;
import static com.tove.demo.Colors.RESET;

public class Player extends ACombatant{
    private int experience;
    private int level;
    private boolean hasFled;

    private int playerID;

    public Player(int playerID, String name, int strength, int agility, int maxHealth, int intelligence, int baseDamage, int experience, int level) {
        super(name, strength, agility, maxHealth, intelligence, baseDamage);
        this.playerID = playerID;
        this.experience = experience;
        this.level = level;
    }

    public Player(String name, int strength, int intelligence, int agility, int maxHealth, int baseDamage) {
        super(name, strength, agility, maxHealth, intelligence, baseDamage);
        this.experience = 0;
        this.level = 1;
    }


    @Override
    public void getStatus() {
        super.getStatus();
        System.out.printf(GREEN + "Experience: %d / 100 %n", this.getExperience());
        System.out.printf(YELLOW + "Level: %d %n" + RESET, this.getLevel());
    }

    @Override
    public void endBattle() {
        super.endBattle();
        System.out.printf(RED + "Game over, try again %n" + RESET);


    }

    public void flee() {
        System.out.println("You chose to flee you coward, will you succeed?");
        if(rand.nextInt(1, 41) < this.getAgility()) {
            this.setHasFled(true);
        } else {
            System.out.println("You did not succeed to flee!");
        }

    }

    public void levelUp() {
        this.setExperience(this.getExperience() - 100);
        this.setLevel(this.getLevel() + 1);
        this.setStrength(this.getStrength() + 2);
        this.setAgility(this.getAgility() + 2);
        this.setMaxHealth(this.getMaxHealth() + 2);
        this.setBaseDamage(this.getBaseDamage() + 2);
        this.setIntelligence(this.getIntelligence() + 2);
    }


    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isHasFled() {
        return hasFled;
    }

    public void setHasFled(boolean hasFled) {
        this.hasFled = hasFled;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}

