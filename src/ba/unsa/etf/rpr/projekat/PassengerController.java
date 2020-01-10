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
    public Label flightLabel;
    public TextField flightField;
    public Label qrCodeLabel;
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
    }

    private void fillForm() {
        idProperty.set(String.valueOf(currentPassenger.getId()));
        nameProperty.set(currentPassenger.getName());
        flightProperty.setValue(currentPassenger.getFlight());
        qrCodeProperty.set(currentPassenger.getQrCode());
        //qrCodeView.setImage(currentPassenger.getQrCode());
        if (currentPassenger.getCheckedIn() == "Yes") {
            checkInToggle.setSelected(true);
            checkInToggle.setText("Yes");
        }

        //System.out.println(currentPassenger.getQrCode().getWidth());
    }

    private void initializeDataBinding() {
        idField.textProperty().bindBidirectional(idProperty);
        nameField.textProperty().bindBidirectional(nameProperty);
        flight.valueProperty().bindBidirectional(flightProperty);
        //qrCodeField.textProperty().bindBidirectional(qrCodeProperty);
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

        flightField.textProperty().addListener((observableValue, s, n) -> {
            if (Validation.isStringTooLong(n)) {
                flightField.getStyleClass().removeAll("notCorrect");
                flightField.getStyleClass().add("correct");
            }
            else {
                flightField.getStyleClass().removeAll("correct");
                flightField.getStyleClass().add("notCorrect");
            }
        });
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

                    //qrCodeView.setImage(wr);
                    qrCodeProperty.set(wr);
                }
            }
            catch (WriterException ex) {
                ex.printStackTrace();
            }
        }
        else {
            checkInToggle.setText("No");
            //qrCodeView.setImage(null);
            qrCodeProperty.set(null);
        }

    }

    private boolean isFormValid() {
        return true;
    }

/* VISENITNO
 postanskiBrojField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (aBoolean && !t1) {
                    validator.setBroj(postanskiBrojField.getText());

                    Task<Boolean> task = new Task<Boolean>() {
                        @Override
                        protected Boolean call() throws Exception {
                            //System.out.println("calling");
                            return validator.provjeriPostanskiBroj(postanskiBrojField.getText());
                        }
                    };

                    task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent workerStateEvent) {
                            Boolean value = task.getValue();
                            //System.out.println(value);
                            if (value) {
                                //System.out.println("test 1");
                                postanskiBrojField.getStyleClass().removeAll("poljeNijeIspravno");
                                postanskiBrojField.getStyleClass().add("poljeIspravno");
                            } else {
                                postanskiBrojField.getStyleClass().removeAll("poljeIspravno");
                                postanskiBrojField.getStyleClass().add("poljeNijeIspravno");
                            }
                        }
                    });

                    new Thread(task).start();

                }
            }
        });
 */



}
