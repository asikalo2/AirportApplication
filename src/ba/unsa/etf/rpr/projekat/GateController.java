package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GateController {

        public Label idLabel;
        public TextField idField;
        public Label nameLabel;
        public TextField nameField;
        public SimpleStringProperty idProperty;
        public SimpleStringProperty nameProperty;
        private AirportDAO dao;
        private Gate currentGate = null;

        @FXML
        public Button cancelButton;
        @FXML
        public Button okButton;

        public GateController(AirportDAO dao, Gate gate) {
            this.dao = dao;
            this.currentGate = gate;
            idProperty = new SimpleStringProperty("");
            nameProperty = new SimpleStringProperty("");
        }

        @FXML
        public void initialize() {
            initializeDataBinding();
            addListeners();
            if (currentGate != null) {
                fillForm();
            }
        }

        public void fillForm() {
            idProperty.set(String.valueOf(currentGate.getId()));
            nameProperty.set(currentGate.getName());
        }

        private void initializeDataBinding() {
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
                boolean adding = currentGate == null;

                if (currentGate == null)
                    currentGate = new Gate();

                currentGate.setId(Integer.valueOf((idProperty.get())));
                currentGate.setName(nameProperty.get());

                if (adding) {
                    dao.addGate(currentGate);
                } else {
                    dao.changeGate(currentGate);
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




