package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LuggageController {
    public Label idLabel;
    public TextField idField;
    public Label passengerLabel;
    public TextField passengerField;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty passengerProperty;

    private AirportDAO dao;
    private Luggage currentLuggage = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public LuggageController(AirportDAO dao, Luggage luggage) {
        this.dao = dao;
        this.currentLuggage = luggage;
        idProperty = new SimpleStringProperty("");
        passengerProperty = new SimpleStringProperty("");
    }

}
