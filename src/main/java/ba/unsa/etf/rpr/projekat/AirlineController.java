package ba.unsa.etf.rpr.projekat;

import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class AirlineController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public Label codeLabel;
    public TextField codeField;
    public Label countryLabel;
    public ProgressIndicator indicator;
    public ComboBox<String> countryBox;
    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;
    public SimpleStringProperty codeProperty;
    public SimpleObjectProperty<String> countryBoxProperty;
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
        countryBoxProperty = new SimpleObjectProperty<>();
        indicator = new ProgressIndicator();
    }

    @FXML
    public void initialize() {
        initializeDataBinding();
        addListeners();
        okButton.setDisable(true);
        if (currentAirline != null) {
            fillForm();
        }
        indicator.setVisible(true);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    ObservableList<String> countryNames = fillCountryBox();
                    countryBox.setItems(countryNames);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    public void run() {
                        indicator.setVisible(false);
                        okButton.setDisable(false);
                        System.out.println("Finished!");
                    }
                });

                return null;
            }
        };
        new Thread(task).start();

        /*try {
            ObservableList<String> countryNames = fillCountryBox();
            countryBox.setItems(countryNames);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }*/
    }

    public void fillForm() {
        idProperty.set(String.valueOf(currentAirline.getId()));
        nameProperty.set(currentAirline.getName());
        codeProperty.set(currentAirline.getCode());
    }

    private ObservableList<String> fillCountryBox() throws Exception {
        URL link = new URL("https://restcountries.eu/rest/v2/all");
        HttpURLConnection conn = (HttpURLConnection) link.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String inline = "";
        int response = conn.getResponseCode();
        System.out.println(response);
        if (response == 200) {
            ArrayList<String> names = new ArrayList<>();
            Scanner sc = new Scanner(link.openStream());
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }
            sc.close();
            JSONArray jsonarray = new JSONArray(inline);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                names.add(name);
                //System.out.println(name);
            }
            return FXCollections.observableArrayList(names);
        }
        return null;
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
        codeField.textProperty().bindBidirectional(codeProperty);
        countryBox.valueProperty().bindBidirectional(countryBoxProperty);
    }

    private void addListeners() {
        nameField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.isValidName(n)) {
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
            currentAirline.setCountry(countryBoxProperty.get());

            if (adding) {
                boolean exists = dao.doesAirlineExist(currentAirline.getId());
                if (exists)
                    return;
                dao.addAirline(currentAirline);
            }
        } else {
            dao.changeAirline(currentAirline);
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    

    private void setFlags() {
        /*if (Validation.isStringTooLong(nameField.getText())) {
            nameField.getStyleClass().removeAll("notCorrect");
            nameField.getStyleClass().add("correct");
        } else {
            nameField.getStyleClass().removeAll("correct");
            nameField.getStyleClass().add("notCorrect");
        }*/

        if (Validation.isValidString(codeField.getText())) {
            codeField.getStyleClass().removeAll("notCorrect");
            codeField.getStyleClass().add("correct");
        } else {
            codeField.getStyleClass().removeAll("correct");
            codeField.getStyleClass().add("notCorrect");
        }

        if (!Validation.isValidName(nameField.getText())) {
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
        return Validation.isValidName(nameProperty.get()) && Validation.isStringTooLong(codeProperty.get())
                && Validation.validateNumber(idProperty.get());
    }
}

