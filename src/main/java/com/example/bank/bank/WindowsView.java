package com.example.bank.bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class WindowsView {
    protected static HashMap<Integer, String> windows = new HashMap<>();

    private static int selected = 0;

    protected static WindowsView wv;

    @FXML
    protected StackPane pane;

    private final VBox vb = new VBox();

    @FXML
    protected void initialize() throws IOException {
        wv = this;
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("display-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Экран");
        stage.setScene(scene);
        stage.show();
        create();
        // -------------------------------
    }
    private ComboBox<String> cb;
    protected void create() {
        vb.getChildren().clear();
        pane.getChildren().clear();
        Button crw = new Button();
        crw.setText("Создать окно");

        crw.setOnAction(e -> {
            try {
                createWindow();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        ObservableList<String> w = FXCollections.observableArrayList();
        if (windows.size() > 0) {
            for (int i = 1; i <= windows.size(); i++) {
                w.add(i + "");
            }
        } else {
            w.add("Добавьте окно");
        }
        cb = new ComboBox<>(w);
        cb.setValue(selected == 0 ? "Добавьте окно" : selected + "");
        cb.setId("cb");
        vb.getChildren().add(cb);
        cb.setOnAction(actionEvent -> change());
        Button bt = new Button("Следующий клиент");
        bt.setOnAction(event -> next());
        vb.getChildren().add(bt);
        vb.getChildren().add(crw);
        pane.getChildren().add(vb);
    }

    @FXML
    protected void next() {
        for (int i = 0; i < Ticket.ticketList.size(); i++) {
            if (Ticket.ticketList.get(i) != null && Ticket.ticketList.get(i).purpose().equals(windows.get(selected))) {
                DisplayView.addTicket(i + 1, selected);
                Ticket.ticketList.set(i, null);
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(":(");
        alert.setHeaderText("Клиентов к вам нету 😔");

        alert.showAndWait();
    }

    protected void change() {
        selected = Integer.parseInt(cb.getSelectionModel().getSelectedItem().equals("Добавьте окно") ? "0" : cb.getSelectionModel().getSelectedItem());
        create();
    }

    @FXML
    protected void createWindow() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("windowCreate-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Создать окно");
        stage.setScene(scene);
        stage.show();
    }
}
