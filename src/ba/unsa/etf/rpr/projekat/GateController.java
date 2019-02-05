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
        private Gate currentgate = null;

        @FXML
        public Button cancelButton;
        @FXML
        public Button okButton;

        public GateController(AirportDAO dao, Gate gate) {
            this.dao = dao;
            this.currentgate = gate;
            idProperty = new SimpleStringProperty("");
            nameProperty = new SimpleStringProperty("");
        }

        @FXML
        public void initialize() {
            initializeDataBinding();
            if (currentgate != null) {
                fillForm();
            }
        }

        private void fillForm() {
            idProperty.set(String.valueOf(currentgate.getId()));
            nameProperty.set(currentgate.getName());
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
                boolean adding = currentgate == null;

                if (currentgate == null)
                    currentgate = new Gate();

                currentgate.setId(Integer.valueOf((idProperty.get())));
                currentgate.setName(nameProperty.get());

                if (adding) {
                    dao.addGate(currentgate);
                } else {
                    dao.changeGate(currentgate);
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




