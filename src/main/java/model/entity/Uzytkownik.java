package main.java.model.entity;


import main.java.model.dao.UzytkownikDAO;
import main.java.model.dao.UzytkownikSqlDAO;

import java.util.List;

public class Uzytkownik {


    private int id;
    private String login;
    private String imie;
    private String nazwisko;
    private String email;
    private String rola;
    private String haslo;



    public Uzytkownik(String login, String imie, String nazwisko, String email, String rola, String haslo) {
        this.login = login;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.rola = rola;
        this.haslo = haslo;
    }

    public static Uzytkownik login(String login, String haslo) {
        return dao.login(login, haslo);
    }

    public static List<Uzytkownik> wszyscyUzytkownicy() {
        return dao.wszyscyUzytkownicy();
    }

    public static Uzytkownik szukajId(int id) {
        return dao.szukajId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login){ this.login = login; }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    private static UzytkownikDAO dao = new UzytkownikSqlDAO();

    public static UzytkownikDAO getDao() {
        return dao;
    }

    public static void setDao(UzytkownikDAO dao) {
        Uzytkownik.dao = dao;
    }

    public void zapisz() {
        if (Uzytkownik.szukajId(this.id) != null){
            dao.edytuj(this);
        }
        else {
            dao.dodaj(this);
        }
    }

    public void usun() {
        dao.usun(this);
    }

    @Override
    public String toString() {
        return login;
    }

}
