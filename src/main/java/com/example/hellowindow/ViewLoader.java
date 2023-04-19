package com.example.hellowindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ViewLoader {

    public void loadView(String fxmlName, Stage stage, Class caller) {
        FXMLLoader fxmlLoader = new FXMLLoader(caller.getResource(fxmlName));
        try {
            InputStream iconStream =
                    new FileInputStream("src/main/resources/com/example/hellowindow/assets/icon.png");
            Image image = new Image(iconStream);
            stage.getIcons().add(image);
            stage.setTitle("First JavaFX");
            stage.setScene(new Scene(fxmlLoader.load(), 700, 400));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
