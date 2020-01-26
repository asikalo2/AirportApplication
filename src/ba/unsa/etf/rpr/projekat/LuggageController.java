package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class LuggageController {
    public Label idLabel;
    public TextField idField;
    public ComboBox<Passenger> passenger;
    public Label weightLabel;
    public TextField weightField;
    public Label payExtraLabel;
    public TextField payExtraField;
    public ComboBox<String> optionsLuggage;
    public SimpleStringProperty idProperty;
    public SimpleObjectProperty<Passenger> passengerProperty;
    public SimpleObjectProperty<String> optionsLuggageProperty;
    public SimpleStringProperty weightProperty;
    public SimpleStringProperty payExtraProperty;

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
        optionsLuggageProperty = new SimpleObjectProperty<>();
        weightProperty = new SimpleStringProperty("");
        payExtraProperty = new SimpleStringProperty("");
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

        weightLabel.setVisible(false);
        weightField.setVisible(false);
        payExtraLabel.setVisible(false);
        payExtraField.setVisible(false);
        addListeners();
        if (currentLuggage != null) {
            fillForm();
        }
    }

    public void fillForm() {
        idProperty.set(String.valueOf(currentLuggage.getId()));
        passengerProperty.setValue(currentLuggage.getPassenger());
        optionsLuggageProperty.setValue("Standard");
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        passenger.valueProperty().bindBidirectional(passengerProperty);
        optionsLuggage.valueProperty().bindBidirectional(optionsLuggageProperty);
        weightField.textProperty().bindBidirectional(weightProperty);
        payExtraField.textProperty().bindBidirectional(payExtraProperty);
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
        optionsLuggage.valueProperty().addListener((observableValue, s, t1) -> {
            //System.out.println(t1);
            if (isHandLuggage(t1) || isAdditionalLuggage(t1)) {
                weightLabel.setVisible(true);
                weightField.setVisible(true);
                payExtraLabel.setVisible(true);
                payExtraField.setVisible(true);
            }
            else if (!isHandLuggage(t1) && !isAdditionalLuggage(t1)) {
                weightLabel.setVisible(false);
                weightField.setVisible(false);
                payExtraLabel.setVisible(false);
                payExtraField.setVisible(false);
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

    public boolean isHandLuggage(String test) {
        System.out.println("testing...");
        return test.equals("Hand Luggage");
    }

    public boolean isAdditionalLuggage(String test) {
        System.out.println("testing...");
        return test.equals("Additional Luggage");
    }



}
