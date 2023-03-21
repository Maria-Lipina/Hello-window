package com.example.hellowindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLoader {

    public void loadView(String fxmlName, Stage stage, Class caller) {
        FXMLLoader fxmlLoader = new FXMLLoader(caller.getResource(fxmlName));
        stage.setTitle("First JavaFX");
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 700, 400));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
