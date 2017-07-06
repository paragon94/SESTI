package main.java.controller;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.conf.database.ConnectDatabase;
import main.helper.AlertBox;
import main.java.model.entity.Wydzial;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;



public class WydzialController extends ConnectDatabase {




    @FXML
    private TextField szukajWydzial;
    @FXML
    private Button btnExcelWydzial;
    @FXML
    private Button btnPDFWydzial;
    @FXML
    private
    TableView<Wydzial> tabelaWydzial;
    @FXML
    private AnchorPane oknoWydzial;
    @FXML
    private Button btnDodaj;
    @FXML
    private Button btnEdytuj;
    @FXML
    private Button btnUsun;

    private Stage stage;


    @FXML
    public void initialize() {
        ukryjPrzyciski();
        aktualizujWydzial();
        szukajWydzial();
    }


    private void ukryjPrzyciski() {

        if(OknoGlowneController.uzytk != null){

            if(OknoGlowneController.uzytk.getRola().equals("administrator")) {

                btnDodaj.setVisible(true);
                btnEdytuj.setVisible(true);
                btnUsun.setVisible(true);

            }
            else {

                btnDodaj.setVisible(false);
                btnEdytuj.setVisible(false);
                btnUsun.setVisible(false);
            }


        }
    }


    private void aktualizujWydzial() {
        tabelaWydzial.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        tabelaWydzial.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("symbol"));
        tabelaWydzial.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("numerKonta"));
        tabelaWydzial.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("uwagi"));
        ObservableList<Wydzial> wydzialObservableList = FXCollections.observableArrayList(Wydzial.wszystkieWydzialy());
        tabelaWydzial.setItems(wydzialObservableList);
    }


    @FXML
    void dodajWydzialAction(ActionEvent actionEvent) throws IOException {
        wydzialAction(null, "Nowy wydział", "Nowy wydział");
    }


    @FXML
    void edytujWydzialAction(ActionEvent actionEvent) throws IOException {
        if (tabelaWydzial.getSelectionModel().getSelectedItem() != null)
            wydzialAction(tabelaWydzial.getSelectionModel().getSelectedItem(), "Wydział został edytowany", "Edycja wydziału");
        else
            new AlertBox("Zaznacz rekord do edycji", "Uwaga", new Alert(Alert.AlertType.WARNING));
    }


    private void wydzialAction(Wydzial wydzial, String text, String title) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/zapiszWydzial.fxml"));
        Parent parent = loader.load();
        ZapiszWydzialController controller = loader.getController();

        if (wydzial != null) {
            controller.setWydzial(wydzial);
        }
        controller.setTekst(text);
        stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(oknoWydzial.getScene().getWindow());
        stage.setResizable(false);
        stage.setOnHiding(event -> aktualizujWydzial());
        stage.show();

    }


    @FXML
    void usunWydzialAction(ActionEvent actionEvent) throws IOException {
        usunWydzial();
    }

    private void usunWydzial() {
        if (tabelaWydzial.getSelectionModel().getSelectedItem() == null) {
            new AlertBox("Zaznacz rekord do usunięcia", "Uwaga", new Alert(Alert.AlertType.WARNING));
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy na pewno chcesz usunąć wydział "
                    + tabelaWydzial.getSelectionModel().getSelectedItem().getNazwa() + " ?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.YES) {
                Wydzial wydzial = tabelaWydzial.getSelectionModel().getSelectedItem();
                wydzial.usun();
                new AlertBox("Wydział został usunięty", "Wydział usuwanie", new Alert(Alert.AlertType.INFORMATION));
                aktualizujWydzial();
            }
        }
    }


    public void podgladWydzialAction() throws IOException {
        podgladWydzial(tabelaWydzial.getSelectionModel().getSelectedItem());

    }

    private void podgladWydzial(Wydzial wydzial) throws IOException {
        if (wydzial != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/podgladWydzial.fxml"));
            Parent podgladWydzial = loader.load();
            PodgladWydzialController controller = loader.getController();
            controller.setWydzial(wydzial);
            Scene podgladWydzial_scene = new Scene(podgladWydzial);
            Stage podgladWydzial_stage = new Stage();
            podgladWydzial_stage.setScene(podgladWydzial_scene);
            podgladWydzial_stage.setTitle("Podgląd wydzialu");
            podgladWydzial_stage.initModality(Modality.WINDOW_MODAL);
            podgladWydzial_stage.initOwner(oknoWydzial.getScene().getWindow());
            podgladWydzial_stage.setResizable(false);
            podgladWydzial_stage.setOnHiding(event -> aktualizujWydzial());
            podgladWydzial_stage.show();
        } else {
            new AlertBox("Wybierz rekord do podglądu", "Uwaga", new Alert(Alert.AlertType.WARNING));
        }

    }

    private void szukajWydzial(){
        szukajWydzial.setOnKeyReleased(event -> {
            TextField text = (TextField) event.getSource();
            if (text.getLength() > 1){
                String nazwa = text.getText();
                String symbol = text.getText();
                String numerKonta = text.getText();
                String uwagi = text.getText();

                tabelaWydzial.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nazwa"));
                tabelaWydzial.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("symbol"));
                tabelaWydzial.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("numerKonta"));
                tabelaWydzial.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("uwagi"));
                ObservableList<Wydzial> wydzialList = FXCollections.observableArrayList(Wydzial.szukajWydzial(nazwa, symbol, numerKonta, uwagi));
                tabelaWydzial.setItems(wydzialList);
            }
            else
                aktualizujWydzial();
        });
    }


    public void excelAction() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(btnExcelWydzial.getScene().getWindow());
        if (file != null) {
            zapiszXLS(file);
            new AlertBox("Plik został zapisany", "Uwaga", new Alert(Alert.AlertType.INFORMATION));
        }
    }

    private void zapiszXLS(File file) {

        openConnection();

        try {

            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(file);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            HSSFSheet hssfSheet = hssfWorkbook.createSheet("Wydzial");
            HSSFRow hssfRow = hssfSheet.createRow((short) 0);
            hssfRow.createCell(0).setCellValue("Nazwa");
            hssfRow.createCell(1).setCellValue("Symbol");
            hssfRow.createCell(2).setCellValue("Numer konta");
            hssfRow.createCell(3).setCellValue("Uwagi");

            hssfSheet.setColumnWidth(0, 12000);
            hssfSheet.setColumnWidth(1, 5000);
            hssfSheet.setColumnWidth(2, 5500);
            hssfSheet.setColumnWidth(3, 7000);

            HSSFRow hssfRow1;

            ResultSet resultSet;
            PreparedStatement statement = connection.prepareStatement("SELECT nazwa, symbol, numerKonta, uwagi FROM wydzial");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int tmp = resultSet.getRow();
                hssfRow1 = hssfSheet.createRow((short) tmp);
                hssfRow1.createCell(0).setCellValue(resultSet.getString(1));
                hssfRow1.createCell(1).setCellValue(resultSet.getString(2));
                hssfRow1.createCell(2).setCellValue(resultSet.getLong(3));
                hssfRow1.createCell(3).setCellValue(resultSet.getString(4));
            }
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            closeConnection();

        } catch (SQLException | IOException e) {

            System.err.println(e);

        }

    }


    public void pdfAction() throws FileNotFoundException {

        FileChooser chfileChooseroser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF Files(*.pdf)", "*.pdf");
        chfileChooseroser.getExtensionFilters().add(extensionFilter);
        File file = chfileChooseroser.showSaveDialog(btnPDFWydzial.getScene().getWindow());
        if (file != null) {
            zapiszPDF(file);
            new AlertBox("Plik został zapisany", "Uwaga", new Alert(Alert.AlertType.INFORMATION));
        }

    }

    private void zapiszPDF(File file) throws FileNotFoundException {

        openConnection();

        try {

            ResultSet resultSet;
            PreparedStatement statement = connection.prepareStatement("SELECT nazwa , symbol, numerKonta, uwagi FROM wydzial");
            resultSet = statement.executeQuery();

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            PdfPTable pdfPTable = new PdfPTable(4);
            float[] columnWidths = new float[] {20f, 12f, 12f, 10f};
            pdfPTable.setWidths(columnWidths);
            pdfPTable.addCell("Nazwa");
            pdfPTable.addCell("Symbol");
            pdfPTable.addCell("Numer konta");
            pdfPTable.addCell("Uwagi");
            PdfPCell pdfPCell;

            while (resultSet.next()) {

                String nazwa = resultSet.getString("nazwa");
                pdfPCell=new PdfPCell(new Phrase(nazwa));
                pdfPTable.addCell(pdfPCell);

                String symbol = resultSet.getString("symbol");
                pdfPCell= new PdfPCell(new Phrase(symbol));
                pdfPTable.addCell(pdfPCell);

                String numerKonta = resultSet.getString("numerKonta");
                pdfPCell= new PdfPCell(new Phrase(numerKonta));
                pdfPTable.addCell(pdfPCell);

                String uwagi = resultSet.getString("uwagi");
                pdfPCell= new PdfPCell(new Phrase(uwagi));
                pdfPTable.addCell(pdfPCell);

            }

            document.add(pdfPTable);
            document.close();
            resultSet.close();
            statement.close();

            closeConnection();

        } catch (FileNotFoundException | DocumentException | SQLException e) {

            e.printStackTrace();

        }
    }

}
