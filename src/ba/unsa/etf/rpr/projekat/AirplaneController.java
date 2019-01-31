package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AirplaneController {
    public Label idLabel;
    public TextField idField;
    public Label airlineLabel;
    public TextField airlineField;
    public Label manufacturerLabel;
    public TextField manufacturerField;
    public Label typeLabel;
    public TextField typeField;
    public Label numberOfSeatsLabel;
    public TextField numberOfSeatsField;
    public SimpleStringProperty idProperty;
    public SimpleObjectProperty<Airline> airlineProperty;
    public SimpleStringProperty manufacturerProperty;
    public SimpleStringProperty typeProperty;
    public SimpleStringProperty numberOfSeatsProperty;

    private AirportDAO dao;
    private Airplane currentAirplane = null;


    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public AirplaneController(AirportDAO dao, Airplane airplane) {
        this.dao = dao;
        this.currentAirplane = airplane;
        idProperty = new SimpleStringProperty("");
        airlineProperty = new SimpleObjectProperty<>();
        manufacturerProperty = new SimpleStringProperty("");
        typeProperty = new SimpleStringProperty("");
        numberOfSeatsProperty = new SimpleStringProperty("");
    }

}
