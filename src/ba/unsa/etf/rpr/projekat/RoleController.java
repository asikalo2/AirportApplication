package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RoleController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;

    private AirportDAO dao;
    private Role currentRole = null;

    public RoleController(AirportDAO dao, Role role) {
        this.dao = dao;
        this.currentRole = role;
        idProperty = new SimpleStringProperty("");
        nameProperty = new SimpleStringProperty("");
    }
}
