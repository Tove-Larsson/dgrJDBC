package com.tove.demo.dbmanager;

import com.tove.demo.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {

    public static void createRecord(Player player) throws SQLException {
        String sql = "INSERT INTO player(name, strength, intelligence, agility, health, basedamage, experience, level) " +
                "VALUE(?, 10, 5, 10, 40, 10, 0, 1)";

        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, player.getName());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                player.setPlayerID(rs.getInt(1));
                System.out.println(player.getPlayerID());
            }

        } catch (SQLException e) {

            System.out.println(e);

            throw e;
        }


    }

    public static List<Player> loadPlayers() {
        String sql = "SELECT PlayerID, Name, Strength, Intelligence, Agility, Health, Basedamage, Experience, Level " +
                "FROM player";
        List<Player> players = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int playerID = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int strength = resultSet.getInt(3);
                int intelligence = resultSet.getInt(4);
                int agility = resultSet.getInt(5);
                int health = resultSet.getInt(6);
                int basedamage = resultSet.getInt(7);
                int experience = resultSet.getInt(8);
                int level = resultSet.getInt(9);

                players.add(new Player(playerID, name, strength, agility, health, intelligence, basedamage, experience, level));

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return players;

    }


    public static void updatePlayer(Player player) {

        String sql = "UPDATE player " +
                "SET Strength=?, Intelligence=?, Agility=?, Health=?, Basedamage=?, Experience=?, Level=? WHERE PlayerID=?";

        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(sql);
            preparedStatement.setInt(1, player.getStrength());
            preparedStatement.setInt(2, player.getIntelligence());
            preparedStatement.setInt(3, player.getAgility());
            preparedStatement.setInt(4, player.getMaxHealth());
            preparedStatement.setInt(5, player.getBaseDamage());
            preparedStatement.setInt(6, player.getExperience());
            preparedStatement.setInt(7, player.getLevel());
            preparedStatement.setInt(8, player.getPlayerID());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e);

        }

    }

    public int loadPlayerHistory(Player player) {
        int numberOfFights = -1;

        String sql = """
                SELECT count(*) AS NumberOfFights
                FROM fighthistory f
                WHERE f.PlayerID = ?
                """;

        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(sql);
            preparedStatement.setInt(1, player.getPlayerID());


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                numberOfFights = resultSet.getInt(1);

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return numberOfFights;

    }
}
