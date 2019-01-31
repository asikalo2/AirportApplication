package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PassengerController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public Label flightLabel;
    public TextField flightField;
    public Label qrCodeLabel;
    public TextField qrCodeField;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;
    public SimpleStringProperty flightProperty;
    public SimpleStringProperty qrCodeProperty;

    private AirportDAO dao;
    private Passenger currentPassenger = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public PassengerController(AirportDAO dao, Passenger passenger) {
        this.dao = dao;
        this.currentPassenger = passenger;
        idProperty = new SimpleStringProperty("");
        nameProperty = new SimpleStringProperty("");
        flightProperty = new SimpleStringProperty("");
        qrCodeProperty = new SimpleStringProperty("");
    }

}
