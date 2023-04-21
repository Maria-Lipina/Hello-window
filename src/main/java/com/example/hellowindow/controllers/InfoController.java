package com.example.hellowindow.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.hellowindow.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class InfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;

    @FXML
    private ScrollPane textPane;

    @FXML
    private Text aboutUsTextOut;

    @FXML
    void initialize() {
        signIn.setOnAction(actionEvent -> {
            signIn.getScene().getWindow().hide();
            new ViewLoader().loadView("logout_view.fxml", new Stage(), getClass());
            System.out.println("Returned back to sign In form");
        });
        signUp.setOnAction(actionEvent -> {
            signUp.getScene().getWindow().hide();
            new ViewLoader().loadView("signup_view.fxml", new Stage(), getClass());
        });

        aboutUsTextOut.setText("""
                Есть много вариантов Lorem Ipsum, но большинство из них имеет не всегда приемлемые модификации, например, юмористические вставки или слова, которые даже отдалённо не напоминают латынь. Если вам нужен Lorem Ipsum для серьёзного проекта, вы наверняка не хотите какой-нибудь шутки, скрытой в середине абзаца. Также все другие известные генераторы Lorem Ipsum используют один и тот же текст, который они просто повторяют, пока не достигнут нужный объём. Это делает предлагаемый здесь генератор единственным настоящим Lorem Ipsum генератором. Он использует словарь из более чем 200 латинских слов, а также набор моделей предложений. В результате сгенерированный Lorem Ipsum выглядит правдоподобно, не имеет повторяющихся абзацей или "невозможных" слов.
                """);


    }


}
