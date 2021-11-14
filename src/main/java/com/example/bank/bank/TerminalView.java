package com.example.bank.bank;


import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TerminalView {
    private Mode mode = Mode.MORNING;
    private final String[] forMorning = {"Кредиты", "Бизнес", "Кредитные карты", "Платежи"};
    private final String[] forAfterNoon = {"Документы"};

    @FXML
    protected StackPane pane;

    @FXML
    protected void initialize() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("windows-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Окна");
        stage.setScene(scene);
        stage.show();
        btnsCt();
    }

    VBox vb = new VBox();

    protected void btnsCt() {
        vb.getChildren().clear();
        pane.getChildren().clear();
        for (String elem : mode == Mode.MORNING ? forMorning : forAfterNoon) {
            Button btn = new Button(elem);
            btn.setOnAction(actionEvent -> {
                Stage stage1 = new Stage();
                AnchorPane gr = new AnchorPane();
                Label l = new Label();
                VBox v = new VBox();
                l.setText("Талон номер " + (Ticket.ticketList.size() + 1) + ". \nЦель визита: " + elem);
                v.getChildren().add(l);
                Ticket.getTicket(elem);
                stage1.initModality(Modality.NONE);
                stage1.setTitle("Талон номер " + Ticket.ticketList.size());
                Scene scene1 = new Scene(gr, 500, 100);
                v.styleProperty().bind(Bindings.concat("-fx-font-size: 30; -fx-base: rgb(100,100,100);"));
                gr.getChildren().add(v);
                stage1.setScene(scene1);
                stage1.show();
            });
            vb.getChildren().add(btn);
        }
        pane.getChildren().add(vb);
        Button b = new Button("Сменить режим");
        b.setOnAction(e -> changeMode());
        pane.getChildren().add(b);
    }

    @FXML
    protected void changeMode() {
        mode = mode == Mode.MORNING ? Mode.AFTERNOON : Mode.MORNING;
        vb = new VBox();
        btnsCt();
    }
}
