package ru.geekbrains.server;

import java.sql.*;

public class SQLHandler {
    private static Connection connection;
    private static Statement statement;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:server/database.db");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPassword(String login, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("select nickname from users where login = ? and password = ?");
            ps.setString (1, login);
            ps.setString (2, password);
            ResultSet rs = ps.executeQuery();
            //System.out.println(rs.next());
            //System.out.println(rs.getString("nickname"));
            //ResultSet rs = statement.executeQuery("SELECT nickname FROM users WHERE login ='" + login + "' AND password = '" + password + "'");

            if (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
