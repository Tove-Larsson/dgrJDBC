package com.tove.demo.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

        private String URL = "jdbc:mariadb://localhost:3306/dungeonrun";
        private String USER = "root";
        private String password = "Your root password here";

        static Connection connection;

    public void open () {
            try {
                connection = DriverManager.getConnection(URL, USER, password);
                System.out.println("Database connected");

            } catch (
                    SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    public void createTablePlayer() {
        String sql = "CREATE TABLE IF NOT EXISTS player " + "(PlayerID INT NOT NULL AUTO_INCREMENT, Name VARCHAR(30), Strength INT, Intelligence INT, Agility INT, Health INT, Basedamage INT, Experience INT, Level INT, primary KEY(playerID))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    public void createTableMonster() {
        String sql = "CREATE TABLE IF NOT EXISTS monster " + "(MonsterID INT NOT NULL AUTO_INCREMENT, Name VARCHAR(30), Strength INT, Intelligence INT, Agility INT, Health INT, Basedamage INT, primary KEY(monsterID))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    public void createTableFightHistory() {
        String sql = "CREATE TABLE IF NOT EXISTS fighthistory " + "(FightID INT NOT NULL AUTO_INCREMENT, " +
                "Timestamp datetime NOT NULL, " +
                "PlayerID INT, MonsterID INT, primary KEY(fightID), " +
                "Didflee boolean, " +
                "Didwin boolean, " +
                "foreign KEY(PlayerID) references player(PlayerID), " +
                "foreign KEY(MonsterID) references monster(MonsterID))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {

            System.out.println(e);
        }
    }
}
