package main.java.model.entity;


import main.java.model.dao.WydzialDAO;
import main.java.model.dao.WydzialSqlDAO;

import java.util.List;

public class Wydzial {


    private int id;
    private String nazwa;
    private String symbol;
    private String numerKonta;
    private String uwagi;

    public Wydzial(String nazwa, String symbol, String numerKonta, String uwagi) {
        this.nazwa = nazwa;
        this.symbol = symbol;
        this.numerKonta = numerKonta;
        this.uwagi = uwagi;
    }

    public Wydzial(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getNumerKonta() {
        return numerKonta;
    }

    public void setNumerKonta(String numerKonta) {
        this.numerKonta = numerKonta;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public static WydzialDAO dao = new WydzialSqlDAO();

    public static Wydzial szukajId(int id) {
        return dao.szukajId(id);
    }

    public static List<Wydzial> wszystkieWydzialy() {
        return dao.wszystkieWydzialy();
    }

    public static List<Wydzial> szukajWydzial(String nazwa, String symbol, String numerKonta, String uwagi) {
        return dao.szukajWydzial(nazwa, symbol, numerKonta, uwagi);
    }

    public void zapisz() {
        if (Wydzial.szukajId(this.id) != null)
            dao.edytuj(this);
        else
            dao.dodaj(this);
    }

    public void usun() {
        dao.usun(this);
    }

    @Override
    public String toString() {
        return nazwa;
    }

}
