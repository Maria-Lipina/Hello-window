package com.example.hellowindow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class DatabaseHandler {

    private Connection dbConnect;
    private Properties props;

    private ResultSet dbOutput;

    public DatabaseHandler () {

    }
    private Connection connect() {
        try {
            if (dbConnect == null || dbConnect.isClosed()) {
                props = new Properties();
                props.load(Files.newInputStream(
                        Path.of("src/main/resources/com/example/hellowindow/assets/database.properties")));
                dbConnect = DriverManager.getConnection(
                        (String) props.get("url"), (String) props.get("db_user"), (String) props.get("password"));
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

    private void close() {
        try {
            if (!dbConnect.isClosed()) {
                dbConnect.close();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    private int getCount() {
        int res = 0;
        try {
            PreparedStatement prst = dbConnect.prepareStatement(
                    String.format("SELECT COUNT(%s) FROM %s",
                            props.get("user_id"), props.get("user_table")),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            dbOutput = prst.executeQuery();
            dbOutput.next();
            res = dbOutput.getInt(1);
            dbOutput.beforeFirst();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return res;
    }

    public int getUserCount() {
        this.connect();
        int res = this.getCount();
        this.close();
        return res;
    }

    private void addUser(String name, String lastname, String email, int passHash){
        try {
            String statement = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                    props.get("user_table"),
                    props.get("user_firstname"), props.get("user_lastname"),
                    props.get("user_email"), props.get("user_pass_hash"));

            PreparedStatement prSt = dbConnect.prepareStatement(statement);
            prSt.setString(1, name);
            prSt.setString(2, lastname);
            prSt.setString(3, email);
            prSt.setInt(4, passHash);

            prSt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
    public void addUserRecord(String name, String lastname, String email, int passHash) {
        this.connect();
        this.addUser(name, lastname, email, passHash);
        this.close();
    }

    private int findUser(String login) {
        int result = 0;
        try {
            String statement = String.format("SELECT * FROM %s WHERE %s=?",
                    this.props.get("user_table"), this.props.get("user_email"));

            PreparedStatement prSt = dbConnect.prepareStatement(statement,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            prSt.setString(1, login);
            this.dbOutput = prSt.executeQuery();
            if (this.dbOutput.next()) {
                result = dbOutput.getInt(5);
                dbOutput.beforeFirst();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return result;
    }

    public boolean isUser(String login, int passHash) {
        this.connect();
        return findUser(login) == passHash;
    }

    public boolean isUser(String login) {
        this.connect();
        return findUser(login) != 0;
    }

    private User makeRecord() {
        User user = new User();
        try {
           if(this.dbOutput.next()) {
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
        return user;
    }

    public User getUserRecord() {
        this.connect();
        User user = makeRecord();
        this.close();
        return user;
    }

    private void getAllUsers() {

        String statement = String.format("SELECT %s, %s, %s, %s FROM %s",
                this.props.get("user_id"),
                this.props.get("user_firstname"),
                this.props.get("user_lastname"),
                this.props.get("user_email"),
                this.props.get("user_table"));

        try {
            Statement stm = dbConnect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            dbOutput = stm.executeQuery(statement);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }


    public User[] getAllUsersRecord(){
        this.connect();
        int capacity = this.getCount();
        this.getAllUsers();
        User[] result = new User[capacity];
        for (int i = 0; i < capacity; i++) {
            result[i] = makeRecord();
        }
        this.close();
        return result;
    }

}
