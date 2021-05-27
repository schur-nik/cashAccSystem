package server.AbsModels;

import java.io.Serializable;

public class Stat implements Serializable {
    int income;
    int cons;
    int loans;
    int consCons;
    int balans;
    double liquidity;

    public Stat() {
        income = 0;
        cons = 0;
        loans = 0;
        consCons = 0;
        balans = 0;
        liquidity = 0;
    }

    public Stat(int income, int cons, int loans, int consCons, int balans) {
        super();
        this.income = income;
        this.cons = cons;
        this.loans = loans;
        this.consCons = consCons;
        this.balans = balans;
    }

    public double getLiquidity(){
        if (cons+loans == 0) {return 0;}
        System.out.println(this.toString());
        return (balans + (income-cons))/(cons+loans);
    }

    public void setLiquidity(int liquidity) {
        this.liquidity = liquidity;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getCons() {
        return cons;
    }

    public void setCons(int cons) {
        this.cons = cons;
    }

    public int getLoans() {
        return loans;
    }

    public void setLoans(int loans) {
        this.loans = loans;
    }

    public int getConsCons() {
        return consCons;
    }

    public void setConsCons(int consCons) {
        this.consCons = consCons;
    }

    public int getBalans() {
        return balans;
    }

    public void setBalans(int balans) {
        this.balans = balans;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "income=" + income +
                ", cons=" + cons +
                ", loans=" + loans +
                ", consCons=" + consCons +
                ", balans=" + balans +
                '}';
    }
}
