package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;


public class FlightController {
    public Label idLabel;
    public TextField idField;
    public Label codeLabel;
    public TextField codeField;
    public Label airplaneLabel;
    public TextField airplaneField;
    public Label startOfUsingTheRunwayLabel;
    public DateTimePicker startOfUsingTheRunwayField;
    public Label endOfUsingTheRunwayLabel;
    public DateTimePicker endOfUsingTheRunwayField;
    public Label flightTypeLabel;
    public TextField flightTypeField;
    public Label userLabel;
    public TextField userField;
    public ComboBox<Airplane> airplane;
    public ComboBox<FlightType> flightType;
    public ComboBox<User> user;


    public SimpleStringProperty idProperty;
    public SimpleStringProperty codeProperty;
    public SimpleObjectProperty<Airplane> airplaneProperty;
    public SimpleObjectProperty<LocalDateTime> startOfUsingTheRunwayProperty;
    public SimpleObjectProperty<LocalDateTime> endOfUsingTheRunwayProperty;
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



    @FXML
    public void initialize() {
        airplane.setItems(dao.getAirplanes());
        flightType.setItems(dao.getFlightTypes());
        initializeDataBinding();

        airplane.setConverter(new StringConverter<Airplane>() {
            @Override
            public String toString(Airplane airplane) {
                if (airplane == null)
                    return "";
                return airplane.getAirlineName();
            }

            @Override
            public Airplane fromString(String string) {
                Airplane newAirplane = new Airplane(0, null, string, "", 0);

                return newAirplane;
            }
        });

        user.setItems(dao.getUsers());
        initializeDataBinding();

        user.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                if (user == null)
                    return "";
                return user.getName();
            }

            @Override
            public User fromString(String string) {
                User newUser = new User(0, string, null);

                return newUser;
            }
        });

        flightType.setConverter(new StringConverter<FlightType>() {
            @Override
            public String toString(FlightType flightType) {
                if (flightType == null)
                    return "";
                return flightType.getName();
            }

            @Override
            public FlightType fromString(String string) {
                FlightType newFlightType = new FlightType(0, string);

                return newFlightType;
            }
        });

        //flightType.setItems(dao.getFlightTypes());
        initializeDataBinding();

        if (currentFlight != null) {
            fillForm();
        }
    }

    private void fillForm() {
        idProperty.set(String.valueOf(currentFlight.getId()));
        codeProperty.set(currentFlight.getCode());
        airplaneProperty.setValue(currentFlight.getAirplane());
        startOfUsingTheRunwayProperty.setValue(currentFlight.getStartOfUsingTheRunway());
        endOfUsingTheRunwayProperty.setValue(currentFlight.getEndOfUsingTheRunway());
        flightTypeProperty.setValue(currentFlight.getFlightType());
        userProperty.setValue(currentFlight.getUser());
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        codeField.textProperty().bindBidirectional(codeProperty);
        airplane.valueProperty().bindBidirectional(airplaneProperty);
        //startOfUsingTheRunwayField.valueProperty().bindBidirectional(startOfUsingTheRunwayProperty);
        //endOfUsingTheRunwayField.valueProperty().bindBidirectional(endOfUsingTheRunwayProperty);
        flightType.valueProperty().bindBidirectional(flightTypeProperty);
        user.valueProperty().bindBidirectional(userProperty);
    }

    private void dodajListenere() {
    }

    public void stopFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = currentFlight == null;

            if (currentFlight == null)
                currentFlight = new Flight();

            currentFlight.setId(Integer.valueOf((idProperty.get())));
            currentFlight.setCode(codeProperty.get());
            currentFlight.setAirplane(airplaneProperty.get());
            currentFlight.setStartOfUsingTheRunway(startOfUsingTheRunwayProperty.get());
            currentFlight.setEndOfUsingTheRunway(endOfUsingTheRunwayProperty.get());
            currentFlight.setFlightType(flightTypeProperty.get());
            currentFlight.setUser(userProperty.get());


            if (adding) {
                dao.addFlight(currentFlight);
            } else {
                dao.changeFlight(currentFlight);
            }
            Stage stage = (Stage) okButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }


    public void cancelFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean isFormValid() {
        return true;
    }

}
