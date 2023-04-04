package com.example.hellowindow;

import com.example.hellowindow.controllers.SignUpController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new ViewLoader().loadView("hello-view.fxml", stage, getClass());
    }

    public static void main(String[] args) {
        User sgc = new User();
        String pass = sgc.passGen(8);
        var usersCount = new DatabaseHandler().getCount();
        System.out.println("Кол-во пользователей из бд " + usersCount);

        launch();
    }
}