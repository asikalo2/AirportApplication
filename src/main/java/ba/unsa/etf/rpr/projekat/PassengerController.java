package ba.unsa.etf.rpr.projekat;

import com.google.zxing.WriterException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JRException;

import java.time.LocalDate;

public class PassengerController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public ComboBox<Flight> flight;
    public ImageView qrCodeView;

    public ToggleButton checkInToggle;

    public SimpleStringProperty idProperty;
    public SimpleStringProperty nameProperty;
    public SimpleObjectProperty<Flight> flightProperty;
    public SimpleObjectProperty<Image> qrCodeProperty;

    private AirportDAO dao;
    private Passenger currentPassenger = null;

    @FXML
    public Button cancelButton;
    @FXML
    public Button okButton;

    public PassengerController(AirportDAO dao, Passenger passenger) {
        this.dao = dao;
        this.currentPassenger = passenger;
        idProperty = new SimpleStringProperty("");
        nameProperty = new SimpleStringProperty("");
        flightProperty = new SimpleObjectProperty<>();
        qrCodeProperty = new SimpleObjectProperty<>();
    }



    @FXML
    public void initialize() {
        flight.setItems(dao.getFlights());
        initializeDataBinding();

        flight.setConverter(new StringConverter<Flight>() {
            @Override
            public String toString(Flight flight) {
                if (flight == null)
                    return "";
                return flight.getCode();
            }

            @Override
            public Flight fromString(String string) {
                Flight newFlight = new Flight(0, string, null, null,null,null, null, null);

                return newFlight;
            }
        });

        addListeners();
        if (currentPassenger != null) {
            fillForm();
        }
        else {
            idField.setDisable(true);
            idProperty.set(String.valueOf(dao.highestIdPassenger()+1));
        }
    }

    public void fillForm() {
        idProperty.set(String.valueOf(currentPassenger.getId()));
        nameProperty.set(currentPassenger.getName());
        flightProperty.setValue(currentPassenger.getFlight());
        qrCodeProperty.set(currentPassenger.getQrCode());
        if (checkInToggle != null && currentPassenger.getCheckedIn() == "Yes") {
            checkInToggle.setSelected(true);
            checkInToggle.setText("Yes");
        }
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
        flight.valueProperty().bindBidirectional(flightProperty);
        qrCodeView.imageProperty().bindBidirectional(qrCodeProperty);
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

    public boolean isAdding() {
        return currentPassenger == null;
    }

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = isAdding();

            if (currentPassenger == null)
                currentPassenger = new Passenger();

            currentPassenger.setId(Integer.valueOf((idProperty.get())));
            currentPassenger.setName(nameProperty.get());
            currentPassenger.setFlight(flightProperty.get());
            currentPassenger.setQrCode(qrCodeProperty.get());

            if (adding) {
                dao.addPassenger(currentPassenger);
            } else {
                dao.changePassenger(currentPassenger);
            }
            if (currentPassenger.getQrCode() != null)
                Utils.saveToFile(currentPassenger.getQrCode());
            Reports reports = new Reports();
            try {
                String boardingTime = currentPassenger.getFlight().getStartOfUsingTheRunway().
                        minusMinutes(30).toLocalTime().toString();
                String titleDate = LocalDate.now().toString();
                reports.showBoadingPass(currentPassenger.getName(), currentPassenger.getFlight().getAirlineName(),
                        currentPassenger.getFlightName(), titleDate, boardingTime);
            }
            catch (JRException ex) {
                ex.printStackTrace();
            }

            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }


    public void cancelFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void toggleCheckIn(ActionEvent actionEvent) {
        if (checkInToggle.isSelected()) {
            checkInToggle.setText("Yes");
            try {
                if (nameProperty.get() != null && flightProperty.get() != null) {
                    WritableImage wr = Utils.generateQrCode(nameProperty.get(),
                            flightProperty.get().getAirlineName() + "|" + flightProperty.get().getCode());

                    qrCodeProperty.set(wr);
                }
            }
            catch (WriterException ex) {
                ex.printStackTrace();
            }
        }
        else {
            checkInToggle.setText("No");
            qrCodeProperty.set(null);
        }

    }

    private boolean isFormValid() {
        return true;
    }

}
