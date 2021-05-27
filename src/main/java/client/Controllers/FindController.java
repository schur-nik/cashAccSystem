package client.Controllers;

import client.WorkWithServer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.AbsModels.Contracts;

import java.io.IOException;
import java.sql.Date;

public class FindController {
    WorkWithServer wws = null;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainFrame.fxml"));
    MainController mainController = null;

    @FXML
    private Button selectButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField incomeField;

    @FXML
    private TextField consField;

    @FXML
    private TableView<Contracts> tableFind;

    @FXML
    private TableColumn<Contracts, Integer> idColumn;

    @FXML
    private TableColumn<Contracts, String> nameColumn;

    @FXML
    private TableColumn<Contracts, Integer> incomeColumn;

    @FXML
    private TableColumn<Contracts, Integer> consColumn;

    @FXML
    private TableColumn<Contracts, Date> startColumn;

    @FXML
    private TableColumn<Contracts, Date> endColumn;

    @FXML
    private Button findButton1;


    @FXML
    void selectButtonAction(ActionEvent event) {
        Contracts findContract = tableFind.getSelectionModel().getSelectedItem();
        mainController.setDataInFindContract(findContract.getIdContracts(),
                                             findContract.getNameCompany(),
                                             findContract.getIncome(),
                                             findContract.getComsumption(),
                                             findContract.getDateBegin(),
                                             findContract.getDateEnd());
        Stage stage = (Stage) selectButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void findButtonAction(ActionEvent event) throws ClassNotFoundException {
        Contracts findContract = new Contracts();
        if (idField.getText().length() != 0)
            findContract.setIdContracts(Integer.parseInt(idField.getText()));
        findContract.setNameCompany(companyNameField.getText());
        if (incomeField.getText().length() != 0)
            findContract.setIncome(Integer.parseInt(incomeField.getText()));
        if (consField.getText().length() != 0)
            findContract.setComsumption(Integer.parseInt(consField.getText()));
        tableFind.setItems(FXCollections.observableArrayList(wws.findContractInBd(findContract)));
    }

    @FXML
    void initialize() throws IOException {
        incomeColumn.setCellValueFactory(new PropertyValueFactory<>("income"));
        consColumn.setCellValueFactory(new PropertyValueFactory<>("comsumption"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("dateBegin"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idContracts"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameCompany"));
        wws = new WorkWithServer();
        loader.load();
        mainController = loader.<MainController>getController();
    }

}
