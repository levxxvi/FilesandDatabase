package com.lexwong.database;

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_url = "jdbc:derby:database/forum;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
    public static DatabaseHandler handler;


    public DatabaseHandler(){
        createConnection();
    }

    public static DatabaseHandler getHandler(){
        if(handler == null){
            handler = new DatabaseHandler();
            return handler;
        } else{
            return handler;
        }
    }

    public void createTable(String directory) {
        String TABLE_NAME = directory;
        try{
            stmt = conn.createStatement();
            DatabaseMetaData dmn = conn.getMetaData();
            ResultSet tables = dmn.getTables(null, null, TABLE_NAME, null);
            if (tables.next()){
                System.out.println("Table " + TABLE_NAME + " exists");
            }else{
                String statement = "CREATE TABLE " + TABLE_NAME + " ("
                        + "fileName varchar(200) primary key, \n"
                        + "path varchar(200), \n"
                        + "extension varchar(200), \n"
                        + "sizeBytes varchar(200))";
                System.out.println(statement);
                stmt.execute(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(DB_url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean execAction(String qu){
        try{
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException e){
            System.out.println(e);
            System.out.println("Did not enter date");
        }
        return false;
    }

    public ResultSet execQuery(String query){
        ResultSet resultSet;
        try {
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return resultSet;
    }
}
