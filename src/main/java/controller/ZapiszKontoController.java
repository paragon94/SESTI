package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.helper.AlertBox;
import main.helper.Invalid;
import main.java.model.entity.Uzytkownik;

import java.io.IOException;


public class ZapiszKontoController {


    @FXML
    private AnchorPane oknoKonto;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Label title;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField login;
    @FXML
    private TextField imie;
    @FXML
    private TextField nazwisko;
    @FXML
    private TextField email;
    @FXML
    private ChoiceBox<String> chRola;
    @FXML
    private TextField haslo;

    private int id;
    private Uzytkownik uzytkownik;
    private String tekst;



    public void zapiszAction() throws IOException {

        if (login.getText().isEmpty() || imie.getText().isEmpty() || nazwisko.getText().isEmpty() || email.getText().isEmpty() || chRola.getSelectionModel().isEmpty() || haslo.getText().isEmpty()) {
            new AlertBox("Poniższe pola są wymagane:\nImie\nNazwisko\nAdres email\nRola\nhaslo",
                    "Uwaga", new Alert(Alert.AlertType.WARNING));
        } else {
            Uzytkownik uzytkownik = new Uzytkownik(login.getText(), imie.getText(), nazwisko.getText(), email.getText(), chRola.getValue(), haslo.getText());
            uzytkownik.setId(id);
            try {
                uzytkownik.zapisz();
                new AlertBox(tekst, "Uwaga", new Alert(Alert.AlertType.INFORMATION));
                if (this.uzytkownik.getId() == uzytkownik.getId()) {
                    new AlertBox("Zajety", "Wyloguj", new Alert(Alert.AlertType.INFORMATION));
                    Parent parent = FXMLLoader.load(getClass().getResource("../view/panelLogowania.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = (Stage) menuBar.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.centerOnScreen();
                    stage.show();
                    anchorPane.getScene().getWindow().hide();
                }
                oknoKonto.getScene().getWindow().hide();
            } catch (Invalid e) {
                new AlertBox(e.getMessage(), "Błąd", new Alert(Alert.AlertType.ERROR));
            } catch (NumberFormatException e) {
                new AlertBox("Zapis konta - Błąd ", "Błąd", new Alert(Alert.AlertType.ERROR));
            }
        }
    }


    void setUzytkownik(Uzytkownik uzytkownik){
            haslo.setText(uzytkownik.getHaslo());
            login.setText(uzytkownik.getLogin());
            imie.setText(uzytkownik.getImie());
            nazwisko.setText(uzytkownik.getNazwisko());
            email.setText(uzytkownik.getEmail());
            chRola.setValue(uzytkownik.getRola());
            id = uzytkownik.getId();
    }


    void setUstawienia(MenuBar menuBar, Uzytkownik uzytkownik, AnchorPane anchorPane) {
        this.menuBar = menuBar;
        this.uzytkownik = uzytkownik;
        this.anchorPane = anchorPane;
    }


    public void czyscAction() {
        czyscKonto();
    }


    private void czyscKonto() {
        haslo.setText("");
        login.setText("");
        imie.setText("");
        nazwisko.setText("");
        email.setText("");
    }


    void setTekst(String tekst) {
        this.tekst = tekst;
    }
}