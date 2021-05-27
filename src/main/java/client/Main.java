package client;

import client.Controllers.MainController;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {
    public static ObjectOutputStream coos;
    public static ObjectInputStream cois;
    private static Socket clientSocket;
    private FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainFrame.fxml"));
    private static MainController mainController = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = loader.load();
        mainController = loader.<MainController>getController();
        if (clientSocket != null)
            mainController.setConnection(1);
        primaryStage.setOnCloseRequest(e -> {
            try {
                Main.coos.writeObject("exit");
                clientSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        primaryStage.setTitle("Cash accounting system");
        primaryStage.setScene(new Scene(root,823, 514));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            connect();
        } catch (IOException e) { System.out.println("No connection");}
        launch(args);
    }

    public static void connect () throws IOException {
        try {
        clientSocket = new Socket("127.0.0.1", 1007);
        coos = new ObjectOutputStream(clientSocket.getOutputStream());
        cois = new ObjectInputStream(clientSocket.getInputStream());
        if (clientSocket.isConnected() && mainController != null) {mainController.setConnection(1);}
        } catch (IOException | ClassNotFoundException e) { System.out.println("No connection");}
    }
}
