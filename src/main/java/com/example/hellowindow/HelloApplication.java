package com.example.hellowindow;

import com.example.hellowindow.controllers.SignUpController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new ViewLoader().loadView("hello_view.fxml", stage, getClass());
    }

    public static void main(String[] args) {
        launch();

//        ArrayList<User> test = new DatabaseHandler().getAllUsers();
//        System.out.println(test.size());
    }
}