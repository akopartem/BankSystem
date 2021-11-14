package com.example.bank.bank;

import javafx.application.Platform;

public class Clear extends Thread {
    public Clear() {
        super("Cleaner");
    }

    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Тред покинул нас, увы(");
            Platform.runLater(() -> DisplayView.av.getChildren().clear());
            return;
        }
        Platform.runLater(() -> DisplayView.av.getChildren().clear());
    }
}
