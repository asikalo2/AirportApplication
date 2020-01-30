package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RoleController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;

    private AirportDAO dao;
    private Role currentRole = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public RoleController(AirportDAO dao, Role role) {
        this.dao = dao;
        this.currentRole = role;
        idProperty = new SimpleStringProperty("");
        nameProperty = new SimpleStringProperty("");
    }


    @FXML
    public void initialize() {
        initializeDataBinding();
        addListeners();
        if (currentRole != null) {
            fillForm();
        }
        else {
            System.out.println(currentRole);
        }
    }

    public void fillForm() {
        idProperty.set(String.valueOf(currentRole.getId()));
        nameProperty.set(currentRole.getName());
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

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = currentRole == null;

            if (currentRole == null)
                currentRole = new Role();

            currentRole.setId(Integer.valueOf((idProperty.get())));
            currentRole.setName(nameProperty.get());

            if (adding) {
                dao.addRole(currentRole);
            } else {
                dao.changeRole(currentRole);
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
        return Validation.isValidString(nameProperty.get()) &&
                Validation.validateNumber(idProperty.get());
    }

}
