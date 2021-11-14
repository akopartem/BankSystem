package com.example.bank.bank;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DisplayView {
    @FXML
    protected VBox v;

    protected static VBox av;

    @FXML
    protected void initialize() {
        av = v;
    }

    protected static void addTicket(int num, int w) {
        Label l = new Label(String.format("%d -> %d", num, w));
        if (av.getChildren().size() == 5) {
            av.getChildren().remove(0);
        }
        l.setFont(new Font(30));
        av.getChildren().add(l);
        new Clear().start();
    }


}
