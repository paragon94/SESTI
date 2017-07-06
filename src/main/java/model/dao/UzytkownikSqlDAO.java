package main.java.model.dao;

import main.conf.database.ConnectDatabase;
import main.java.model.entity.Uzytkownik;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UzytkownikSqlDAO extends ConnectDatabase implements UzytkownikDAO {


    @Override
    public void dodaj(Uzytkownik uzytkownik) {

        openConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO uzytkownik (login, imie, nazwisko, email, rola, haslo) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, uzytkownik.getLogin());
            statement.setString(2, uzytkownik.getImie());
            statement.setString(3, uzytkownik.getNazwisko());
            statement.setString(4, uzytkownik.getEmail());
            statement.setString(5, uzytkownik.getRola());
            statement.setString(6, uzytkownik.getHaslo());
            statement.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Dodawanie użytkownika - Błąd" + e.getMessage());

        } finally {

            closeConnection();

        }

    }


    @Override
    public void edytuj(Uzytkownik uzytkownik) {

        openConnection();

        try {

            PreparedStatement stm = connection.prepareStatement("UPDATE uzytkownik SET login = ?, imie = ?, nazwisko = ?, email=?, rola = ?, haslo = ? WHERE id = ?");
            stm.setString(1, uzytkownik.getLogin());
            stm.setString(2, uzytkownik.getImie());
            stm.setString(3, uzytkownik.getNazwisko());
            stm.setString(4, uzytkownik.getEmail());
            stm.setString(5, uzytkownik.getRola());
            stm.setString(6, uzytkownik.getHaslo());
            stm.setInt(7, uzytkownik.getId());
            stm.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Edycja użytkownika - Błąd" + uzytkownik.getLogin() + ":" + e.getMessage());

        } finally {

            closeConnection();

        }
    }


    @Override
    public void usun(Uzytkownik uzytkownik) {

        openConnection();

        try {

            PreparedStatement stm = connection.prepareStatement("DELETE FROM uzytkownik WHERE id = ?");
            stm.setInt(1, uzytkownik.getId());
            stm.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Usuwanie użytkownika - Błąd" + uzytkownik.getLogin() + ":" + e.getMessage());

        } finally {

            closeConnection();

        }
    }


    @Override
    public List<Uzytkownik> wszyscyUzytkownicy() {

        ArrayList<Uzytkownik> uzytkownikList = new ArrayList<>();
        openConnection();

        try {
            ResultSet resultSet;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM uzytkownik ORDER BY rola DESC");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String login = resultSet.getString("login");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                String email = resultSet.getString("email");
                String rola = resultSet.getString("rola");
                String haslo = resultSet.getString("haslo");
                Uzytkownik uzytkownik = new Uzytkownik(login, imie, nazwisko, email, rola, haslo);
                uzytkownik.setId(resultSet.getInt("id"));
                uzytkownikList.add(uzytkownik);

            }
        } catch (SQLException e) {

            System.err.println("Lista użytkowników - Błąd" + e.getMessage());

        } finally {

            closeConnection();

        }

        return uzytkownikList;
    }


    @Override
    public Uzytkownik login(String login, String haslo) {

        Uzytkownik uzytkownik = null;
        openConnection();

        try {

            ResultSet resultSet;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM uzytkownik WHERE login = ? AND haslo = ?");
            statement.setString(1, login);
            statement.setString(2, haslo);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String l = resultSet.getString("login");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                String email = resultSet.getString("email");
                String rola = resultSet.getString("rola");
                String h = resultSet.getString("haslo");
                uzytkownik = new Uzytkownik(l, imie, nazwisko, email, rola, h);
                uzytkownik.setId(resultSet.getInt("id"));

            }
        } catch (SQLException e) {

            System.err.println("Użytkownik błąd " + e.getMessage());

        } finally {

            closeConnection();

        }

        return uzytkownik;
    }


    @Override
    public Uzytkownik szukajId(int id) {

        Uzytkownik uzytkownik = null;
        openConnection();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM uzytkownik WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                String login = resultSet.getString("login");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                String email = resultSet.getString("email");
                String rola = resultSet.getString("rola");
                String haslo = resultSet.getString("haslo");
                uzytkownik = new Uzytkownik(login, imie, nazwisko, email, rola, haslo);
                uzytkownik.setId(resultSet.getInt("id"));

            }
        }catch (SQLException e){

            e.printStackTrace();

        }finally {

            closeConnection();

        }

        return uzytkownik;
    }
}
