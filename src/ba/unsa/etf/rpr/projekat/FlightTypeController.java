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
        if (currentFlightType != null) {
            fillForm();
        }
    }

    private void fillForm() {
        idProperty.set(String.valueOf(currentFlightType.getId()));
        nameProperty.set(currentFlightType.getName());
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
    }

    private void dodajListenere() {
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


    private boolean isFormValid() {
        return true;
    }

};




