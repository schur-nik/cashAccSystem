package server;

import server.AbsModels.Cons;
import server.AbsModels.Contracts;
import server.AbsModels.InfoContract;
import server.AbsModels.Loans;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;


public class MonoThreadClientHandler implements Runnable {
    MultiThreadServer obj;
    private static Socket clientAccepted;

    MonoThreadClientHandler(Socket client, MultiThreadServer obj) {
        this.obj=obj;
        MonoThreadClientHandler.clientAccepted = client;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream serverStreamIn = new ObjectInputStream(clientAccepted.getInputStream());
            ObjectOutputStream serverStreamOut = new ObjectOutputStream(clientAccepted.getOutputStream());
            while (!clientAccepted.isClosed()) {
                String clientMessage = (String)serverStreamIn.readObject();
                System.out.println(clientMessage);
                if (clientMessage.equalsIgnoreCase("testF1")) {
                    System.out.println(ConnectionToDB.testF());
                    serverStreamOut.writeObject(ConnectionToDB.testF());
                }
                if (clientMessage.equalsIgnoreCase("getListConractsFromBd")) {
                    serverStreamOut.writeObject(ConnectionToDB.getListContractsFromBd());
                }
                if (clientMessage.equalsIgnoreCase("addContractToBd")) {
                    ConnectionToDB.addContractToBd((Contracts) serverStreamIn.readObject());
                }
                if (clientMessage.equalsIgnoreCase("addConsToBd")) {
                    ConnectionToDB.addConsToBd((Cons) serverStreamIn.readObject());
                }
                if (clientMessage.equalsIgnoreCase("editConsToBd")) {
                    ConnectionToDB.editConsToBd((Cons) serverStreamIn.readObject());
                }
                if (clientMessage.equalsIgnoreCase("deleteContractFromBd")) {
                    ConnectionToDB.deleteContractFromBd((Contracts) serverStreamIn.readObject());
                }
                if (clientMessage.equalsIgnoreCase("deleteInfoContractFromBd")) {
                    int selectIdContract = (int)serverStreamIn.readObject();
                    ConnectionToDB.deleteInfoContractFromBd(selectIdContract, (InfoContract) serverStreamIn.readObject());
                }
                if (clientMessage.equalsIgnoreCase("changeBalans")) {
                    serverStreamOut.writeObject(ConnectionToDB.changeBalans((Integer) serverStreamIn.readObject()));
                }
                if (clientMessage.equalsIgnoreCase("editContractFromBd")) {
                    ConnectionToDB.editContractFromBd((Contracts) serverStreamIn.readObject());
                }
                if (clientMessage.equalsIgnoreCase("getListIncomeDayFromBd")) {
                    int readInt = (int)serverStreamIn.readObject();
                    serverStreamOut.writeObject(ConnectionToDB.getListIncomeDayFromBd(readInt));
                }
                if (clientMessage.equalsIgnoreCase("getListLoansFromBd")) {
                    serverStreamOut.writeObject(ConnectionToDB.getListLoansFromBd());
                }
                if (clientMessage.equalsIgnoreCase("addLoanToBd")) {
                    ConnectionToDB.addLoanToBd((Loans) serverStreamIn.readObject());
                }
                if (clientMessage.equalsIgnoreCase("editLoanFromBd")) {
                    ConnectionToDB.editLoanFromBd((Loans) serverStreamIn.readObject());
                }
                if (clientMessage.equalsIgnoreCase("getListConsFromBd")) {
                    serverStreamOut.writeObject(ConnectionToDB.getListConsFromBd());
                }
                if (clientMessage.equalsIgnoreCase("findContractInBd")) {
                    serverStreamOut.writeObject(ConnectionToDB.findContractInBd((Contracts) serverStreamIn.readObject()));
                }
                if (clientMessage.equalsIgnoreCase("getDebtResultFromBd")) {
                    Date begin = (Date) serverStreamIn.readObject();
                    Date end = (Date) serverStreamIn.readObject();
                    serverStreamOut.writeObject(ConnectionToDB.getDebtResultFromBd(begin, end));
                }
                if (clientMessage.equalsIgnoreCase("getStatFromBd")) {
                    Date begin = (Date) serverStreamIn.readObject();
                    Date end = (Date) serverStreamIn.readObject();
                    serverStreamOut.writeObject(ConnectionToDB.getStatFromBd(begin, end));
                }
                if (clientMessage.equalsIgnoreCase("getBalansFromBd")) {
                    Date begin = (Date) serverStreamIn.readObject();
                    Date end = (Date) serverStreamIn.readObject();
                    serverStreamOut.writeObject(ConnectionToDB.getBalansFromBd(begin, end));
                }
                if (clientMessage.equalsIgnoreCase("auth")) {
                    String login = (String) serverStreamIn.readObject();
                    String pass = (String) serverStreamIn.readObject();
                    serverStreamOut.writeObject(ConnectionToDB.auth(login, pass));
                }
                if (clientMessage.equalsIgnoreCase("register")) {
                    String login = (String) serverStreamIn.readObject();
                    String pass = (String) serverStreamIn.readObject();
                    ConnectionToDB.registerNewUser(login, pass);
                }
                if (clientMessage.equalsIgnoreCase("addInfoInContractToBd")) {
                    Integer selectId = (Integer) serverStreamIn.readObject();
                    InfoContract obj = (InfoContract) serverStreamIn.readObject();
                    ConnectionToDB.addInfoInContractToBd(selectId, obj);
                }
                if (clientMessage.equalsIgnoreCase("exit")) {
                    break;
                }
            }
            serverStreamIn.close();
            serverStreamOut.close();
            obj.minusClientNumb();
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");
            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}