package com.example.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Users {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getUserByID(String id) {
        Users user = new Users();
        try {
            Class.forName("org.sqlite.JDBC");

            //String url = "jdbc:sqlite:./sqlite.db";
            String url = "jdbc:sqlite:/media/kali/Локальний диск/Linux/Code/JAVA/kaiwa/src/main/db/test.db";  // DYNAMIC !!!

            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE id = " + id);

            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
