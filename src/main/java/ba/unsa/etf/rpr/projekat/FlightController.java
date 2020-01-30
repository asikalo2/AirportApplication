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
    public TextField codeField;
    public DateTimePicker startOfUsingTheRunwayField;
    public DateTimePicker endOfUsingTheRunwayField;
    public ComboBox<Airplane> airplane;
    public ComboBox<FlightType> flightType;
    public ComboBox<User> user;
    public ComboBox<Gate> gate;


    public SimpleStringProperty idProperty;
    public SimpleStringProperty codeProperty;
    public SimpleObjectProperty<Airplane> airplaneProperty;
    public SimpleObjectProperty<LocalDateTime> startOfUsingTheRunwayProperty;
    public SimpleObjectProperty<LocalDateTime> endOfUsingTheRunwayProperty;
    public SimpleObjectProperty<FlightType> flightTypeProperty;
    public SimpleObjectProperty<User> userProperty;
    public SimpleObjectProperty<Gate> gateProperty;

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
        gateProperty = new SimpleObjectProperty<>();
    }

    @FXML
    public void initialize() {
        airplane.setItems(dao.getAirplanes());
        flightType.setItems(dao.getFlightTypes());
        gate.setItems(dao.getGates());
        initializeDataBinding();

        airplane.setConverter(new StringConverter<Airplane>() {
            @Override
            public String toString(Airplane airplane) {
                if (airplane == null) {
                    return "";
                }
                return airplane.getAirlineName();
            }

            @Override
            public Airplane fromString(String string) {
                Airplane newAirplane = null;
                try {
                    newAirplane = new Airplane(0, null, string, "", 0);
                } catch (IllegalNumberOfSeats illegalNumberOfSeats) {
                    illegalNumberOfSeats.printStackTrace();
                }

                return newAirplane;
            }
        });

        user.setItems(dao.getUsers());
        initializeDataBinding();
        addListeners();
        user.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                if (user == null) {
                    return "";
                }
                return user.getName();
            }

            @Override
            public User fromString(String string) {
                User newUser = new User(0, string, null);

                return newUser;
            }
        });

        gate.setConverter(new StringConverter<Gate>() {
            @Override
            public String toString(Gate gate) {
                if (gate == null) {
                    return "N/A";
                }
                return gate.getName();
            }

            @Override
            public Gate fromString(String string) {
                Gate newGate = new Gate(0, string);
                return newGate;
            }
        });

        flightType.setConverter(new StringConverter<FlightType>() {
            @Override
            public String toString(FlightType flightType) {
                if (flightType == null) {
                    return "";
                }
                return flightType.getName();
            }

            @Override
            public FlightType fromString(String string) {
                FlightType newFlightType = new FlightType(0, string);

                return newFlightType;
            }
        });

        flightType.setItems(dao.getFlightTypes());
        initializeDataBinding();

        if (currentFlight != null) {
            fillForm();
        }
        else {
            idField.setDisable(true);
            idProperty.set(String.valueOf(dao.highestIdFlight()+1));
        }
    }

    public void fillForm() {
        idProperty.set(String.valueOf(currentFlight.getId()));
        codeProperty.set(currentFlight.getCode());
        airplaneProperty.setValue(currentFlight.getAirplane());
        startOfUsingTheRunwayProperty.setValue(currentFlight.getStartOfUsingTheRunway());
        endOfUsingTheRunwayProperty.setValue(currentFlight.getEndOfUsingTheRunway());

        if(startOfUsingTheRunwayField!= null && endOfUsingTheRunwayField!=null) {
            startOfUsingTheRunwayField.setDateTimeValue(currentFlight.getStartOfUsingTheRunway());
            endOfUsingTheRunwayField.setDateTimeValue(currentFlight.getEndOfUsingTheRunway());
        }
        flightTypeProperty.setValue(currentFlight.getFlightType());
        userProperty.setValue(currentFlight.getUser());
        gateProperty.setValue(currentFlight.getGate());

    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        codeField.textProperty().bindBidirectional(codeProperty);
        airplane.valueProperty().bindBidirectional(airplaneProperty);
        flightType.valueProperty().bindBidirectional(flightTypeProperty);
        user.valueProperty().bindBidirectional(userProperty);
        gate.valueProperty().bindBidirectional(gateProperty);
    }

    private void addListeners() {
        codeField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.isValidString(n)) {
                codeField.getStyleClass().removeAll("notCorrect");
                codeField.getStyleClass().add("correct");
            }
            else {
                codeField.getStyleClass().removeAll("correct");
                codeField.getStyleClass().add("notCorrect");
            }
        });

        idField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.validateNumber(n)) {
                idField.getStyleClass().removeAll("notCorrect");
                idField.getStyleClass().add("correct");
            }
            else {
                idField.getStyleClass().removeAll("correct");
                idField.getStyleClass().add("notCorrect");
            }
        });

    }

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = currentFlight == null;

            if (currentFlight == null) {
                currentFlight = new Flight();
            }

            currentFlight.setId(Integer.valueOf((idProperty.get())));
            currentFlight.setCode(codeProperty.get());
            currentFlight.setAirplane(airplaneProperty.get());
            currentFlight.setStartOfUsingTheRunway(startOfUsingTheRunwayField.getDateTimeValue());
            currentFlight.setEndOfUsingTheRunway(endOfUsingTheRunwayField.getDateTimeValue());
            currentFlight.setFlightType(flightTypeProperty.get());
            currentFlight.setUser(userProperty.get());
            currentFlight.setGate(gateProperty.get());


            if (adding) {
                dao.addFlight(currentFlight);
            } else {
                dao.changeFlight(currentFlight);
            }
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }


    public void cancelFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean isFormValid() {
        return Validation.isValidString(codeProperty.get()) &&
                Validation.validateNumber(idProperty.get());
    }

}
