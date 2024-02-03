package com.tove.demo;


import com.tove.demo.dbmanager.FightDao;
import com.tove.demo.dbmanager.MonsterDao;
import com.tove.demo.dbmanager.PlayerDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static com.tove.demo.Colors.*;

public class GamePlay {


    static Random rand = new Random();
    static String[] monsterNames = {"Bokoblin", "Lizalfos", "ChuChu", "Moblin", "Lynel"};

    PlayerDao playerDao = new PlayerDao();



    public static Player player;

    public void introduction() throws SQLException {

        System.out.println("Welcome to Dungeon Run!");

        System.out.println("""
                Choose one of the following:
                1. New game
                2. Load game
                3. Exit game
                """);

        switch (ScannerClass.getInput()) {
            case "1" -> newGame();
            case "2" -> {loadPlayer();
                playerHistory();}
            case "3" -> {
                System.out.println("You chose to exit game, bye!" );
                System.exit(0);
            }

            default -> System.out.println("Try again" );
        }

    }

    public static void newGame () throws SQLException {
        String name = "";
            System.out.println("Please input your name or username" );
            name = ScannerClass.getInput();
        player = new Player(name, 10, 5, 10, 40, 10);
             PlayerDao.createRecord(player);

        System.out.println("I see, your name is: " + player.getName());

    }

    public static void loadPlayer() {
        List<Player> players = PlayerDao.loadPlayers();
        System.out.println("Choose one player to load:");

        for (int i = 0; i < players.size(); i++) {
            Player tempPlayer = players.get(i);
            System.out.printf("%d - %s(level: %d) %n", i, tempPlayer.getName(), tempPlayer.getLevel());
        }

        boolean playerSelected = false;

        while(!playerSelected) {
            System.out.println("Select player index:");
            int selectedPlayer = ScannerClass.getInt();
            if (selectedPlayer < players.size() && selectedPlayer >= 0) {
                player = players.get(selectedPlayer);
                playerSelected = true;
            }

        }

    }

    private void playerHistory() {

            System.out.println("""
                Would you like to see your fight history?
                1. Yes
                2. No
                3. Exit game
                """);

        switch(ScannerClass.getInput()) {
            case "1" -> System.out.println("Number of fights: " + playerDao.loadPlayerHistory(player));
            case "2" -> System.out.println("You chose no, moving on");
            case "3" -> {
                System.out.println("You chose to exit game, bye!" );
                System.exit(0);
            }

            default -> System.out.println("Try again" );
        }


    }



    public static void startMenu() throws SQLException {

        do {
            System.out.println("""
                    Choose one of the following:
                    1. Status
                    2. Fight
                    3. Exit game
                    """);

            switch (ScannerClass.getInput()) {
                case "1" -> player.getStatus();
                case "2" -> fightMenu();
                case "3" -> {
                    System.out.println("You chose to exit game, bye!" );
                    System.exit(0);
                }

                default -> System.out.println("Try again" );
            }
        } while (!player.isDead());

    }

    static Monster generateMonster() {
        String name = monsterNames[rand.nextInt(monsterNames.length)];
        int strength = (player.getLevel() + 5) / 2;
        int agility = (player.getLevel() + 10) / 2;
        int health = player.getLevel() * rand.nextInt(1, 4) + rand.nextInt(20, 31);
        int intelligence = (player.getLevel() + 10) / 2;
        int baseDamage = rand.nextInt(5, 16);
        int xpReward = (int) ((health + baseDamage) * 0.8);

        if (name.equals("Lynel" )) {
            strength *= 2;
            agility *= 2;
            health *= 2;
            intelligence *= 2;
            baseDamage *= 2;
            xpReward *= 2;
        }
        return new Monster(name, strength, agility, health, intelligence, baseDamage, xpReward);


    }
    public static void fightMenu() throws SQLException {
        Monster monster = generateMonster();
        MonsterDao.createRecord(monster);
        do {

            System.out.println("""
                    Choose one of the following:
                    1. See status of the monster and you
                    2. Attack
                    3. Flee from the monster
                    4. Exit game
                    """);

            switch (ScannerClass.getInput()) {
                case "1" -> {
                    System.out.println("Your stats is: " );
                    player.getStatus();
                    System.out.println("The monster has the following stats:" );
                    monster.getStatus();
                }
                case "2" -> {
                    player.attack(monster);
                    if (!monster.isDead()) {
                        monster.attack(player);
                    }
                }
                case "3" -> {
                    player.flee();
                    if (!player.isHasFled()) {
                        monster.attack(player);
                    }
                }
                case "4" -> {
                    System.out.println("You chose to exit the game, bye!" );
                    System.exit(0);

                }
                default -> System.out.println("Try again" );

            }

            if (player.isDead()) {
                player.endBattle();
            } else if (monster.isDead()) {
                player.setExperience(player.getExperience() + monster.getXpReward());
                monster.endBattle();

            }

        } while (!player.isDead() && !monster.isDead() && !player.isHasFled());

        if (player.isHasFled()) {
            System.out.println("You succeeded to flee!" );
            player.setHasFled(false);
        }

        if (player.getExperience() >= 100) {
            player.levelUp();
        }


        PlayerDao.updatePlayer(player);
        FightDao.createRecord(player, monster);


        player.setHealth(player.getMaxHealth());

    }
}