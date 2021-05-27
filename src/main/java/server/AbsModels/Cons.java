package server.AbsModels;

import java.io.Serializable;

public class Cons implements Serializable {
    private int idCons;
    private String name;
    private int cons;

    public Cons(int idCons, String name, int cons) {
        this.idCons = idCons;
        this.name = name;
        this.cons = cons;
    }

    public int getIdCons() {
        return idCons;
    }

    public void setIdCons(int idCons) {
        this.idCons = idCons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCons() {
        return cons;
    }

    public void setCons(int cons) {
        this.cons = cons;
    }

    @Override
    public String toString() {
        return "Cons{" +
                "idCons=" + idCons +
                ", name='" + name + '\'' +
                ", cons=" + cons +
                '}';
    }

}