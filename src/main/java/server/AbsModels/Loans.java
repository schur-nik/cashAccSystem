package server.AbsModels;

import java.io.Serializable;
import java.sql.Date;

public class Loans implements Serializable {
    private int idContracts;
    private String name;
    private String nameCompany;
    private int allSum;
    private int sumDay;
    private Date dateBegin;
    private Date dateEnd;

    public Loans(int idContracts, String name, String nameCompany, int allSum, int sumDay, Date dateBegin, Date dateEnd) {
        this.idContracts = idContracts;
        this.name = name;
        this.nameCompany = nameCompany;
        this.allSum = allSum;
        this.sumDay = sumDay;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public int getIdContracts() {
        return idContracts;
    }

    public void setIdContracts(int idContracts) {
        this.idContracts = idContracts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public int getAllSum() {
        return allSum;
    }

    public void setAllSum(int allSum) {
        this.allSum = allSum;
    }

    public int getSumDay() {
        return sumDay;
    }

    public void setSumDay(int sumDay) {
        this.sumDay = sumDay;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "Loans{" +
                "idContracts=" + idContracts +
                ", name='" + name + '\'' +
                ", nameCompany='" + nameCompany + '\'' +
                ", allSum=" + allSum +
                ", sumDay=" + sumDay +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                '}';
    }

}