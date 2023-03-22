package com.example.hellowindow;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends ConfigConst { //throws ClassBotFoundException

    Connection dbConnect;
    public Connection getDbConnect() {

        try {
            // Начиная с JDBC 4+ не нужно
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            dbConnect = DriverManager.getConnection(
                    String.format("jdbc:mysql://%s/%s?user=%s&password=%s", dbHost, dbName, dbUser, dbPass));
            System.out.println("Connected to MySQL");

            System.out.printf("Metadata:%s%ngetCatalog: %s%ngetClientInfo: %s%ngetSchema: %s%n",
                    dbConnect.getMetaData(), dbConnect.getCatalog(), dbConnect.getClientInfo(), dbConnect.getSchema());

        dbConnect.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
//        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
//               | IllegalAccessException | InvocationTargetException e) {
//            System.out.println(e.getMessage());
//        }
        return dbConnect;
    }

}
