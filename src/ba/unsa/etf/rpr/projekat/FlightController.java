package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class FlightController {
    public Label idLabel;
    public TextField idField;
    public Label codeLabel;
    public TextField codeField;
    public Label airplaneLabel;
    public TextField airplaneField;
    public Label startOfUsingTheRunwayLabel;
    public DatePicker startOfUsingTheRunwayField;
    public Label endOfUsingTheRunwayLabel;
    public DatePicker endOfUsingTheRunwayField;
    public Label flightTypeLabel;
    public TextField flightTypeField;
    public Label userLabel;
    public TextField userField;


    public SimpleStringProperty idProperty;
    public SimpleStringProperty codeProperty;
    public SimpleObjectProperty<Airplane> airplaneProperty;
    public SimpleObjectProperty<LocalDate> startOfUsingTheRunwayProperty;
    public SimpleObjectProperty<LocalDate> endOfUsingTheRunwayProperty;
    public SimpleObjectProperty<FlightType> flightTypeProperty;
    public SimpleObjectProperty<User> userProperty;

    private AirportDAO dao;
    private Flight currentFlight = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public FlightController(AirportDAO dao, Flight flight) {
        this.dao = dao;
        this.currentFlight = flight;
        idProperty = new SimpleStringProperty("");
        codeProperty = new SimpleStringProperty("");
        airplaneProperty = new SimpleObjectProperty<>();
        startOfUsingTheRunwayProperty = new SimpleObjectProperty<>();
        endOfUsingTheRunwayProperty = new SimpleObjectProperty<>();
        flightTypeProperty = new SimpleObjectProperty<>();
        userProperty = new SimpleObjectProperty<>();
    }
}
