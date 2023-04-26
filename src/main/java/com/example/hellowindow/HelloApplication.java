package com.example.hellowindow;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new ViewLoader().loadView("hello_view.fxml", stage, getClass());
    }

    public static void main(String[] args) {
//        launch();

        var test = new DatabaseHandler().getAllUsersRecord();
        System.out.println(test.length);
    }
}