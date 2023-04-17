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
        String user = new DatabaseHandler().getUserRecord("MARY.SMITH@sakilacustomer.org", -1414346094);
        System.out.println(user);


//        launch();
    }
}