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

public class PassengerController {
    public Label idLabel;
    public TextField idField;
    public Label nameLabel;
    public TextField nameField;
    public Label flightLabel;
    public TextField flightField;
    public Label qrCodeLabel;
    //public TextField qrCodeField;
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
                Flight newFlight = new Flight(0, string, null, null,null,null, null);

                return newFlight;
            }
        });

        if (currentPassenger != null) {
            fillForm();
        }
    }

    private void fillForm() {
        idProperty.set(String.valueOf(currentPassenger.getId()));
        nameProperty.set(currentPassenger.getName());
        flightProperty.setValue(currentPassenger.getFlight());
        qrCodeProperty.set(currentPassenger.getQrCode());
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
        flight.valueProperty().bindBidirectional(flightProperty);
        //qrCodeField.textProperty().bindBidirectional(qrCodeProperty);
    }

    private void dodajListenere() {
    }

    public void stopFormBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void okFormBtn(ActionEvent actionEvent) {
        if (isFormValid()) {
            boolean adding = currentPassenger == null;

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
            Stage stage = (Stage) okButton.getScene().getWindow();
            // do what you have to do
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

                    qrCodeView.setImage(wr);
                }
            }
            catch (WriterException ex) {
                ex.printStackTrace();
            }
        }
        else {
            checkInToggle.setText("No");
            qrCodeView.setImage(null);
        }

    }

    private boolean isFormValid() {
        return true;
    }



}
