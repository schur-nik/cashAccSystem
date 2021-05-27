package client.Controllers;

import client.WorkWithServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {
    WorkWithServer wws = null;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainFrame.fxml"));
    MainController mainController = null;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button authButton;

    @FXML
    private Button regButton;

    @FXML
    void regButtonAction(ActionEvent event) throws ClassNotFoundException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/regFrame.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Registration");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void authButtonAction(ActionEvent event) throws ClassNotFoundException {
        int idUser = wws.auth(loginField.getText(), passField.getText());
        if (idUser > 0) {
            mainController.setIdUserAndLogin(idUser, loginField.getText());
            Stage stage = (Stage) authButton.getScene().getWindow();
            stage.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Аутентификация");
            alert.setHeaderText("Ошибка входа");
            alert.setContentText("Неправильно введен логин/пароль");
            alert.showAndWait();
            loginField.setText("");
            passField.setText("");
        }
    }

    @FXML
    void initialize() throws IOException {
        wws = new WorkWithServer();
        loader.load();
        mainController = loader.<MainController>getController();
    }

}
