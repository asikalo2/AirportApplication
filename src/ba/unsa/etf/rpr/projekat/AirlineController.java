package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AirlineController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public Label codeLabel;
    public TextField codeField;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;
    public SimpleStringProperty codeProperty;
    private AirportDAO dao;
    private Airline currentAirline = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public AirlineController(AirportDAO dao, Airline airline) {
        this.dao = dao;
        this.currentAirline = airline;
        idProperty = new SimpleStringProperty("");
        nameProperty = new SimpleStringProperty("");
        codeProperty = new SimpleStringProperty("");
    }

    @FXML
    public void initialize() {
        initializeDataBinding();
        if (currentAirline != null) {
            fillForm();
        }
    }

    private void fillForm() {
        idProperty.set(String.valueOf(currentAirline.getId()));
        nameProperty.set(currentAirline.getName());
        codeProperty.set(currentAirline.getCode());
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
        codeField.textProperty().bindBidirectional(codeProperty);
    }

    private void dodajListenere() {
    }

    public void stopFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = currentAirline == null;

            if (currentAirline == null)
                currentAirline = new Airline();

            currentAirline.setId(Integer.valueOf((idProperty.get())));
            currentAirline.setName(nameProperty.get());
            currentAirline.setCode(codeProperty.get());

            if (adding) {
                dao.addAirline(currentAirline);
            } else {
                dao.changeAirline(currentAirline);
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

