package com.example.hellowindow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

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
                System.out.println("Reconnected to MySQL");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public int getCount() {

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

}
//Rfr