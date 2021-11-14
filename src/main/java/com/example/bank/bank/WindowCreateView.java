package com.example.bank.bank;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class WindowCreateView {
    @FXML
    protected ComboBox<String> cb;

    @FXML
    protected void onCreateClick() {
        if (cb.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Внимание!");

            alert.setHeaderText("Укажите тип окна!");

            alert.showAndWait();
        } else {
            WindowsView.windows.put(WindowsView.windows.size() + 1, cb.getSelectionModel().getSelectedItem());
            WindowsView.wv.create();
            Stage stage = (Stage) cb.getScene().getWindow();
            stage.close();
        }
    }
}
