package com.example.hellowindow.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;

    public Shake (Node node) {
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0f);
        tt.setFromY(0f);
        tt.setByX(10f);
        tt.setByY(15f);
        tt.setCycleCount(4);
        tt.setAutoReverse(true);
    }

    public void playAnim() {
        tt.playFromStart();
    }
}
