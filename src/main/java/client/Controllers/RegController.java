package client.Controllers;

import client.WorkWithServer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class RegController {
    WorkWithServer wws = null;

    @FXML
    private TextField loginRegField;

    @FXML
    private PasswordField passRegField;

    @FXML
    private Button backButton;

    @FXML
    private Button regButton;

    @FXML
    private PasswordField confirmPassRegField;

    @FXML
    void backButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void regButtonAction(ActionEvent event) throws ClassNotFoundException {
        if (loginRegField.getText().length() >= 5 && passRegField.getText().length() >= 5 && confirmPassRegField.getText().length() >= 5 && confirmPassRegField.getText().equals(passRegField.getText())) {
            wws.registerNewUser(loginRegField.getText(), passRegField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText("Новый пользователь зарегистрирован");
            alert.showAndWait();
            Stage stage = (Stage) regButton.getScene().getWindow();
            stage.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка регистрации");
            alert.setHeaderText("Пользователь не зарегистрирован");
            alert.setContentText("Неправильно заполнены поля, проверьте данные");
            alert.showAndWait();
        }
    }

    @FXML
    void checkInput(KeyEvent event) {
        if (loginRegField.getText().length() < 5) {
            loginRegField.setStyle("-fx-border-color: RED");
        }
        else {
            loginRegField.setStyle("-fx-border-color: GREEN");
        }
        if (passRegField.getText().length() < 5) {
            passRegField.setStyle("-fx-border-color: RED");
        }
        else {
            passRegField.setStyle("-fx-border-color: GREEN");
        }
        if (confirmPassRegField.getText().length() < 5 || !confirmPassRegField.getText().equals(passRegField.getText())) {
            confirmPassRegField.setStyle("-fx-border-color: RED");
        }
        else {
            confirmPassRegField.setStyle("-fx-border-color: GREEN");
        }
    }

    @FXML
    void initialize() throws IOException {
        wws = new WorkWithServer();
    }
}
