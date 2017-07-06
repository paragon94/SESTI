package main.java.controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class MainController extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main/java/view/panelLogowania.fxml"));
        Scene scene = new Scene(root);
        // setUserAgentStylesheet(STYLESHEET_CASPIAN);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    public static void main(String[] args) throws IOException, SQLException {
        launch(args);

    }


}
