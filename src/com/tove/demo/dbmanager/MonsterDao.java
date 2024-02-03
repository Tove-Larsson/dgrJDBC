package com.tove.demo.dbmanager;

import com.tove.demo.Monster;
import com.tove.demo.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonsterDao {

    public static void createRecord(Monster monster) throws SQLException {

            String sql = "INSERT INTO monster(name, strength, intelligence, agility, health, basedamage) " +
                    "VALUE(?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, monster.getName());
                preparedStatement.setInt(2, monster.getStrength());
                preparedStatement.setInt(3, monster.getIntelligence());
                preparedStatement.setInt(4, monster.getAgility());
                preparedStatement.setInt(5, monster.getHealth());
                preparedStatement.setInt(6, monster.getBaseDamage());
                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    monster.setMonsterID(rs.getInt(1));
                    System.out.println(monster.getMonsterID());
                }

            } catch (SQLException e) {

                System.out.println(e);

                throw e;
            }

    }

}
