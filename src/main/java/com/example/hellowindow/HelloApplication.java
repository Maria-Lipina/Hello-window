package com.example.hellowindow;

import com.example.hellowindow.controllers.SignUpController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new ViewLoader().loadView("hello-view.fxml", stage, getClass());
    }

    public static void main(String[] args) {
        launch();
    }
}