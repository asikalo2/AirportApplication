package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class LuggageController {
    public Label idLabel;
    public TextField idField;
    public ComboBox<Passenger> passenger;
    public SimpleStringProperty idProperty;
    public SimpleObjectProperty<Passenger> passengerProperty;

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
        passengerProperty = new SimpleObjectProperty<>();
    }

    @FXML
    public void initialize() {
        passenger.setItems(dao.getPassengers());
        initializeDataBinding();

        passenger.setConverter(new StringConverter<Passenger>() {
            @Override
            public String toString(Passenger passenger) {
                if (passenger == null)
                    return "";
                return passenger.getName();
            }

            @Override
            public Passenger fromString(String string) {
                Passenger newPassenger = null;

                    newPassenger = new Passenger(0, string, null, null);

                return newPassenger;
            }
        });

        addListeners();
        if (currentLuggage != null) {
            fillForm();
        }
    }

    public void fillForm() {
        idProperty.set(String.valueOf(currentLuggage.getId()));
        passengerProperty.setValue(currentLuggage.getPassenger());
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        passenger.valueProperty().bindBidirectional(passengerProperty);
    }

    private void addListeners() {


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

    public void stopFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = currentLuggage == null;

            if (currentLuggage == null)
                currentLuggage = new Luggage();

            currentLuggage.setId(Integer.valueOf((idProperty.get())));
            currentLuggage.setPassenger(passengerProperty.get());

            if (adding) {
                dao.addLuggage(currentLuggage);
            } else {
                dao.changeLuggage(currentLuggage);
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
