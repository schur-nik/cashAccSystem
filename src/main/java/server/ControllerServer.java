package server;

public class ControllerServer {
    public static void main(String[] args) {
        new Thread(new ConnectionToDB()).start();
        new Thread(new MultiThreadServer()).start();
    }
}
