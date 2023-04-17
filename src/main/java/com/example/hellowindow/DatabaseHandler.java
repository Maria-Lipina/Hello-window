package com.example.hellowindow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;


public class DatabaseHandler {

    Connection dbConnect;
    Properties props;

    ResultSet dbOutput;

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
            dbOutput = prst.executeQuery();
            System.out.println("row: " + dbOutput.first());

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
                    this.props.get("user_table"),
                    this.props.get("user_email"), props.get("user_pass_hash"));

            PreparedStatement prSt = db.prepareStatement(statement,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            prSt.setString(1, login);
            prSt.setInt(2, passHash);
            this.dbOutput = prSt.executeQuery();

            if (this.dbOutput.next()) return true;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return result;
    }

    public String getUserRecord (String login, int passHash) {
        StringBuilder sb = new StringBuilder();
        this.getUser(login, passHash);
        try {
            dbOutput.beforeFirst();
            while (this.dbOutput.next()) {
                sb.append("id: ").append(dbOutput.getInt(1));
                sb.append(", firstName: ").append(dbOutput.getString(2));
                sb.append(", lastName: ").append(dbOutput.getString(3));
                sb.append(", email: ").append(dbOutput.getString(4)).append("\n");
        }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        this.close();
        return sb.toString();
    }

}
