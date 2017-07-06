package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.model.entity.Uzytkownik;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.IOException;


public class PanelLogowaniaController {

    @FXML
    private AnchorPane loginOkno;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnReset;
    @FXML
    private TextField tfNazwaUzytkownika;
    @FXML
    private PasswordField pfHaslo;


    @FXML
    public void loginAction(ActionEvent actionEvent) throws IOException {

        Uzytkownik uzytkownik = Uzytkownik.login(tfNazwaUzytkownika.getText(), pfHaslo.getText());

        if (uzytkownik != null){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/oknoGlowne.fxml"));
            Parent parent = loader.load();

            OknoGlowneController controller = loader.getController();
            controller.setUzytkownik(uzytkownik);

            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setMaximized(true);
            stage.show();
    }
        else
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błąd : Niepoprawne dane logowania lub puste pola");
        alert.setContentText("Sprawdź czy wpisana nazwa użytkownika i hasło są prawidłowe lub pola nie są puste");
        alert.showAndWait();
    }
    }

    public void resetAction() throws IOException{
       resetPola();
    }

    private void resetPola() {
        tfNazwaUzytkownika.clear();
        pfHaslo.clear();
    }
}
