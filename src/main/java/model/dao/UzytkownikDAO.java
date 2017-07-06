package main.java.model.dao;

import main.java.model.entity.Uzytkownik;
import java.util.List;



public interface UzytkownikDAO {

    void dodaj(Uzytkownik uzytkownik);

    void edytuj(Uzytkownik uzytkownik);

    void usun(Uzytkownik uzytkownik);

    List<Uzytkownik> wszyscyUzytkownicy();

    Uzytkownik login(String login, String haslo);

    Uzytkownik szukajId(int id);


}
