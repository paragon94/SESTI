package main.helper;

import javafx.scene.control.Alert;


public class AlertBox extends Alert {

    public AlertBox(String m, String t, Alert a) {
        super(a.getAlertType());
        this.setTitle(t);
        this.setHeaderText(m);
        this.showAndWait();
    }

}