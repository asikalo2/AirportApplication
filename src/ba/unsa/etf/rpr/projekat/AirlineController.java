package ba.unsa.etf.rpr.projekat;

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

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
        codeField.textProperty().bindBidirectional(codeProperty);
    }

    private void dodajListenere() {}

    public void stopFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void potvrdiFormuBtn(ActionEvent actionEvent) {
    }

    public void prekiniFormuBtn(ActionEvent actionEvent) {
    }


  /*

    @FXML
    public void initialize() {
        initializeDataBinding();
        addListener();
        if(currentAirline != null){
            fillForm();
            }
        }

        private void addListener() {
            idField.textProperty().addListener((observableValue, s, n) -> {
                if (Validation.isStringValid(n)) {
                    idField.getStyleClass().removeAll("notCorrect");
                    idField.getStyleClass().add("correct");
                }
                else {
                    idField.getStyleClass().removeAll("correct");
                    idField.getStyleClass().add("notCorrect");
                }
            });

            nameField.textProperty().addListener((observableValue, s, n) -> {
                if (Validation.isStringValid(n)) {
                    nameField.getStyleClass().removeAll("notCorrect");
                    nameField.getStyleClass().add("correct");
                }
                else {
                    nameField.getStyleClass().removeAll("correct");
                    nameField.getStyleClass().add("notCorrect");
                }
            });

            codeField.textProperty().addListener((observableValue, s, n) -> {
                if (Validation.isStringValid(n)) {
                    codeField.getStyleClass().removeAll("notCorrect");
                    codeField.getStyleClass().add("correct");
                }
                else {
                    codeField.getStyleClass().removeAll("correct");
                    codeField.getStyleClass().add("notCorrect");
                }
            });
        }

        private void fillForm() {
            idProperty.set(currentAirline.getId());
            nameProperty.set(currentAirline.getName());
            codeProperty.set(currentAirline.getCode());
        }



        public void confirmFormBtn(ActionEvent actionEvent) {
         if (isFormValid()) {
             boolean adding = currentAirline == null;
             if (currentAirline == null) {
                 currentAirline = new Airline();
             }

             currentAirline.setId(idProperty.get());
             currentAirline.setName(nameProperty.get());
             currentAirline.setCode(codeProperty.get());

             if (adding)
                 dao.addAirline(currentAirline);
             else
                 dao.changeAirline(currentAirline);
             Stage stage = (Stage) okButton.getScene().getWindow();
             // do what you have to do
             stage.close();
         }
            else{
                    setFlags();
                }
            }



        private boolean isFormValid() {
            return Validation.isStringValid(idProperty.get()) &&
                    Validation.isStringValid(nameProperty.get()) &&
                    Validation.isStringValid(codeProperty.get());
        }

        private void setFlags() {
            if (Validation.isStringValid(idField.getText())) {
                idField.getStyleClass().removeAll("notCorrect");
                idField.getStyleClass().add("correct");
            }
            else {
                idField.getStyleClass().removeAll("correct");
                idField.getStyleClass().add("notCorrect");
            }

            if (Validation.isStringValid(nameField.getText())) {
                nameField.getStyleClass().removeAll("notCorrect");
                nameField.getStyleClass().add("correct");
            }
            else {
                nameField.getStyleClass().removeAll("correct");
                nameField.getStyleClass().add("notCorrect");
            }

            if (Validation.isStringValid(codeField.getText())) {
                codeField.getStyleClass().removeAll("notCorrect");
                codeField.getStyleClass().add("correct");
            }
            else {
                codeField.getStyleClass().removeAll("correct");
                codeField.getStyleClass().add("notCorrect");
            }
    }*/
    }

