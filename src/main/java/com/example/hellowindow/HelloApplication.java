package com.example.hellowindow;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new ViewLoader().loadView("hello_view.fxml", stage, getClass());
    }

    public static void main(String[] args) {
        launch();

//        User[] dbRecord = new DatabaseHandler().getAllUsersRecord();
//        ObservableList<String> names = FXCollections.observableArrayList();
//        for (int i = 0; i < dbRecord.length; i++) {
//            names.add(String.format("%s %s", dbRecord[i].getFirstname(), dbRecord[i].getLastname()));
//        }
//        System.out.println(names.get(100));
    }
}