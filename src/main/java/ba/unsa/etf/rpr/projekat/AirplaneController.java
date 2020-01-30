package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AirplaneController {
    public Label idLabel;
    public TextField idField;
    public TextField manufacturerField;
    public TextField typeField;
    public TextField numberOfSeatsField;
    public ComboBox<Airline> airline;
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


    @FXML
    public void initialize() {
        airline.setItems(FXCollections.observableArrayList(dao.getAirlines()));
        initializeDataBinding();

        airline.setConverter(new StringConverter<Airline>() {
            @Override
            public String toString(Airline airline) {
                if (airline == null)
                    return "";
                return airline.getName();
            }

            @Override
            public Airline fromString(String string) {
                Airline newAirline = new Airline(0, string, "");
                return newAirline;
            }
        });
        addListeners();
        if (currentAirplane != null) {
            fillForm();
        }
    }

    public void fillForm() {
        idProperty.set(String.valueOf(currentAirplane.getId()));
        airlineProperty.setValue(currentAirplane.getAirline());
        manufacturerProperty.set(currentAirplane.getManufacturer());
        typeProperty.set(currentAirplane.getType());
        numberOfSeatsProperty.set(String.valueOf(currentAirplane.getNumberOfSeats()));
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        airline.valueProperty().bindBidirectional(airlineProperty);
        manufacturerField.textProperty().bindBidirectional(manufacturerProperty);
        typeField.textProperty().bindBidirectional(typeProperty);
        numberOfSeatsField.textProperty().bindBidirectional(numberOfSeatsProperty);
    }

    private void addListeners() {
        manufacturerField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.isValidString(n)) {
                manufacturerField.getStyleClass().removeAll("notCorrect");
                manufacturerField.getStyleClass().add("correct");
            }
            else {
                manufacturerField.getStyleClass().removeAll("correct");
                manufacturerField.getStyleClass().add("notCorrect");
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

        typeField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.isStringTooLong(n)) {
                typeField.getStyleClass().removeAll("notCorrect");
                typeField.getStyleClass().add("correct");
            }
            else {
                typeField.getStyleClass().removeAll("correct");
                typeField.getStyleClass().add("notCorrect");
            }
        });

        numberOfSeatsField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.validateNumber(n) && Validation.validateNumberOfSeats(n)) {
                numberOfSeatsField.getStyleClass().removeAll("notCorrect");
                numberOfSeatsField.getStyleClass().add("correct");
            }
            else {
                numberOfSeatsField.getStyleClass().removeAll("correct");
                numberOfSeatsField.getStyleClass().add("notCorrect");
            }
        });
    }

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = currentAirplane == null;

            if (currentAirplane == null)
                currentAirplane = new Airplane();

            currentAirplane.setId(Integer.valueOf((idProperty.get())));
            currentAirplane.setAirline(airlineProperty.get());
            currentAirplane.setManufacturer(manufacturerProperty.get());
            currentAirplane.setType(typeProperty.get());
            currentAirplane.setNumberOfSeats(Integer.valueOf(numberOfSeatsProperty.get()));

            if (adding) {
                dao.addAirplane(currentAirplane);
            } else {
                dao.changeAirplane(currentAirplane);
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
        return Validation.isValidString(manufacturerProperty.get()) &&
                Validation.isStringTooLong(typeProperty.get()) &&
                Validation.validateNumber(numberOfSeatsProperty.get()) &&
                Validation.validateNumberOfSeats(numberOfSeatsProperty.get()) &&
                Validation.validateNumber(idProperty.get());
    }

}
