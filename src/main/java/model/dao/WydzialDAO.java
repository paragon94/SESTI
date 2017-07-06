package main.java.model.dao;


import main.java.model.entity.Wydzial;

import java.util.List;

public interface WydzialDAO {

    void dodaj(Wydzial wydzial);

    void edytuj(Wydzial wydzial);

    void usun(Wydzial wydzial);

    List<Wydzial> wszystkieWydzialy();

    Wydzial szukajId(int id);

    List<Wydzial> szukajWydzial(String nazwa, String symbol, String numerKonta, String uwagi);
}