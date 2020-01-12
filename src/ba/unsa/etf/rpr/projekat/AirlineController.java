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
        addListeners();
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

    private void addListeners() {
        nameField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.isValidString3(n)) {
                nameField.getStyleClass().removeAll("notCorrect");
                nameField.getStyleClass().add("correct");
            } else {
                nameField.getStyleClass().removeAll("correct");
                nameField.getStyleClass().add("notCorrect");
            }
        });

        idField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.validateNumber(n)) {
                idField.getStyleClass().removeAll("notCorrect");
                idField.getStyleClass().add("correct");
            } else {
                idField.getStyleClass().removeAll("correct");
                idField.getStyleClass().add("notCorrect");
            }
        });

        codeField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.isStringTooLong(n)) {
                codeField.getStyleClass().removeAll("notCorrect");
                codeField.getStyleClass().add("correct");
            } else {
                codeField.getStyleClass().removeAll("correct");
                codeField.getStyleClass().add("notCorrect");
            }
        });

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

    private void setFlags() {
        if (Validation.isStringTooLong(nameField.getText())) {
            nameField.getStyleClass().removeAll("notCorrect");
            nameField.getStyleClass().add("correct");
        } else {
            nameField.getStyleClass().removeAll("correct");
            nameField.getStyleClass().add("notCorrect");
        }

        if (Validation.isValidString(codeField.getText())) {
            codeField.getStyleClass().removeAll("notCorrect");
            codeField.getStyleClass().add("correct");
        } else {
            codeField.getStyleClass().removeAll("correct");
            codeField.getStyleClass().add("notCorrect");
        }

        if (Validation.isValidString(nameField.getText())) {
            nameField.getStyleClass().removeAll("notCorrect");
            nameField.getStyleClass().add("correct");
        } else {
            nameField.getStyleClass().removeAll("correct");
            nameField.getStyleClass().add("notCorrect");
        }

        if (Validation.isStringTooLong(codeField.getText())) {
            codeField.getStyleClass().removeAll("notCorrect");
            codeField.getStyleClass().add("correct");
        } else {
            codeField.getStyleClass().removeAll("correct");
            codeField.getStyleClass().add("notCorrect");
        }

    }


    public void cancelFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean isFormValid() {
        return Validation.isValidString(nameProperty.get()) && Validation.isValidString(codeProperty.get()) &&
                Validation.isStringTooLong(nameProperty.get()) &&
                Validation.isStringTooLong(codeProperty.get());
    }
}

