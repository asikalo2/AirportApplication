package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    @FXML
    public void initialize() {
        initializeDataBinding();
        addListeners();
        if (currentFlightType != null) {
            idField.setDisable(true);
            fillForm();
        }
    }

    public void fillForm() {
        idProperty.set(String.valueOf(currentFlightType.getId()));
        nameProperty.set(currentFlightType.getName());
    }

    public void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
    }

    private void addListeners() {
        nameField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.isValidString(n)) {
                nameField.getStyleClass().removeAll("notCorrect");
                nameField.getStyleClass().add("correct");
            }
            else {
                nameField.getStyleClass().removeAll("correct");
                nameField.getStyleClass().add("notCorrect");
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

    public void stopFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = currentFlightType == null;

            if (currentFlightType == null)
                currentFlightType = new FlightType();

            currentFlightType.setId(Integer.valueOf((idProperty.get())));
            currentFlightType.setName(nameProperty.get());

            if (adding) {
                dao.addFlightType(currentFlightType);
            } else {
                dao.changeFlightType(currentFlightType);
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


    public boolean isFormValid() {
        return true;
    }

};




