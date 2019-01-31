package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AirlineController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public Label codeLabel;
    public TextField codeField;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;
    public SimpleStringProperty codeProperty;
    private AirportDAO dao;
    private Airline currentAirline = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public AirlineController(AirportDAO dao, Airline airline) {
        this.dao = dao;
        this.currentAirline = airline;
        idProperty = new SimpleStringProperty("");
        nameProperty = new SimpleStringProperty("");
        codeProperty = new SimpleStringProperty("");
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
        codeField.textProperty().bindBidirectional(codeProperty);
    }

   /* private void fillForm() {
        idProperty.set(currentAirline.getId());
        nameProperty.set(currentAirline.getName());
        codeProperty.set(currentAirline.getCode());
    }*/

    private void dodajListenere() {}

    public void stopFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
