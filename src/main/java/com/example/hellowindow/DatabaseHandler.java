package com.example.hellowindow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;


public class DatabaseHandler {

    private Connection dbConnect;
    private Properties props;

    private ResultSet dbOutput;

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

    public int getCount() {
        int res = 0;
        try {
            PreparedStatement prst = connect().prepareStatement(
                    String.format("SELECT COUNT(%s) FROM %s",
                            props.get("user_id"), props.get("user_table")));
            dbOutput = prst.executeQuery();
            dbOutput.next();
            res = dbOutput.getInt(1);
            dbConnect.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
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
                    this.props.get("user_table"),
                    this.props.get("user_email"), props.get("user_pass_hash"));

            PreparedStatement prSt = db.prepareStatement(statement,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            prSt.setString(1, login);
            prSt.setInt(2, passHash);
            this.dbOutput = prSt.executeQuery();

            if (this.dbOutput.next()) result = true;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return result;
    }

    public User getUserRecord() {
        User user = new User();
        try {
            dbOutput.beforeFirst();
            while (this.dbOutput.next()) {
                user.setId(dbOutput.getInt(1));
                user.setFirstname(dbOutput.getString(2));
                user.setLastname(dbOutput.getString(3));
                user.setEmail(dbOutput.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        this.close();
        return user;
    }

}
