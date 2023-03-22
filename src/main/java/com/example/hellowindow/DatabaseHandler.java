package com.example.hellowindow;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseHandler extends ConfigConst {

    Connection dbConnect;
    public Connection connect() {

        try {
            Properties props = new Properties();
            props.load(Files.newInputStream(
                   Path.of("src/main/resources/com/example/hellowindow/assets/database.properties")));
            dbConnect = DriverManager.getConnection(
                    (String) props.get("url"), (String) props.get("user"), (String) props.get("password"));

            System.out.println("Connected to MySQL");

            dbConnect.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbConnect;
    }

    public boolean isClose() {
        try {
            return dbConnect.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
