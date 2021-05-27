package server.AbsModels;

import java.io.Serializable;
import java.sql.Date;

public class InfoContract implements Serializable {
    private int income;
    private int cons;
    private Date dateDay;

    public InfoContract(int income, int cons, Date dateDay) {
        super();
        this.income = income;
        this.cons = cons;
        this.dateDay = dateDay;
    }

    public int getCons() {
        return cons;
    }

    public void setCons(int cons) {
        this.cons = cons;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public Date getDateDay() {
        return dateDay;
    }

    public void setDateDay(Date dateDay) {
        this.dateDay = dateDay;
    }

    @Override
    public String toString() {
        return "InfoContract{" +
                "income=" + income +
                ", cons=" + cons +
                ", dateDay=" + dateDay +
                '}';
    }
}