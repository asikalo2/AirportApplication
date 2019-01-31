package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public Label roleLabel;
    public TextField roleField;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;
    public SimpleStringProperty roleProperty;

    private AirportDAO dao;
    private User currentUser = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public UserController(AirportDAO dao, User user) {
        this.dao = dao;
        this.currentUser = user;
        idProperty = new SimpleStringProperty("");
        nameProperty = new SimpleStringProperty("");
        roleProperty = new SimpleStringProperty("");
    }
}
