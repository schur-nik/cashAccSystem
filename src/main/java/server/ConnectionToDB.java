package server;


import server.AbsModels.*;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionToDB implements Runnable{
    private static final String URL="jdbc:mysql://localhost:3306/testproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";
    private static Connection conn = null;
    private static PreparedStatement ps = null;

    public void run(){
        System.out.println("DB connecting....");
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect done....");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String testF() throws SQLException {
        String test = null;
        return test;
    }

    public static ArrayList<Contracts> getListContractsFromBd() throws SQLException {
        ArrayList<Contracts> list = new ArrayList<Contracts>();
        ps = conn.prepareStatement("SELECT idcontracts, companyName, sum(income), sum(consumption), dateBegin, dateEnd " +
                "FROM " +
                "(SELECT idcontracts, " +
                "       companyName, " +
                "       income, " +
                "       consumption, " +
                "       dateBegin, " +
                "       dateEnd " +
                "FROM testproject.contracts " +
                "UNION ALL " +
                "SELECT id, " +
                "       \"\" cmpName, " +
                "       sum(incomeDay) as inc, " +
                "       sum(consumptionDay) as cons, " +
                "\t   \"2020-01-01\" dateB, " +
                "       \"2020-01-01\" dateE " +
                "  FROM testproject.incomeday  " +
                "GROUP BY id)tbl " +
                "GROUP BY idcontracts");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            list.add(new Contracts(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4),
                            resultSet.getDate(5),
                            resultSet.getDate(6)
                    )
            );
        }
        return list;
    }

    public static ArrayList<InfoContract> getListIncomeDayFromBd(int readInt) throws SQLException{
        ArrayList<InfoContract> list = new ArrayList<InfoContract>();
        ps = conn.prepareStatement("SELECT incomeDay, consumptionDay, date FROM testproject.incomeday WHERE id = ?");
        ps.setInt(1, readInt);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            list.add(new InfoContract(resultSet.getInt(1),resultSet.getInt(2), resultSet.getDate(3)));
        }
        return list;
    }

    public static ArrayList<Contracts> findContractInBd(Contracts readObject) throws SQLException {
        ArrayList<Contracts> list = new ArrayList<Contracts>();
        String id, name, inc, coms;
        if (readObject.getIdContracts() == 0) id = "%";
        else id = String.valueOf(readObject.getIdContracts())+"%";
        if (readObject.getNameCompany().length() == 0) name = "%";
        else name = readObject.getNameCompany()+"%";
        if (readObject.getIncome() == 0) inc = "%";
        else inc = String.valueOf(readObject.getIncome())+"%";
        if (readObject.getComsumption() == 0) coms = "%";
        else coms = String.valueOf(readObject.getComsumption())+"%";
        ps = conn.prepareStatement(
                "SELECT * " +
                        "FROM " +
                         "(SELECT idcontracts, companyName, sum(income) income, sum(consumption) consumption, dateBegin, dateEnd " +
                             "FROM " +
                              "(SELECT idcontracts, companyName, income, consumption, dateBegin, dateEnd " +
                                 "FROM testproject.contracts " +
                              "UNION ALL " +
                               "SELECT id, \"\" cmpName, sum(incomeDay) as inc, sum(consumptionDay) as cons, \"2020-01-01\" dateB, \"2020-01-01\" dateE " +
                                 "FROM testproject.incomeday " +
                              "GROUP BY id)tbl " +
                           "GROUP BY idcontracts )tbl2 " +
                       "WHERE idcontracts like '"+id+"' and companyName like '"+name+"' and income like '"+inc+"' and consumption like '"+coms+"'");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            list.add(new Contracts(resultSet.getInt(1),
                                   resultSet.getString(2),
                                   resultSet.getInt(3),
                                   resultSet.getInt(4),
                                   resultSet.getDate(5),
                                   resultSet.getDate(6)));
        }
        return list;
    }

    public static  ArrayList<Loans>  getListLoansFromBd() throws SQLException {
        ArrayList<Loans> list = new ArrayList<Loans>();
        ps = conn.prepareStatement("SELECT * FROM testproject.loans");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            list.add(new Loans(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5),
                            resultSet.getDate(6),
                            resultSet.getDate(7)
                    )
            );
        }
        return list;
    }

    public static ArrayList<Cons> getListConsFromBd() throws SQLException  {
        ArrayList<Cons> list = new ArrayList<Cons>();
        ps = conn.prepareStatement("SELECT * FROM testproject.cons");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            list.add(new Cons(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt(3)
                    )
            );
        }
        return list;
    }

    public static Stat getStatFromBd(Date begin, Date end) throws SQLException {
        Stat stat = new Stat();
        ResultSet resultSet;
        int resultCons = 0;
        ps = conn.prepareStatement("SELECT sum(incomeDay), sum(consumptionDay) FROM testproject.incomeday where date between ? and ?");
        ps.setDate(1, begin);
        ps.setDate(2, end);
        resultSet = ps.executeQuery();
        while (resultSet.next()){
            stat.setIncome(resultSet.getInt(1));
            stat.setCons(resultSet.getInt(2));
        }

        int countDays= (int)((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        ps = conn.prepareStatement("SELECT sum(consumption)*"+countDays+"/30 FROM testproject.cons");
        resultSet = ps.executeQuery();
        while (resultSet.next()){
            resultCons += resultSet.getDouble(1);
        }
        stat.setConsCons(resultCons);

        ArrayList<Loans> list = getListLoansFromBd();
        int res = 0;
        for (Loans loan : list) {
            Date dt = loan.getDateBegin();
            if (begin.after(dt)) {dt = begin;}
            while ((dt.before(end) || dt.equals(end)) && (dt.before(loan.getDateEnd()) || dt.equals(loan.getDateEnd()))) {
                res += loan.getSumDay();
                dt = Date.valueOf(dt.toLocalDate().plusDays(1));
            }
        }
        stat.setLoans(res);

        ps = conn.prepareStatement("SELECT COALESCE((SELECT balans FROM testproject.balans WHERE (dateBalans between ? and ?) order by id DESC LIMIT 1), (SELECT balans from testproject.balans WHERE dateBalans < ? order by id DESC LIMIT 1), 0) FROM dual");
        ps.setDate(1, begin);
        ps.setDate(2, end);
        ps.setDate(3, begin);
        resultSet = ps.executeQuery();
        while (resultSet.next()){
            stat.setBalans(resultSet.getInt(1));
        }

        return stat;
    }


    public static Integer getBalansFromBd(Date begin, Date end) throws SQLException {
        ps = conn.prepareStatement("SELECT COALESCE((SELECT balans FROM testproject.balans WHERE (dateBalans between ? and ?) order by id DESC LIMIT 1), (SELECT balans from testproject.balans WHERE dateBalans < ? order by id DESC LIMIT 1), 0) FROM dual");
        ps.setDate(1, begin);
        ps.setDate(2, end);
        ps.setDate(3, begin);
        int result = 0;
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            result = resultSet.getInt(1);
        }
        return result;
    }

    public static int changeBalans(Integer readObject) throws SQLException {
        int result = 0;
        ps = conn.prepareStatement("SELECT balans FROM testproject.balans order by id DESC LIMIT 1");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            result = resultSet.getInt(1);
        }
        result += readObject;
        ps = conn.prepareStatement("INSERT INTO testproject.balans (balans, dateBalans) VALUES (?,?)");
        ps.setInt(1, result);
        ps.setDate(2, new Date(System.currentTimeMillis()));
        ps.executeUpdate();
        return result;
    }

    public static int getDebtResultFromBd(Date begin, Date end) throws SQLException {
        int result = 0;
        ArrayList<Loans> list = getListLoansFromBd();
        int countDays= (int)((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        ps = conn.prepareStatement("SELECT sum(consumption)*"+countDays+"/30 FROM testproject.cons");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            result += resultSet.getDouble(1);
        }
        int res = 0;
        for (Loans loan : list) {
            System.out.println(loan.toString());
            Date dt = loan.getDateBegin();
            if (begin.after(dt)) {dt = begin;}
            while ((dt.before(end) || dt.equals(end)) && (dt.before(loan.getDateEnd()) || dt.equals(loan.getDateEnd()))) {
                res += loan.getSumDay();
                dt = Date.valueOf(dt.toLocalDate().plusDays(1));
            }
        }
        result += res;
        return result;
    }

    public static int auth(String login, String pass) throws SQLException {
        int result = 0;
        ps = conn.prepareStatement("SELECT idUser FROM testproject.accounts WHERE login = ? AND password = ?");
        ps.setString(1, login);
        ps.setString(2, pass);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            result = resultSet.getInt(1);
        }
        return result;
    }

    public static void registerNewUser(String login, String pass) throws SQLException {
        int result = 0;
        ps = conn.prepareStatement("INSERT INTO testproject.accounts (login, password) VALUES (?,?)");
        ps.setString(1, login);
        ps.setString(2, pass);
        ps.executeUpdate();
    }

    public static void addContractToBd(Contracts readObject) throws SQLException {
        ps = conn.prepareStatement("INSERT INTO contracts (idcontracts, companyName, income, consumption, dateBegin, dateEnd) VALUES (?,?,?,?,?,?)");
        ps.setInt(1, readObject.getIdContracts());
        ps.setString(2, readObject.getNameCompany());
        ps.setInt(3, readObject.getIncome());
        ps.setInt(4, readObject.getComsumption());
        ps.setDate(5, readObject.getDateBegin());
        ps.setDate(6, readObject.getDateEnd());
        ps.executeUpdate();
    }

    public static void addConsToBd(Cons readObject) throws SQLException {
        ps = conn.prepareStatement("INSERT INTO cons (name, consumption) VALUES (?,?)");
        ps.setString(1, readObject.getName());
        ps.setInt(2, readObject.getCons());
        ps.executeUpdate();
    }

    public static void editConsToBd(Cons readObject) throws SQLException {
        ps = conn.prepareStatement("UPDATE cons SET name = ?, consumption = ? WHERE id = ?");
        ps.setString(1, readObject.getName());
        ps.setInt(2, readObject.getCons());
        ps.setInt(3, readObject.getIdCons());
        ps.executeUpdate();
    }


    public static void addInfoInContractToBd(Integer selectId, InfoContract obj) throws SQLException {
        ps = conn.prepareStatement("INSERT INTO incomeday (id, incomeDay, consumptionDay, date) VALUES (?,?,?,?)");
        ps.setInt(1, selectId);
        ps.setInt(2, obj.getIncome());
        ps.setInt(3, obj.getCons());
        ps.setDate(4, obj.getDateDay());
        ps.executeUpdate();
    }

    public static void deleteContractFromBd(Contracts readObject) throws SQLException {
        ps = conn.prepareStatement("delete from contracts WHERE idcontracts = ?");
        ps.setInt(1, readObject.getIdContracts());
        ps.executeUpdate();
    }

    public static void deleteInfoContractFromBd(int selectIdContract, InfoContract readObject) throws SQLException {
        ps = conn.prepareStatement("delete from incomeday WHERE id = ? and date = ? and incomeDay = ? and consumptionDay = ?");
        ps.setInt(1, selectIdContract);
        ps.setDate(2, readObject.getDateDay());
        ps.setInt(3, readObject.getIncome());
        ps.setInt(4, readObject.getCons());
        ps.executeUpdate();
    }

    public static void editContractFromBd(Contracts readObject) throws SQLException {
        ps = conn.prepareStatement("UPDATE contracts SET idcontracts = ?, companyName = ?, income = ?, consumption = ?, dateBegin = ?, dateEnd = ? WHERE idcontracts = ?");
        ps.setInt(1, readObject.getIdContracts());
        ps.setString(2, readObject.getNameCompany());
        ps.setInt(3, readObject.getIncome());
        ps.setInt(4, readObject.getComsumption());
        ps.setDate(5, readObject.getDateBegin());
        ps.setDate(6, readObject.getDateEnd());
        ps.setInt(7, readObject.getIdContracts());
        ps.executeUpdate();
    }

    public static void addLoanToBd(Loans readObject) throws SQLException {
        ps = conn.prepareStatement("insert into loans (id, name, companyName, allsum, sumDay, dateBegin, dateEnd) VALUES (?,?,?,?,?,?,?)");
        ps.setInt(1, readObject.getIdContracts());
        ps.setString(2, readObject.getName());
        ps.setString(3, readObject.getNameCompany());
        ps.setInt(4, readObject.getAllSum());
        ps.setInt(5, readObject.getSumDay());
        ps.setDate(6, readObject.getDateBegin());
        ps.setDate(7, readObject.getDateEnd());
        ps.executeUpdate();
    }

    public static void editLoanFromBd(Loans readObject) throws SQLException {
        ps = conn.prepareStatement("UPDATE loans SET id = ?, name = ?, companyName = ?, allsum = ?, sumDay = ?, dateBegin = ?, dateEnd = ? WHERE id = ?");
        ps.setInt(1, readObject.getIdContracts());
        ps.setString(2, readObject.getName());
        ps.setString(3, readObject.getNameCompany());
        ps.setInt(4, readObject.getAllSum());
        ps.setInt(5, readObject.getSumDay());
        ps.setDate(6, readObject.getDateBegin());
        ps.setDate(7, readObject.getDateEnd());
        ps.setInt(8, readObject.getIdContracts());
        ps.executeUpdate();
    }

}