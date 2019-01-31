package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FlightTypeController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;

    private AirportDAO dao;
    private FlightType currentFlightType = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public FlightTypeController(AirportDAO dao, FlightType flightType) {
        this.dao = dao;
        this.currentFlightType = flightType;
        idProperty = new SimpleStringProperty("");
        nameProperty = new SimpleStringProperty("");
    }
}
