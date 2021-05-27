package server.AbsModels;

import java.io.Serializable;
import java.sql.Date;

public class Contracts implements Serializable {
    private int idContracts;
    private String nameCompany;
    private int income;
    private int comsumption;
    private Date dateBegin;
    private Date dateEnd;

    public Contracts() {
        this.idContracts = 0;
        this.nameCompany = "";
        this.income = 0;
        this.comsumption = 0;
        this.dateBegin = new Date(System.currentTimeMillis());
        this.dateEnd = new Date(System.currentTimeMillis());
    }

    public Contracts(int id, String name, int inc, int coms, Date dB, Date dE){
        super();
        this.idContracts = id;
        this.nameCompany = name;
        this.income = inc;
        this.comsumption = coms;
        this.dateBegin = dB;
        this.dateEnd = dE;
    }

    public int getIdContracts() {
        return idContracts;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public int getIncome() {
        return income;
    }

    public int getComsumption() {
        return comsumption;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setIdContracts(int idContracts) {
        this.idContracts = idContracts;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setComsumption(int comsumption) {
        this.comsumption = comsumption;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "Contracts{" +
                "idContracts=" + idContracts +
                ", nameCompany='" + nameCompany + '\'' +
                ", income=" + income +
                ", comsumption=" + comsumption +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                '}';
    }
}