package main.java.controller;

import javafx.scene.layout.AnchorPane;
import main.helper.AlertBox;
import main.java.model.entity.Wydzial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;



public class ZapiszWydzialController {


    @FXML
    private AnchorPane zapiszWydzialOkno;
    @FXML
    private TextField nazwa;
    @FXML
    private TextField symbol;
    @FXML
    private TextField numerKonta;
    @FXML
    private TextField uwagi;

    private int id;
    private String tekst;




    public void setWydzial(Wydzial wydzial) {
        nazwa.setText(wydzial.getNazwa());
        symbol.setText(wydzial.getSymbol());
        numerKonta.setText(wydzial.getNumerKonta());
        uwagi.setText(wydzial.getUwagi());
        id = wydzial.getId();
    }


    @FXML
    void zapiszWydzialAction(ActionEvent e) {
        if (nazwa.getText().isEmpty() || symbol.getText().isEmpty() || numerKonta.getText().isEmpty()) {
            new AlertBox("Poniższe pola są wymagane:\nNazwa\nSymbol\nNumer konta",
                    "Błąd", new Alert(Alert.AlertType.ERROR));

        } else {
            Wydzial wydzial = new Wydzial(nazwa.getText(), symbol.getText(), numerKonta.getText(), uwagi.getText());
            wydzial.setId(id);
            wydzial.zapisz();
            new AlertBox(tekst, "Uwaga", new Alert(Alert.AlertType.INFORMATION));
            zapiszWydzialOkno.getScene().getWindow().hide();
        }
    }


    public void czyscWydzialAction() {
        czyscWydzial();
    }


    private void czyscWydzial() {
        nazwa.setText("");
        symbol.setText("");
        numerKonta.setText("");
        uwagi.setText("");
    }


    void setTekst(String tekst) {
        this.tekst = tekst;
    }
}


