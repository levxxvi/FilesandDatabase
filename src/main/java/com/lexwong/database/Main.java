package com.lexwong.database;

import org.apache.commons.io.FileUtils;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static DatabaseHandler handler;

    public static void main(String[] args) {
        handler = DatabaseHandler.getHandler();
    }

    public static void addMember(String id, String name, String email, String nickName){
        String qu = "INSERT INTO MEMBER VALUES (" +
                "'" + id + "'," +
                "'" + name + "'," +
                "'" + email + "'," +
                "'" + nickName + "'," + ")";
        handler.execAction(qu);
    }
}
