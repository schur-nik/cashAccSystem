package client.Controllers;

import client.Main;
import server.AbsModels.*;
import client.WorkWithServer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.AbsModels.Contracts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.regex.Pattern;

public class MainController {
    WorkWithServer wws;

    @FXML
    private AnchorPane anch1;

    @FXML
    private Tab tabContracts;

    @FXML
    private TableView<Contracts> tableContracts;

    @FXML
    private TableColumn<Contracts, Integer> idColumn;

    @FXML
    private TableColumn<Contracts, String> nameColumn;

    @FXML
    private TableColumn<Contracts, Integer> incomeColumn;

    @FXML
    private TableColumn<Contracts, Integer> consColumn;

    @FXML
    private TableColumn<Contracts, Date> dateBeginColumn;

    @FXML
    private TableColumn<Contracts, Date> dateEndColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private AnchorPane deletePane;

    @FXML
    private Button deleteContractButton;

    @FXML
    private AnchorPane addPane;

    @FXML
    private Button addContractButton;

    @FXML
    private TextField addIdField;

    @FXML
    private TextField addCompanyNameField;

    @FXML
    private TextField addIncomeField;

    @FXML
    private TextField addConsField;

    @FXML
    private DatePicker addDatePickerBegin;

    @FXML
    private DatePicker addDatePickerEnd;

    @FXML
    private AnchorPane editPane;

    @FXML
    private Button editContractButton;

    @FXML
    private TextField editIdField;

    @FXML
    private TextField editCompanyNameField;

    @FXML
    private TextField editIncomeField;

    @FXML
    private TextField editConsField;

    @FXML
    private DatePicker editDatePickerBegin;

    @FXML
    private DatePicker editDatePickerEnd;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelNumberContract;

    @FXML
    private TableView<InfoContract> tableIncomeDay;

    @FXML
    private TableColumn<InfoContract, Integer> incomeDayColumn;

    @FXML
    private TableColumn<InfoContract, Integer> consDayColumn;

    @FXML
    private TableColumn<InfoContract, Date> dateDayColumn;

    @FXML
    private Tab tabLoans;

    @FXML
    private Button addLoanButton;

    @FXML
    private TableView<Loans> tableLoans;

    @FXML
    private TableColumn<Loans, Integer> idLoanColumn;

    @FXML
    private TableColumn<Loans, String> nameLoanColumn;

    @FXML
    private TableColumn<Loans, String> nameCompanyLoanColumn;

    @FXML
    private TableColumn<Loans, Integer> sumLoanColumn;

    @FXML
    private TableColumn<Loans, Integer> daySumLoanColumn;

    @FXML
    private TableColumn<Loans, Date> dateBeginLoanColumn;

    @FXML
    private TableColumn<Loans, Date> dateEndLoanColumn;

    @FXML
    private Button addInfoInContractButton;

    @FXML
    private Button deleteInfoInContractButton;

    @FXML
    private Button editInfoInContractButton;

    @FXML
    private AnchorPane addInfoPane;

    @FXML
    private Button addInfoButton;

    @FXML
    private TextField addInfoIncomeField;

    @FXML
    private TextField addInfoConsField;

    @FXML
    private DatePicker addInfoDatePicker;

    @FXML
    private AnchorPane editInfoPane;

    @FXML
    private Button editInfoButton;

    @FXML
    private TextField editInfoIncomeField;

    @FXML
    private TextField editInfoConsField;

    @FXML
    private DatePicker editInfoDatePicker;

    @FXML
    private Button deleteLoanButton;

    @FXML
    private Button editLoanButton;

    @FXML
    private TableView<Cons> tableCons;

    @FXML
    private TableColumn<Cons, String> consName;

    @FXML
    private TableColumn<Cons, Integer> consAmout;

    @FXML
    private TableView<?> tableConsDay;

    @FXML
    private TableColumn<?, ?> consPersDayColumn;

    @FXML
    private TableColumn<?, ?> datePersDayColumn;

    @FXML
    private DatePicker debtDatePickerBegin;

    @FXML
    private DatePicker debtDatePickerEnd;

    @FXML
    private Label debtResultLabel;

    @FXML
    private AnchorPane deleteLoanAndConsPane;

    @FXML
    private Label deleteCountLabel;

    @FXML
    private Button deleteSelectLoanButton;

    @FXML
    private AnchorPane formatLoanPane;

    @FXML
    private Button formatLoanButton;

    @FXML
    private TextField loanFormatIdField;

    @FXML
    private TextField loanFormatCompanyNameField;

    @FXML
    private TextField loanFormatAllSumField;

    @FXML
    private TextField loanFormatSumInDayField;

    @FXML
    private DatePicker loanFormatDatePickerBegin;

    @FXML
    private DatePicker loanFormatDatePickerEnd;

    @FXML
    private TextField loanFormatNameField;

    @FXML
    private Button addConsuptionButton;

    @FXML
    private Button deleteConsuptionButton;

    @FXML
    private Button tryConnect;

    @FXML
    private Button editConsuptionButton;

    @FXML
    private AnchorPane formatConsPane;

    @FXML
    private Button formatConsuptionButton;

    @FXML
    private Label formatNameConsLabel;

    @FXML
    private TextField formatNameConsField;

    @FXML
    private TextField formatConsConsField;

    @FXML
    private Label formatConsConsLabel;

    @FXML
    private DatePicker formatConsDatePicker;

    @FXML
    private Tab tabStat;

    @FXML
    private DatePicker statDatePickerBegin;

    @FXML
    private DatePicker statDatePickerEnd;

    @FXML
    private TextField incomeStat;

    @FXML
    private TextField consStat;

    @FXML
    private TextField profitStat;

    @FXML
    private TextField loansStat;

    @FXML
    private TextField consConsStat;

    @FXML
    private TextField balansStat;

    @FXML
    private TextField changeBalansField;

    @FXML
    private Pane grapthBalansPane;

    @FXML
    private Pane grapthContractsPane;

    @FXML
    private Button authButton;

    @FXML
    private Button aboutButton;

    @FXML
    private Label userLabel;

    @FXML
    private Label deleteNumbLabel;

    @FXML
    private Button changeBalansButton;

    @FXML
    private Button fi0ndButton;

    @FXML
    private Button SaveFileButton;

    @FXML
    private Button findButton;

    @FXML
    private Label changeBalansLabel;

    private static int idUser = 0;
    private static String login = null;
    private static Contracts findContract = new Contracts();
    private static int connection;

    public void SaveFileButtonAction(ActionEvent event) throws IOException {
        String outputFileName = "C:/1/orderReport.txt";
        String str = new String();
        for (Contracts getContract :tableContracts.getItems()) {
            str += getContract.toString()+"\n";
        }
        File file = new File(outputFileName);
        if (!file.exists())
            file.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(str);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setDataInFindContract(int id, String name, int inc, int cons, Date dB, Date dE) {
        findContract.setIdContracts(id);
        findContract.setNameCompany(name);
        findContract.setIncome(inc);
        findContract.setComsumption(cons);
        findContract.setDateBegin(dB);
        findContract.setDateEnd(dE);
    }

    public void setIdUserAndLogin(int idUser, String login) {
        this.idUser = idUser;
        this.login = login;
    }

    void updateWindow() {
        if (idUser > 0) {
            authButton.setVisible(false);
            userLabel.setVisible(true);
            userLabel.setText("User: " + login);
            addButton.setDisable(false);
            deleteButton.setDisable(false);
            editButton.setDisable(false);
            addLoanButton.setDisable(false);
            editLoanButton.setDisable(false);
            deleteLoanButton.setDisable(false);
            addConsuptionButton.setDisable(false);
            deleteConsuptionButton.setDisable(false);
            editConsuptionButton.setDisable(false);
            addInfoInContractButton.setDisable(false);
            editInfoInContractButton.setDisable(false);
            deleteInfoInContractButton.setDisable(false);
        }
    }

    void updateWindowAfterFind() {
        tableContracts.getSelectionModel().select(findContract.getIdContracts()-1);
    }

    @FXML
    void changeBalansButtonAction(ActionEvent event) throws ClassNotFoundException {
        balansStat.setText(String.valueOf(wws.changeBalans(Integer.parseInt(changeBalansField.getText()))));
        changeGrapthBalans();
        changeBalansField.setText("0");
        changeBalansLabel.setVisible(true);
        hideLabel(changeBalansLabel);
    }


    @FXML
    void authButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/authFrame.fxml"));
        Stage stage = new Stage();
        stage.setOnCloseRequest(e -> updateWindow());
        stage.setOnHidden(e -> updateWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Authentication");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private int MODE;

    @FXML
    void deleteLoanAction(ActionEvent event) {

    }



    @FXML
    void formatConsuptionButtonAction(ActionEvent event) throws ClassNotFoundException {
        Cons cons = new Cons(tableCons.getSelectionModel().getSelectedItem().getIdCons(), formatNameConsField.getText(), Integer.parseInt(formatConsConsField.getText()));
        if (getMode() == 3) {
            wws.addConsToBd(cons);
            formatNameConsField.setText("");
            formatConsConsField.setText("");
        }
        else if (getMode() == 4) {
            wws.editConsToBd(cons);
        };
        tableCons.setItems(FXCollections.observableArrayList(wws.getListConsFromBd()));
    }

    @FXML
    void formatLoanButtonAction(ActionEvent event) throws ClassNotFoundException {
        Loans loan = new Loans(Integer.parseInt(loanFormatIdField.getText()),
                loanFormatNameField.getText(),
                loanFormatCompanyNameField.getText(),
                Integer.parseInt(loanFormatAllSumField.getText()),
                Integer.parseInt(loanFormatSumInDayField.getText()),
                Date.valueOf(loanFormatDatePickerBegin.getValue()),
                Date.valueOf(loanFormatDatePickerEnd.getValue())
        );
        if (getMode() == 1) {
            wws.addLoanToBd(loan);
            loanFormatIdField.setText("");
            loanFormatNameField.setText("");
            loanFormatCompanyNameField.setText("");
            loanFormatAllSumField.setText("");
            loanFormatSumInDayField.setText("");
            loanFormatDatePickerBegin.setValue(new Date(System.currentTimeMillis()).toLocalDate());
            loanFormatDatePickerEnd.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        }
        else if (getMode() == 2) {wws.editLoanFromBd(loan);};
        tableLoans.setItems(FXCollections.observableArrayList(wws.getListLoansFromBd()));
    }

    @FXML
    void addConsuptionButtonAction(ActionEvent event) {
        Button curButton = (Button)event.getSource();
        if (formatConsPane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            formatConsPane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            formatLoanPane.setVisible(false);
            deleteLoanAndConsPane.setVisible(false);
            formatConsPane.setVisible(true);
            setChoice(curButton, 1);
        }
        formatConsuptionButton.setText("Add Consuption");
        setMode(3);
        formatNameConsField.setText("");
        formatConsConsField.setText("");
    }

    @FXML
    void deleteConsuptionButtonAction(ActionEvent event) {
        Button curButton = (Button)event.getSource();
        if (deleteLoanAndConsPane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            deleteLoanAndConsPane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            formatLoanPane.setVisible(false);
            deleteLoanAndConsPane.setVisible(true);
            formatConsPane.setVisible(false);
            setChoice(curButton, 1);
        }
    }

    @FXML
    void editConsuptionButtonAction(ActionEvent event) {
        Button curButton = (Button)event.getSource();
        if (formatConsPane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            formatConsPane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            formatLoanPane.setVisible(false);
            deleteLoanAndConsPane.setVisible(false);
            formatConsPane.setVisible(true);
            setChoice(curButton, 1);
        }
        formatConsuptionButton.setText("Edit Consuption");
        setMode(4);
        if (tableCons.getSelectionModel().getSelectedItem() != null) {
            formatNameConsField.setText(String.valueOf(tableCons.getSelectionModel().getSelectedItem().getName()));
            formatConsConsField.setText(String.valueOf(tableCons.getSelectionModel().getSelectedItem().getCons()));
        }
    }

    @FXML
    void addLoanButtonAction(ActionEvent event) throws ClassNotFoundException {
        loanFormatIdField.setText("");
        loanFormatNameField.setText("");
        loanFormatCompanyNameField.setText("");
        loanFormatAllSumField.setText("");
        loanFormatSumInDayField.setText("");
        loanFormatDatePickerBegin.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        loanFormatDatePickerEnd.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        Button curButton = (Button)event.getSource();
        if (formatLoanPane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            formatLoanPane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            formatLoanPane.setVisible(true);
            deleteLoanAndConsPane.setVisible(false);
            formatConsPane.setVisible(false);
            setChoice(curButton, 1);
        }
        formatLoanButton.setText("Add Loan");
        setMode(1);
    }

    @FXML
    void deleteLoanButtonAction(ActionEvent event) {
        Button curButton = (Button)event.getSource();
        if (deleteLoanAndConsPane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            deleteLoanAndConsPane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            formatLoanPane.setVisible(false);
            deleteLoanAndConsPane.setVisible(true);
            formatConsPane.setVisible(false);
            setChoice(curButton, 1);
        }
    }

    @FXML
    void editLoanButtonAction(ActionEvent event) {
        Button curButton = (Button)event.getSource();
        if (formatLoanPane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            formatLoanPane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            formatLoanPane.setVisible(true);
            deleteLoanAndConsPane.setVisible(false);
            formatConsPane.setVisible(false);
            setChoice(curButton, 1);
        }
        setMode(2);
        if (tableLoans.getSelectionModel().getSelectedItem() != null) {
            loanFormatIdField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getIdContracts()));
            loanFormatNameField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getName()));
            loanFormatCompanyNameField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getNameCompany()));
            loanFormatAllSumField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getAllSum()));
            loanFormatSumInDayField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getAllSum()));
            loanFormatDatePickerBegin.setValue(tableLoans.getSelectionModel().getSelectedItem().getDateBegin().toLocalDate());
            loanFormatDatePickerEnd.setValue(tableLoans.getSelectionModel().getSelectedItem().getDateEnd().toLocalDate());
        }
    }

    private void setMode(int mode) {
        if (mode == 2)
            formatLoanButton.setText("Edit Loan");
        else if (mode == 1)
            formatLoanButton.setText("Add Loan");
        MODE = mode;
    }

    private int getMode() {
        return MODE;
    }

    private void setChoice(Button choiceButton, int mode){
        editLoanButton.setText("Edit Loan");
        addLoanButton.setText("Add Loan");
        deleteLoanButton.setText("Delete Loan");
        editConsuptionButton.setText("Edit Consuption");
        addConsuptionButton.setText("Add Consuption");
        deleteConsuptionButton.setText("Delete Consuption");

        addInfoInContractButton.setText("Add Info");
        editInfoInContractButton.setText("Edit Info");
        deleteInfoInContractButton.setText("Delete Info");
        addButton.setText("Add");
        deleteButton.setText("Delete");
        editButton.setText("Edit");
        if (mode == 1)
            choiceButton.setText(choiceButton.getText() + " *");
    }

    @FXML
    void clickTableConsAction(MouseEvent event) {
        if (formatConsPane.isVisible() && getMode()==4) {
            formatNameConsField.setText(String.valueOf(tableCons.getSelectionModel().getSelectedItem().getName()));
            formatConsConsField.setText(String.valueOf(tableCons.getSelectionModel().getSelectedItem().getCons()));
        }
    }

    private Boolean flagLabelIsActive = false;

    private void prc(Label lbl) {
        if (flagLabelIsActive) {
            lbl.setText("");
            lbl.setVisible(false);
        }
        else flagLabelIsActive = true;
    }

    private void hideLabel(Label lbl) {
        Thread thread = new Thread(() -> {
            Runnable updater = () -> {
                prc(lbl);
            };
            while (true) {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException ex) {
                }
                Platform.runLater(updater);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    void addInfoButtonAction (ActionEvent event) throws ClassNotFoundException {
        Contracts selectedItem = tableContracts.getSelectionModel().getSelectedItem();
        int selectedContractIdInt = selectedItem.getIdContracts();
        if ((addInfoConsField.getText().length() > 0 && (Pattern.matches("^[0-9]+", addInfoConsField.getText()) )) &&
                (addInfoIncomeField.getText().length() > 0 && (Pattern.matches("^[0-9]+", addInfoIncomeField.getText())))) {
            wws.addInfoInContractToBd(new InfoContract(Integer.parseInt(addInfoIncomeField.getText()),
                            Integer.parseInt(addInfoConsField.getText()),
                            Date.valueOf(addInfoDatePicker.getValue())),
                    selectedContractIdInt
            );
            addInfoConsField.setText("");
            addInfoConsField.setText("");
            labelStatus.setVisible(true);
            labelStatus.setText("Info in contract is added.");
            hideLabel(labelStatus);
            addInfoDatePicker.setValue(new Date(System.currentTimeMillis()).toLocalDate());
            tableIncomeDay.setItems(FXCollections.observableArrayList(wws.getListIncomeDayFromBd(selectedContractIdInt)));
            tableContracts.setItems(FXCollections.observableArrayList(wws.getListConractsFromBd()));
            tableContracts.getSelectionModel().select(selectedItem);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка добавления информации");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Неправильно заполнены поля");
            alert.showAndWait();
        }
    }

    @FXML
    void editInfoButtonAction (ActionEvent event) {}

    void editInfoPaneSetInfo() {
        InfoContract obj = tableIncomeDay.getSelectionModel().getSelectedItem();
        if (obj != null) {
            editInfoIncomeField.setText(String.valueOf(obj.getIncome()));
            editInfoConsField.setText(String.valueOf(obj.getCons()));
            editInfoDatePicker.setValue(obj.getDateDay().toLocalDate());
        }
    }

    void getTableIncomeDay() throws ClassNotFoundException { //вспомогательная функция для получения таблицы инфы на вкладке обороты
        int numbContract = tableContracts.getSelectionModel().getSelectedItem().getIdContracts();
        labelNumberContract.setText("Contract #"+numbContract);
        tableIncomeDay.setItems(FXCollections.observableArrayList(wws.getListIncomeDayFromBd(numbContract)));
        if (editPane.isVisible()) {
            editIdField.setText(String.valueOf(tableContracts.getSelectionModel().getSelectedItem().getIdContracts()));
            editCompanyNameField.setText(String.valueOf(tableContracts.getSelectionModel().getSelectedItem().getNameCompany()));
            editIncomeField.setText(String.valueOf(tableContracts.getSelectionModel().getSelectedItem().getIncome()));
            editConsField.setText(String.valueOf(tableContracts.getSelectionModel().getSelectedItem().getComsumption()));
            editDatePickerBegin.setValue(tableContracts.getSelectionModel().getSelectedItem().getDateBegin().toLocalDate());
            editDatePickerEnd.setValue(tableContracts.getSelectionModel().getSelectedItem().getDateEnd().toLocalDate());
        }
    }

    @FXML
    void clickTableLoanAction(MouseEvent event) throws ClassNotFoundException { //экшн на клик по таблице займов
        if (formatLoanPane.isVisible() && getMode()==2) {
            loanFormatIdField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getIdContracts()));
            loanFormatNameField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getName()));
            loanFormatCompanyNameField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getNameCompany()));
            loanFormatAllSumField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getAllSum()));
            loanFormatSumInDayField.setText(String.valueOf(tableLoans.getSelectionModel().getSelectedItem().getAllSum()));
            loanFormatDatePickerBegin.setValue(tableLoans.getSelectionModel().getSelectedItem().getDateBegin().toLocalDate());
            loanFormatDatePickerEnd.setValue(tableLoans.getSelectionModel().getSelectedItem().getDateEnd().toLocalDate());
        }
    }

    @FXML
    void changeDebtLabel(ActionEvent event) throws ClassNotFoundException { //меняет лейбл на вкладке обязательств в зависимости от выставленных дат
        debtResultLabel.setText(wws.getDebtResultFromBd(Date.valueOf(debtDatePickerBegin.getValue()), Date.valueOf(debtDatePickerEnd.getValue())));
    }

    void changeStat() throws ClassNotFoundException { //меняет данные на вкладке статистики
        Stat stat = wws.getStatFromBd(Date.valueOf(statDatePickerBegin.getValue()), Date.valueOf(statDatePickerEnd.getValue()));
        incomeStat.setText(String.valueOf(stat.getIncome()));
        consStat.setText(String.valueOf(stat.getCons()));
        profitStat.setText(String.valueOf(stat.getIncome()-stat.getCons()));
        loansStat.setText(String.valueOf(stat.getLoans()));
        consConsStat.setText(String.valueOf(stat.getConsCons()));
        balansStat.setText(String.valueOf(stat.getBalans()));
    }

    void changeGrapth() throws ClassNotFoundException { //рисует график ликвидности
        Date begin = Date.valueOf(statDatePickerBegin.getValue());
        Date end = Date.valueOf(statDatePickerEnd.getValue());
        Date shiftDate = begin;
        Stat stat = new Stat();
        int countDays= (int)((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        long shift = countDays/10;
        int lengthGraph = countDays;
        if (countDays>30) {lengthGraph = 11;}

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        XYChart.Series<String, Double> values = new XYChart.Series<String, Double>();

        for (int i = 1; i <= lengthGraph; i++) {
            stat = wws.getStatFromBd(shiftDate, Date.valueOf(shiftDate.toLocalDate().plusDays(shift)));
            values.getData().add(new XYChart.Data(shiftDate.toString(), stat.getLiquidity()));
            shiftDate = Date.valueOf(shiftDate.toLocalDate().plusDays(shift));
        }
        XYChart.Series<String, Double> series = values;
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setLegendVisible(false);
        lineChart.setMaxHeight(205);
        lineChart.setMaxWidth(460);
        lineChart.getData().add(series);
        grapthContractsPane.getChildren().clear();
        grapthContractsPane.getChildren().add(lineChart);
    }

    void changeGrapthBalans() throws ClassNotFoundException {  //рисует график баланса
        Date begin = Date.valueOf(statDatePickerBegin.getValue());
        Date end = Date.valueOf(statDatePickerEnd.getValue());
        Date shiftDate = begin;
        int countDays= (int)((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        long shift = countDays/10;
        int lengthGraph = countDays;
        if (countDays>30) {lengthGraph = 11;}
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        XYChart.Series<String, Double> values = new XYChart.Series<String, Double>();

        for (int i = 1; i <= lengthGraph; i++) {
            values.getData().add(new XYChart.Data(shiftDate.toString(), wws.getBalansFromBd(shiftDate, Date.valueOf(shiftDate.toLocalDate().plusDays(shift)))));
            shiftDate = Date.valueOf(shiftDate.toLocalDate().plusDays(shift));
        }
        XYChart.Series<String, Double> series = values;
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setLegendVisible(false);
        lineChart.setMaxHeight(100);
        lineChart.setMaxWidth(460);
        lineChart.getData().add(series);
        grapthBalansPane.getChildren().clear();
        grapthBalansPane.getChildren().add(lineChart);
    }

    @FXML
    void changeStatAction(ActionEvent event) throws ClassNotFoundException { //вызывается когда меняются даты на вкладке статистики
        changeStat();
        changeGrapth();
        changeGrapthBalans();
    }

//Begin Actions на кнопки вызывающие Pane для работы с контрактами + сами действия добавления/удаления/редакта
    @FXML
    void addButtonAction(ActionEvent event) {
        addDatePickerBegin.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        addDatePickerEnd.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        Button curButton = (Button)event.getSource();
        if (addPane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            addPane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            addPane.setVisible(true);
            deletePane.setVisible(false);
            editPane.setVisible(false);
            editInfoPane.setVisible(false);
            addInfoPane.setVisible(false);
            setChoice(curButton, 1);
        }
    }

    @FXML
    void deleteButtonAction(ActionEvent event) {
        Button curButton = (Button)event.getSource();
        if (deletePane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            deletePane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            addPane.setVisible(false);
            deletePane.setVisible(true);
            editPane.setVisible(false);
            editInfoPane.setVisible(false);
            addInfoPane.setVisible(false);
            setChoice(curButton, 1);
        }
    }

    @FXML
    void editButtonAction(ActionEvent event) {
        if ((editIdField.getText().length() > 0 && (Pattern.matches("^[0-9]+", editIdField.getText()))) &&
                (editCompanyNameField.getText().length() > 0 && (Pattern.matches("^[a-zA-Z0-9]+", editCompanyNameField.getText()) || Pattern.matches("^[а-яА-Я0-9]+", editCompanyNameField.getText()))) &&
                (editIncomeField.getText().length() > 0 && (Pattern.matches("^[0-9]+", editIncomeField.getText()) )) &&
                (editConsField.getText().length() > 0 && (Pattern.matches("^[0-9]+", editConsField.getText())))) {
            if (tableContracts.getSelectionModel().getSelectedItem() != null) {
                editIdField.setText(String.valueOf(tableContracts.getSelectionModel().getSelectedItem().getIdContracts()));
                editCompanyNameField.setText(String.valueOf(tableContracts.getSelectionModel().getSelectedItem().getNameCompany()));
                editIncomeField.setText(String.valueOf(tableContracts.getSelectionModel().getSelectedItem().getIncome()));
                editConsField.setText(String.valueOf(tableContracts.getSelectionModel().getSelectedItem().getComsumption()));
                editDatePickerBegin.setValue(tableContracts.getSelectionModel().getSelectedItem().getDateBegin().toLocalDate());
                editDatePickerEnd.setValue(tableContracts.getSelectionModel().getSelectedItem().getDateEnd().toLocalDate());
            }
            Button curButton = (Button) event.getSource();
            if (editPane.isVisible() && curButton.getText().charAt(curButton.getText().length() - 1) == '*') {
                editPane.setVisible(false);
                setChoice(new Button(), 2);
            } else {
                addPane.setVisible(false);
                deletePane.setVisible(false);
                editPane.setVisible(true);
                editInfoPane.setVisible(false);
                addInfoPane.setVisible(false);
                setChoice(curButton, 1);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка редактирования");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Неправильно заполнены поля");
            alert.showAndWait();
        }
    }

    @FXML
    void addContractAction(ActionEvent event) throws ClassNotFoundException {
        if ((addIdField.getText().length() > 0 && (Pattern.matches("^[0-9]+", addIdField.getText()))) &&
                (addCompanyNameField.getText().length() > 0 && (Pattern.matches("^[a-zA-Z0-9]+", addCompanyNameField.getText()) || Pattern.matches("^[а-яА-Я0-9]+", addCompanyNameField.getText()))) &&
                (addConsField.getText().length() > 0 && (Pattern.matches("^[0-9]+", addConsField.getText()) )) &&
                (addIncomeField.getText().length() > 0 && (Pattern.matches("^[0-9]+", addIncomeField.getText())))) {
            wws.addContractToBd(new Contracts(Integer.parseInt(addIdField.getText()),
                    addCompanyNameField.getText(),
                    Integer.parseInt(addIncomeField.getText()),
                    Integer.parseInt(addConsField.getText()),
                    Date.valueOf(addDatePickerBegin.getValue()),
                    Date.valueOf(addDatePickerEnd.getValue())));
            tableContracts.setItems(FXCollections.observableArrayList(wws.getListConractsFromBd()));
            labelStatus.setVisible(true);
            labelStatus.setText("Contract is added.");
            hideLabel(labelStatus);
            addIdField.setText("");
            addCompanyNameField.setText("");
            addIncomeField.setText("0");
            addConsField.setText("0");
            addDatePickerBegin.setValue(new Date(System.currentTimeMillis()).toLocalDate());
            addDatePickerEnd.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка добавления");
            alert.setHeaderText("Ошибка ввода");
            alert.setContentText("Неправильно заполнены поля");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteContractAction(ActionEvent event) throws ClassNotFoundException {
        if (tableContracts.getSelectionModel().getSelectedItem() !=null) {
            wws.deleteContractFromBd(tableContracts.getSelectionModel().getSelectedItem());
            tableContracts.setItems(FXCollections.observableArrayList(wws.getListConractsFromBd()));
            labelStatus.setText("Contract is deleted.");
            labelStatus.setVisible(true);
            hideLabel(labelStatus);
            deleteNumbLabel.setText("XXX");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка удаления");
            alert.setHeaderText("Ошибка при удалении");
            alert.setContentText("Не выбран контракт для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void editContractAction(ActionEvent event) throws ClassNotFoundException {
        wws.editContractFromBd(new Contracts(Integer.parseInt(editIdField.getText()),
                editCompanyNameField.getText(),
                Integer.parseInt(editIncomeField.getText()),
                Integer.parseInt(editConsField.getText()),
                Date.valueOf(editDatePickerBegin.getValue()),
                Date.valueOf(editDatePickerEnd.getValue())
        ));
        tableContracts.setItems(FXCollections.observableArrayList(wws.getListConractsFromBd()));
        labelStatus.setText("Contract is edit.");
        labelStatus.setVisible(true);
        hideLabel(labelStatus);
    }
//End Actions на кнопки вызывающие Pane для работы с контрактами + сами действия добавления/удаления/редакта


//Begin Actions таблиц вкладки Обороты
    @FXML
    void clickTableAction(MouseEvent event) throws ClassNotFoundException {  //Экшн клика по таблице контрактов
        if (editInfoPane.isVisible()) {
            editInfoPane.setVisible(false);
            editInfoButton.setText("Edit Info");
        }
        else if (deletePane.isVisible()) {
            deleteNumbLabel.setText(String.valueOf(tableContracts.getSelectionModel().getSelectedItem().getIdContracts()));
        }
        getTableIncomeDay();
    }

    @FXML
    void clickTableDayAction(MouseEvent event) throws ClassNotFoundException { //Экшн клика по таблице инфы контрактов
        if (editInfoPane.isVisible()) {
            editInfoPaneSetInfo();
        }
    }
//End Actions таблиц вкладки Обороты


//Begin Actions на кнопки вызывающие Pane для работы с инфой контракта
    @FXML
    void addInfoInContractButtonAction(ActionEvent event) throws ClassNotFoundException {
        addInfoDatePicker.setValue(new Date(System.currentTimeMillis()).toLocalDate());

        Button curButton = (Button)event.getSource();
        if (addInfoPane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            addInfoPane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            addPane.setVisible(false);
            deletePane.setVisible(false);
            editPane.setVisible(false);
            editInfoPane.setVisible(false);
            addInfoPane.setVisible(true);
            setChoice(curButton, 1);
        }
    }

    @FXML
    void deleteInfoInContractButtonAction(ActionEvent event) throws ClassNotFoundException {
        if (tableIncomeDay.getSelectionModel().getSelectedItem() != null) {
            int selectIdContract = tableContracts.getSelectionModel().getSelectedItem().getIdContracts();
            InfoContract selectInfo = tableIncomeDay.getSelectionModel().getSelectedItem();
            wws.deleteInfoContractFromBd(selectIdContract, selectInfo);
            getTableIncomeDay();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка удаления");
            alert.setHeaderText("Ошибка при удалении");
            alert.setContentText("Не выбран информация контракта для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void editInfoInContractButtonAction(ActionEvent event) {
        editInfoPaneSetInfo();
        Button curButton = (Button)event.getSource();
        if (editInfoPane.isVisible() && curButton.getText().charAt(curButton.getText().length()-1) == '*') {
            editInfoPane.setVisible(false);
            setChoice(new Button(), 2);
        }
        else {
            addPane.setVisible(false);
            deletePane.setVisible(false);
            editPane.setVisible(false);
            editInfoPane.setVisible(true);
            addInfoPane.setVisible(false);
            setChoice(curButton, 1);
        }
    }
//End Actions на кнопки вызывающие Pane для работы с инфой контракта

    @FXML
    void findButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/findFrame.fxml"));
        Stage stage = new Stage();
        stage.setOnCloseRequest(e -> updateWindowAfterFind());
        stage.setOnHidden(e -> updateWindowAfterFind());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Find Contract");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void tryConnectAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (connection == 0) {
            Main.connect();
            tryConnect.setStyle("-fx-border-color: RED ; -fx-border-width: 3 ; -fx-background-color:  #2E3375");
        }
        if (connection == 1) {
            tryConnect.setText("Connected");
            tryConnect.setStyle("-fx-border-color: GREEN ; -fx-border-width: 3 ; -fx-background-color:  #2E3375");
            reloadAllTable();
        }
    }

    void reloadAllTable() throws ClassNotFoundException {
        authButton.setDisable(false);
        fi0ndButton.setDisable(false);
        SaveFileButton.setDisable(false);
        debtDatePickerBegin.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        debtDatePickerEnd.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        loanFormatDatePickerBegin.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        loanFormatDatePickerEnd.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        statDatePickerBegin.setValue(new Date(System.currentTimeMillis()).toLocalDate().minusDays(10));
        statDatePickerEnd.setValue(new Date(System.currentTimeMillis()).toLocalDate());
        tableContracts.setItems(FXCollections.observableArrayList(wws.getListConractsFromBd()));
        tableContracts.getSelectionModel().select(0);
        getTableIncomeDay();
        tabLoans.setOnSelectionChanged(event -> {
            if (tabLoans.isSelected()) {
                try {
                    debtResultLabel.setText(wws.getDebtResultFromBd(Date.valueOf(debtDatePickerBegin.getValue()), Date.valueOf(debtDatePickerEnd.getValue())));
                    tableLoans.setItems(FXCollections.observableArrayList(wws.getListLoansFromBd()));
                    tableCons.setItems(FXCollections.observableArrayList(wws.getListConsFromBd()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        tabContracts.setOnSelectionChanged(event -> {
            if (tabContracts.isSelected()) {
                try {
                    tableContracts.setItems(FXCollections.observableArrayList(wws.getListConractsFromBd()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        tabStat.setOnSelectionChanged(event -> {
            if (tabStat.isSelected()) {
                try {
                    changeStat();
                    changeGrapth();
                    changeGrapthBalans();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void initialize() throws ClassNotFoundException {
        wws = new WorkWithServer();
        tryConnect.setStyle("-fx-border-color: RED ; -fx-border-width: 3 ; -fx-background-color:  #2E3375");
        incomeDayColumn.setCellValueFactory(new PropertyValueFactory<>("income"));
        consDayColumn.setCellValueFactory(new PropertyValueFactory<>("cons"));
        dateDayColumn.setCellValueFactory(new PropertyValueFactory<>("dateDay"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idContracts"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameCompany"));
        incomeColumn.setCellValueFactory(new PropertyValueFactory<>("income"));
        consColumn.setCellValueFactory(new PropertyValueFactory<>("comsumption"));
        dateBeginColumn.setCellValueFactory(new PropertyValueFactory<>("dateBegin"));
        dateEndColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        idLoanColumn.setCellValueFactory(new PropertyValueFactory<>("idContracts"));
        nameLoanColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCompanyLoanColumn.setCellValueFactory(new PropertyValueFactory<>("nameCompany"));
        sumLoanColumn.setCellValueFactory(new PropertyValueFactory<>("allSum"));
        daySumLoanColumn.setCellValueFactory(new PropertyValueFactory<>("sumDay"));
        dateBeginLoanColumn.setCellValueFactory(new PropertyValueFactory<>("dateBegin"));
        dateEndLoanColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        consAmout.setCellValueFactory(new PropertyValueFactory<>("cons"));
        consName.setCellValueFactory(new PropertyValueFactory<>("name"));

        aboutButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("О программе");
            alert.setHeaderText("Cash accounting system");
            alert.setContentText("Разработано в 2020 году. Версия программы 0.0.3");
            alert.showAndWait();
        });

        if (connection == 1) {
            reloadAllTable();
        }
    }

    public void setConnection(int connection) throws ClassNotFoundException {
        System.out.println("Connect is ok");
        this.connection = connection;
        if (connection == 1) {
            tryConnect.setText("Connected");
            tryConnect.setStyle("-fx-border-color: GREEN ; -fx-border-width: 3 ; -fx-background-color:  #2E3375");
            reloadAllTable();}
    }
}
