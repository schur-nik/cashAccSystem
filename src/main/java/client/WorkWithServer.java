package client;

import server.AbsModels.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class WorkWithServer {

    public String testF() throws ClassNotFoundException
    {
        System.out.println("wws func");
        String text = null;
        try {
            Main.coos.writeObject("testF1");
            text = (String)Main.cois.readObject();
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public ArrayList<Contracts> getListConractsFromBd() throws ClassNotFoundException
    {
        System.out.println("get list contracts from bd");
        ArrayList<Contracts> list = new ArrayList<Contracts>();
        try {
            Main.coos.writeObject("getListConractsFromBd");
            list = (ArrayList<Contracts>)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<InfoContract> getListIncomeDayFromBd(int idContracts) throws ClassNotFoundException {
        System.out.println("get list incomeDay from bd");
        ArrayList<InfoContract> list = new ArrayList<InfoContract>();
        try {
            Main.coos.writeObject("getListIncomeDayFromBd");
            Main.coos.writeObject(idContracts);
            list = (ArrayList<InfoContract>)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Loans>  getListLoansFromBd() throws ClassNotFoundException {
        System.out.println("get list loans from bd");
        ArrayList<Loans> list = new ArrayList<Loans>();
        try {
            Main.coos.writeObject("getListLoansFromBd");
            list = (ArrayList<Loans>)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Cons> getListConsFromBd() throws ClassNotFoundException {
        System.out.println("get list cons from bd");
        ArrayList<Cons> list = new ArrayList<Cons>();
        try {
            Main.coos.writeObject("getListConsFromBd");
            list = (ArrayList<Cons>)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Stat getStatFromBd(Date begin, Date end) throws ClassNotFoundException {
        System.out.println("get stat from bd");
        Stat stat = null;
        try {
            Main.coos.writeObject("getStatFromBd");
            Main.coos.writeObject(begin);
            Main.coos.writeObject(end);
            stat = (Stat)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stat;
    }

    public int getBalansFromBd(Date begin, Date end) throws ClassNotFoundException {
        System.out.println("get balans from bd");
        int result = 0;
        try {
            Main.coos.writeObject("getBalansFromBd");
            Main.coos.writeObject(begin);
            Main.coos.writeObject(end);
            result = (Integer)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addContractToBd(Contracts contractToAdd) {
        System.out.println("add contract to bd");
        System.out.println(contractToAdd.toString());
        try {
            Main.coos.writeObject("addContractToBd");
            Main.coos.writeObject(contractToAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addInfoInContractToBd(InfoContract infoContract, int selectedContractId) {
        System.out.println("add info contract to bd");
        try {
            Main.coos.writeObject("addInfoInContractToBd");
            Main.coos.writeObject(selectedContractId);
            Main.coos.writeObject(infoContract);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteContractFromBd(Contracts contractToDelete) {
        System.out.println("delete contract from bd");
        System.out.println(contractToDelete.toString());
        try {
            Main.coos.writeObject("deleteContractFromBd");
            Main.coos.writeObject(contractToDelete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteInfoContractFromBd(int selectIdContract, InfoContract selectInfo) {
        System.out.println("delete info contract from bd");
        try {
            Main.coos.writeObject("deleteInfoContractFromBd");
            Main.coos.writeObject(selectIdContract);
            Main.coos.writeObject(selectInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editContractFromBd(Contracts contractToEdit) {
        System.out.println("edit contract from bd");
        System.out.println(contractToEdit.toString());
        try {
            Main.coos.writeObject("editContractFromBd");
            Main.coos.writeObject(contractToEdit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLoanToBd(Loans loanToAdd) {
        System.out.println("add contract to bd");
        System.out.println(loanToAdd.toString());
        try {
            Main.coos.writeObject("addLoanToBd");
            Main.coos.writeObject(loanToAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addConsToBd(Cons cons) {
        System.out.println("add cons to bd");
        try {
            Main.coos.writeObject("addConsToBd");
            Main.coos.writeObject(cons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editConsToBd(Cons cons) {
        System.out.println("edit cons to bd");
        try {
            Main.coos.writeObject("editConsToBd");
            Main.coos.writeObject(cons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editLoanFromBd(Loans loanToEdit) {
        System.out.println("edit contract from bd");
        System.out.println(loanToEdit.toString());
        try {
            Main.coos.writeObject("editLoanFromBd");
            Main.coos.writeObject(loanToEdit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDebtResultFromBd(Date begin, Date end) throws ClassNotFoundException {
        System.out.println("get debt result from bd");
        int result = 0;
        try {
            Main.coos.writeObject("getDebtResultFromBd");
            Main.coos.writeObject(begin);
            Main.coos.writeObject(end);
            result = (Integer)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }

    public int auth(String text, String text1) throws ClassNotFoundException {
        System.out.println("Auth");
        int result = 0;
        try {
            Main.coos.writeObject("auth");
            Main.coos.writeObject(text);
            Main.coos.writeObject(text1);
            result = (Integer)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void registerNewUser(String text, String text1) throws ClassNotFoundException {
        System.out.println("Register new user");
        int result = 0;
        try {
            Main.coos.writeObject("register");
            Main.coos.writeObject(text);
            Main.coos.writeObject(text1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int changeBalans(int parseInt) throws ClassNotFoundException {
        System.out.println("change balans");
        int result = 0;
        try {
            Main.coos.writeObject("changeBalans");
            Main.coos.writeObject(parseInt);
            result = (Integer)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Contracts> findContractInBd(Contracts contracts) throws ClassNotFoundException {
        System.out.println("find contract in bd");
        ArrayList<Contracts> list = new ArrayList<Contracts>();
        try {
            Main.coos.writeObject("findContractInBd");
            Main.coos.writeObject(contracts);
            list = (ArrayList<Contracts>)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
