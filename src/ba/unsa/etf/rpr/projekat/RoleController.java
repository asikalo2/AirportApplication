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
        if (currentRole != null) {
            fillForm();
        }
    }

    private void fillForm() {
        idProperty.set(String.valueOf(currentRole.getId()));
        nameProperty.set(currentRole.getName());
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
