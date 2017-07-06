package main.java.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.conf.database.ConnectDatabase;
import main.java.model.entity.Wydzial;

public class PodgladWydzialController extends ConnectDatabase{


    @FXML
    private Label nazwaLabel;
    @FXML
    private Label symbolLabel;
    @FXML
    private Label nrKontaLabel;
    @FXML
    private Label uwagiLabel;

    private int id;


    public void setWydzial(Wydzial wydzial) {

        id = wydzial.getId();

        nazwaLabel.setText(wydzial.getNazwa());
        symbolLabel.setText(wydzial.getSymbol());
        nrKontaLabel.setText(wydzial.getNumerKonta());
        uwagiLabel.setText(wydzial.getUwagi());

    }


}
