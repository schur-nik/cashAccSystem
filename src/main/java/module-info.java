module client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens client.Controllers to javafx.fxml;
    opens client to javafx.fxml;

    exports client.Controllers;
    exports client;

    opens server.AbsModels to java.base;
    opens server to java.base;

    exports server.AbsModels;
    exports server;
}