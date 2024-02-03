package com.tove.demo;

import com.tove.demo.dbmanager.DBConnection;
import com.tove.demo.dbmanager.FightDao;
import com.tove.demo.dbmanager.PlayerDao;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        DBConnection dbConnection = new DBConnection();

        GamePlay gamePlay = new GamePlay();

        dbConnection.open();

        dbConnection.createTablePlayer();

        dbConnection.createTableMonster();

        dbConnection.createTableFightHistory();

        gamePlay.introduction();

        GamePlay.startMenu();


    }

}
