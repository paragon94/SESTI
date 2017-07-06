package main.java.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.helper.AlertBox;
import main.java.model.entity.Uzytkownik;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.io.IOException;
import java.util.Optional;

public class ZarzadzanieKontamiController {

    private Uzytkownik uzytkownik;


    @FXML
    private TableView<Uzytkownik> uzytkownicy;
    @FXML
    private MenuBar menuBar;
    @FXML
    private AnchorPane kontaOkno;


    @FXML void initialize(){
        wszyscyUzytkownicy();
    }


    @FXML void dodajAction(ActionEvent event) throws IOException{
        Uzytkownik uzytkownik = null;
        uzytkownikAction(uzytkownik, "Nowy użytkownik został dodany do bazy", "Nowy użytkownik");
    }


    @FXML void edytujAction(ActionEvent actionEvent) throws IOException{
        if (uzytkownicy.getSelectionModel().getSelectedItem() !=null) {
            uzytkownikAction(uzytkownicy.getSelectionModel().getSelectedItem(), "Użytkownik edytowany", "Uzytkownik edycja");
        }else
            new AlertBox("Użytkownik edytowany", "Uwaga", new Alert(Alert.AlertType.WARNING));
    }


    private void uzytkownikAction(Uzytkownik uzytkownik, String text, String title) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/zapiszKonto.fxml"));
        Parent parent = loader.load();
        ZapiszKontoController controller = loader.getController();
        controller.setUstawienia(menuBar, this.uzytkownik, kontaOkno);

        if (uzytkownik != null){
            controller.setUzytkownik(uzytkownik);
        }
        controller.setTekst(text);
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(kontaOkno.getScene().getWindow());
        stage.setResizable(false);
        stage.setOnHiding(event -> wszyscyUzytkownicy());
        stage.show();
    }


    @FXML void usunAction(ActionEvent actionEvent) {
        if(uzytkownicy.getSelectionModel().getSelectedIndex() != -1){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy jesteś pewny, że chcesz usunąć użytkownika o loginie " +
                    uzytkownicy.getSelectionModel().getSelectedItem().getLogin() + ", imieniu i nazwisku : " + uzytkownicy.getSelectionModel().getSelectedItem().getImie() + " " + uzytkownicy.getSelectionModel().getSelectedItem().getNazwisko(), ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.YES) {
                Uzytkownik uzytkownik = uzytkownicy.getSelectionModel().getSelectedItem();
                uzytkownik.usun();
                wszyscyUzytkownicy();
            }
        }
        else {
            new AlertBox("Nie zaznaczono rekordu do usunięcia", "Uwaga", new Alert(Alert.AlertType.WARNING));
        }
    }


    private void wszyscyUzytkownicy(){
        ObservableList<Uzytkownik> uzytkownikObservableList = FXCollections.observableArrayList(Uzytkownik.wszyscyUzytkownicy());
        uzytkownicy.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("login"));
        uzytkownicy.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("imie"));
        uzytkownicy.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        uzytkownicy.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));
        uzytkownicy.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("rola"));
        uzytkownicy.setItems(uzytkownikObservableList);
    }


    void setUstawienia(MenuBar menuBar, Uzytkownik uzytkownik) {
        this.menuBar = menuBar;
        this.uzytkownik = uzytkownik;
    }

}
