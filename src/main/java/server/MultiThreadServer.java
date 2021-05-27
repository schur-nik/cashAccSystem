package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer implements Runnable{
    static ExecutorService executeIt = Executors.newFixedThreadPool(5);
    static int clientNumb = 0;
    MultiThreadServer(){}
    public void run() {
        System.out.println("Server ON");
        try (ServerSocket server = new ServerSocket(1007);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!server.isClosed()) {
                Socket client = server.accept();
                executeIt.execute(new MonoThreadClientHandler(client, this));
                clientNumb++;
                System.out.println("Connection accepted. Client " + clientNumb);
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void minusClientNumb() {this.clientNumb--;}
}