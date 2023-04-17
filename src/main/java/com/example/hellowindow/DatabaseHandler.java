package com.example.hellowindow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DatabaseHandler {

    Connection dbConnect;
    Properties props;

    public DatabaseHandler () {

    }
    public Connection connect() {
        try {
            if (dbConnect == null || dbConnect.isClosed()) {
                props = new Properties();
                props.load(Files.newInputStream(
                        Path.of("src/main/resources/com/example/hellowindow/assets/database.properties")));
                dbConnect = DriverManager.getConnection(
                        (String) props.get("url"), (String) props.get("db_user"), (String) props.get("password"));
                System.out.println("Connected to MySQL");
                } }
                catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                    System.out.println("SQLState: " + e.getSQLState());
                    System.out.println("VendorError: " + e.getErrorCode());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
        return dbConnect;
    }

    public void close() {
        try {
            if (!dbConnect.isClosed()) {
                dbConnect.close();
                System.out.println("Exited from MySQL");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public int getCount() { //Запрос выполняется, но как вытащить из него результат? Ни строки ни столбца как бы нету

        int res = 0;
        try {
            props = new Properties();
            props.load(Files.newInputStream(
                    Path.of("src/main/resources/com/example/hellowindow/assets/database.properties")));
            String statement = String.format("SELECT COUNT(%s) FROM %s",
                    props.get("user_id"), props.get("user_table"));
            System.out.println(statement);
            PreparedStatement prst = connect().prepareStatement(
                    statement);
            ResultSet rs = prst.executeQuery();
            System.out.println("row: " + rs.first());

            dbConnect.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    public void addUser (String name, String lastname, String email, int passHash) {
        Connection db = this.connect();
        try {
            String statement = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                    props.get("user_table"),
                    props.get("user_firstname"), props.get("user_lastname"),
                    props.get("user_email"), props.get("user_pass_hash"));

            System.out.println(statement);

            PreparedStatement prSt = db.prepareStatement(statement);
            prSt.setString(1, name);
            prSt.setString(2, lastname);
            prSt.setString(3, email);
            prSt.setInt(4, passHash);

            prSt.executeUpdate();

            System.out.println("User added to DB");

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        this.close();
    }

    public boolean getUser(String login, int passHash) {
        Connection db = this.connect();
        boolean result = false;
        try {
            String statement = String.format("SELECT * FROM %s WHERE %s=? AND %s=?",
                    props.get("user_table"),
                    props.get("user_email"), props.get("user_pass_hash"));

            PreparedStatement prSt = db.prepareStatement(statement);
            prSt.setString(1, login);
            prSt.setInt(2, passHash);
            ResultSet rs = prSt.executeQuery();

            int counter = 0;
            while(rs.next()) counter++;
            if (counter >= 1) {
                result = true;
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        this.close();
        return result;
    }
}
