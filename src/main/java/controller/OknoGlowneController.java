package main.java.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.model.entity.Uzytkownik;

import java.io.IOException;



public class OknoGlowneController {


    @FXML
    public AnchorPane oknoGlowne;
    @FXML
    public Menu uzytkownik;
    @FXML
    public Menu administrator;
    @FXML
    public MenuBar menuBar;
    @FXML
    public Menu menu;
    @FXML
    public MenuItem btnWyloguj;
    @FXML
    public MenuItem btnZakoncz;


    public static Uzytkownik uzytk;


    @FXML void uzytkownicyZarzadanieAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/zarzadzanieKontami.fxml"));
        Parent parent = loader.load();
        ZarzadzanieKontamiController zarzadzanieKontamiController = loader.getController();
        zarzadzanieKontamiController.setUstawienia(this.menuBar, uzytk);
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(oknoGlowne.getScene().getWindow());
        stage.setResizable(false);
        stage.show();
    }


    void setUzytkownik(Uzytkownik uzytkownik) {
        uzytk = uzytkownik;
        menu.setText(uzytkownik.getLogin());
        if (uzytkownik.getRola().equals("uzytkownik"))
            this.uzytkownik.setVisible(true);
        if (uzytkownik.getRola().equals("administrator"))
            administrator.setVisible(true);
    }


    @FXML void wydzialAction(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/wydzial.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Wydział");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(oknoGlowne.getScene().getWindow());
        stage.setResizable(true);
        stage.show();
    }



    @FXML void wylogujAction(ActionEvent actionEvent) throws IOException {
        wyloguj();
    }


    private void wyloguj() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/panelLogowania.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }


    @FXML void zakonczAction(ActionEvent actionEvent) {
        zakoncz();
    }


    private void zakoncz() {
        Platform.exit();
        System.exit(0);
    }

}
