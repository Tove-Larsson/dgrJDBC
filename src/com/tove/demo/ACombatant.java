package com.tove.demo;
import java.util.Random;

import static com.tove.demo.Colors.*;
import static com.tove.demo.Colors.RESET;

public abstract class ACombatant {

    static Random rand = new Random();
    private final String name;
    private int strength;
    private int agility;
    private int health;
    private int maxHealth;
    private int intelligence;
    private int baseDamage;

    private boolean isDead;

    public ACombatant(String name, int strength, int agility, int maxHealth, int intelligence, int baseDamage) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.intelligence = intelligence;
        this.baseDamage = baseDamage;
        this.isDead = false;
    }

    // damagedealt = basedamage + rand origin 1, strenght. om rand är mellan 1-100 och mindre än intelligens
    // damage dealt += strenght * 1.5

    public int calculateDamage(ACombatant target) {
        int damageDealt = this.getBaseDamage() + rand.nextInt(1, this.getStrength());
        if(rand.nextInt(1,101) < this.getIntelligence()) {
            damageDealt += (int) (this.getStrength() * 1.5);
        }
        if(didDodge(target.getAgility())){
            System.out.printf( GREEN + "%s " + RESET + "Dodged %n", target.getName());
            damageDealt = 0;

        }
        return damageDealt;
    }

    public boolean didDodge(int targetAgility) {
        return rand.nextInt(1, 101) < targetAgility;

    }

    public void receiveDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
        if(this.health <= 0) {
            this.setDead(true);
        }

    }

    public void endBattle() {
        System.out.printf(GREEN + "%s " + RESET + "died a horrific " + RED + "death %n" + RESET, this.name);
    }


    public void attack(ACombatant target) {
        int damage = this.calculateDamage(target);
        target.receiveDamage(damage);
        System.out.printf(GREEN + "%s " + RESET + "attacked " + RED + "%s " + RESET + "with " + BLUE + "%d damage %n " + RESET,
                this.name, target.name, damage);


    }


    public void getStatus() {
        System.out.printf("Name: %s %n", this.getName());
        System.out.printf(RED + "Strength: %d %n" + RESET, this.getStrength());
        System.out.printf(BLUE + "Intelligence: %d %n", this.getIntelligence());
        System.out.printf(PURPLE + "Agility: %d %n", this.getAgility());
        System.out.printf(RED + "Health: %d / %d %n", this.getHealth(), this.getMaxHealth());
        System.out.printf(CYAN + "BaseDamage: %d %n" + RESET, this.getBaseDamage());
    }


    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}

