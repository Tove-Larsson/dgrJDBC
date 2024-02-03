package com.tove.demo.dbmanager;


import com.tove.demo.Monster;
import com.tove.demo.Player;

import java.sql.*;
import java.util.Calendar;

public class FightDao {

    public static void createRecord(Player player, Monster monster) throws SQLException {
        String sql = "INSERT INTO fighthistory(playerID, monsterID, didflee, didwin, timestamp) " +
                "VALUE(?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, player.getPlayerID());
            preparedStatement.setInt(2, monster.getMonsterID());
            preparedStatement.setBoolean(3, player.isHasFled());
            preparedStatement.setBoolean(4, monster.isDead());
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStatement.execute();

        } catch (SQLException e) {

            System.out.println(e);

            throw e;
        }

    }



}


