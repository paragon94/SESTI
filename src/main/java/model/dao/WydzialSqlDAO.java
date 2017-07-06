package main.java.model.dao;


import main.conf.database.ConnectDatabase;
import main.java.model.entity.Wydzial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WydzialSqlDAO extends ConnectDatabase implements WydzialDAO {


    @Override
    public void dodaj(Wydzial wydzial) {

        openConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO wydzial (nazwa, symbol, numerKonta, uwagi)" +
                    " VALUES (?, ?, ?, ?)");
            statement.setString(1, wydzial.getNazwa());
            statement.setString(2, wydzial.getSymbol());
            statement.setString(3, wydzial.getNumerKonta());
            statement.setString(4, wydzial.getUwagi());
            statement.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Wydział dodawanie - Błąd" + e.getMessage());

        } finally {

            closeConnection();

        }
    }


    @Override
    public void edytuj(Wydzial wydzial) {

        openConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE wydzial SET nazwa = ?, symbol = ?, numerKonta = ?, uwagi = ? WHERE id = ?");
            statement.setString(1, wydzial.getNazwa());
            statement.setString(2, wydzial.getSymbol());
            statement.setString(3, wydzial.getNumerKonta());
            statement.setString(4, wydzial.getUwagi());
            statement.setInt(5, wydzial.getId());
            statement.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Wydział edycja - Błąd"  + e.getMessage());

        } finally {

            closeConnection();

        }
    }


    @Override
    public void usun(Wydzial wydzial) {

        openConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM wydzial WHERE id = ?");
            statement.setInt(1, wydzial.getId());
            statement.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Wydział usuwanie - Błąd" + e.getMessage());

        } finally {

            closeConnection();

        }
    }


    @Override
    public List<Wydzial> wszystkieWydzialy() {

        ArrayList<Wydzial> wydzialArrayList = new ArrayList<>();
        openConnection();

        try {

            ResultSet resultSet;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM wydzial");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nazwa = resultSet.getString("nazwa");
                String symbol = resultSet.getString("symbol");
                String numerKonta = resultSet.getString("numerKonta");
                String uwagi = resultSet.getString("uwagi");
                Wydzial wydzial = new Wydzial(nazwa, symbol, numerKonta, uwagi);
                wydzial.setId(resultSet.getInt("id"));
                wydzialArrayList.add(wydzial);

            }
        } catch (SQLException e) {

            System.err.println("Wydział - Błąd" + e.getMessage());

        } finally {

            closeConnection();

        }

        return wydzialArrayList;
    }


    @Override
    public Wydzial szukajId(int id) {

        Wydzial wydzial = null;
        openConnection();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM wydzial WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                wydzial = new Wydzial(resultSet.getString("nazwa"), resultSet.getString("symbol"), resultSet.getString("numerKonta"), resultSet.getString("uwagi"));
                wydzial.setId(resultSet.getInt("id"));

            }
        }catch (SQLException e){

            e.printStackTrace();

        }finally {

            closeConnection();

        }

        return wydzial;
    }


    @Override
    public List<Wydzial> szukajWydzial(String nazwa, String symbol, String numerKonta, String uwagi) {

        ArrayList<Wydzial> wydzialArrayList = new ArrayList<>();
        openConnection();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM wydzial WHERE nazwa LIKE ? OR symbol LIKE ? OR numerKonta LIKE ? OR uwagi LIKE ?");
            statement.setString(1, "%"+nazwa+"%");
            statement.setString(2, "%"+symbol+"%");
            statement.setString(3, "%"+numerKonta+"%");
            statement.setString(4, "%"+uwagi+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Wydzial wydzial = new Wydzial(resultSet.getString("nazwa"), resultSet.getString("symbol"), resultSet.getString("numerKonta"), resultSet.getString("uwagi"));
                wydzial.setId(resultSet.getInt("id"));
                wydzialArrayList.add(wydzial);

            }
        }catch (SQLException e){

            e.printStackTrace();

        }finally {

            closeConnection();

        }

        return wydzialArrayList;

    }

}
