package ba.unsa.etf.rpr.projekat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    public Tab flightsTab;
    public TextField airlineSearch;
    public TableColumn countryAirline;
    private ResourceBundle bundle;
    public TableView tableAirline;
    public TableColumn idAirline;
    public TableColumn nameAirline;
    public TableColumn codeAirline;
    public TableView tableAirplanes;
    public TableColumn idAirplane;
    public TableColumn airlineAirplane;
    public TableColumn manufacturerAirplane;
    public TableColumn typeAirplane;
    public TableColumn numberOfSeatsAirplane;
    public TableView tableFlights;
    public TableColumn idFlight;
    public TableColumn airplaneFlight;
    public TableColumn codeFlight;
    public TableColumn startOfUsingTheRunwayFlight;
    public TableColumn endOfUsingTheRunwayFlight;
    public TableColumn userFlight;
    public TableColumn flightGate;
    public TableView tableFlightType;
    public TableColumn idFT;
    public TableColumn nameFT;
    public TableView tableLuggage;
    public TableColumn idLuggage;
    public TableColumn passengerLuggage;
    public TableColumn luggageType;
    public TableView tablePassenger;
    public TableColumn idPassenger;
    public TableColumn namePassenger;
    public TableColumn flightPassenger;
    public TableColumn qrPassenger;
    public TableView tableUsers;
    public TableColumn idUser;
    public TableColumn nameUser;
    public TableColumn roleUser;
    public TableView tableRole;
    public TableColumn idRole;
    public TableColumn nameRole;
    private AirportDAO dao;

    public void exitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void setSQLite(ActionEvent actionEvent) {
        dao = new AirportDAO();
        fillTableAirlines();
        fillTableAirplanes();
        fillTableFlights();
        fillTableFlightTypes();
        fillTableLuggage();
        fillTablePassengers();
        fillTableRoles();
        fillTableUsers();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new AirportDAO();
        bundle = resourceBundle;
        fillTableAirlines();
        fillTableAirplanes();
        fillTableFlights();
        fillTableFlightTypes();
        fillTableLuggage();
        fillTablePassengers();
        fillTableRoles();
        fillTableUsers();

        flightsTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (flightsTab.isSelected()) {
                    ObservableList<Flight> listFlights = dao.getFlights();
                    for (Flight flight: listFlights) {
                        flight.informOfFlight1();
                        flight.informOfFlight2();
                    }
                }
            }
        });

        airlineSearch.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.equals("")) {
                fillTableAirlines();
                return;
            }
            List<Airline> listA = dao.getAirlines();
            List<Airline> listAirlines = listA.stream().filter(
                    airline -> {
                        return airline.getName().toUpperCase().indexOf(t1.toUpperCase()) != -1 ||
                                airline.getCode().toUpperCase().indexOf(t1.toUpperCase()) != -1 ||
                                airline.getCountry().toUpperCase().indexOf(t1.toUpperCase()) != -1;
                    }).collect(Collectors.toList());

            idAirline.setCellValueFactory(new PropertyValueFactory("id"));
            nameAirline.setCellValueFactory(new PropertyValueFactory("name"));
            codeAirline.setCellValueFactory(new PropertyValueFactory("code"));
            countryAirline.setCellValueFactory(new PropertyValueFactory("country"));
            tableAirline.setItems(FXCollections.observableArrayList(listAirlines));

        });
    }

    private void fillTableAirlines() {
        ArrayList<Airline> listAirlines = dao.getAirlines();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("name"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("code"));
        countryAirline.setCellValueFactory(new PropertyValueFactory("country"));
        tableAirline.setItems(FXCollections.observableArrayList(listAirlines));
    }

    private void fillTableAirplanes() {
        ObservableList<Airplane> listAirplanes = dao.getAirplanes();

        idAirplane.setCellValueFactory(new PropertyValueFactory("id"));
        airlineAirplane.setCellValueFactory(new PropertyValueFactory("airlineName"));
        manufacturerAirplane.setCellValueFactory(new PropertyValueFactory("manufacturer"));
        typeAirplane.setCellValueFactory(new PropertyValueFactory("type"));
        numberOfSeatsAirplane.setCellValueFactory(new PropertyValueFactory("numberOfSeats"));
        tableAirplanes.setItems(listAirplanes);
    }

    private void fillTableFlights() {
        ObservableList<Flight> listFlights = dao.getFlights();

        idFlight.setCellValueFactory(new PropertyValueFactory("id"));
        airplaneFlight.setCellValueFactory(new PropertyValueFactory("airlineName"));
        codeFlight.setCellValueFactory(new PropertyValueFactory("code"));
        startOfUsingTheRunwayFlight.setCellValueFactory(new PropertyValueFactory("startOfUsingTheRunway"));
        endOfUsingTheRunwayFlight.setCellValueFactory(new PropertyValueFactory("endOfUsingTheRunway"));
        userFlight.setCellValueFactory(new PropertyValueFactory("userName"));
        flightGate.setCellValueFactory(new PropertyValueFactory("gateName"));
        tableFlights.setItems(listFlights);
    }

    private void fillTableFlightTypes() {
        ObservableList<FlightType> listFlightType = dao.getFlightTypes();

        idFT.setCellValueFactory(new PropertyValueFactory("id"));
        nameFT.setCellValueFactory(new PropertyValueFactory("name"));
        tableFlightType.setItems(listFlightType);
    }

    private void fillTableLuggage() {
        ObservableList<Luggage> listLuggage = dao.getLuggages();

        idLuggage.setCellValueFactory(new PropertyValueFactory("id"));
        passengerLuggage.setCellValueFactory(new PropertyValueFactory("passengerName"));
        luggageType.setCellValueFactory(new PropertyValueFactory("luggageType"));
        tableLuggage.setItems(listLuggage);
    }

    private void fillTablePassengers() {
        ObservableList<Passenger> listPassengers = dao.getPassengers();

        idPassenger.setCellValueFactory(new PropertyValueFactory("id"));
        namePassenger.setCellValueFactory(new PropertyValueFactory("name"));
        flightPassenger.setCellValueFactory(new PropertyValueFactory("flightName"));
        qrPassenger.setCellValueFactory(new PropertyValueFactory("checkedIn"));
        tablePassenger.setItems(listPassengers);
    }

    private void fillTableUsers() {
        ObservableList<User> listUsers = dao.getUsers();

        idUser.setCellValueFactory(new PropertyValueFactory("id"));
        nameUser.setCellValueFactory(new PropertyValueFactory("name"));
        roleUser.setCellValueFactory(new PropertyValueFactory("roleName"));
        tableUsers.setItems(listUsers);
    }

    private void fillTableRoles() {
        ArrayList<Role> listRoles = dao.getRoles();

        idRole.setCellValueFactory(new PropertyValueFactory("id"));
        nameRole.setCellValueFactory(new PropertyValueFactory("name"));
        tableRole.setItems(FXCollections.observableArrayList(listRoles));
    }

    public void addAirline(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AirlineController airlineController = new AirlineController(dao, null);
            loader.setController(airlineController);
            loader.setLocation(getClass().getResource("/fxml/airline.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Airline");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> {
                tableAirline.setItems(FXCollections.observableArrayList(dao.getAirlines()));
            });
            stage.setOnHiding(event -> {
                tableAirline.setItems(FXCollections.observableArrayList(dao.getAirlines()));
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addFlightType(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            FlightTypeController flightTypeController = new FlightTypeController(dao, null);
            loader.setController(flightTypeController);
            loader.setLocation(getClass().getResource("/fxml/flightType.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Flight type");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tableFlightType.setItems(dao.getFlightTypes());
            });
            stage.setOnHiding(event -> {
                tableFlightType.setItems(dao.getFlightTypes());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void removeAirline(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting Airline");
        alert.setContentText("Do you want to delete an airline?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tableAirline.getSelectionModel().getSelectedItems().size() == 0) {
                Alert alertNew = new Alert(Alert.AlertType.ERROR);
                alertNew.setHeaderText("Error!");
                alertNew.setContentText("Deleting is not possible!");

                alertNew.showAndWait();
                return;
            }
            Airline airline = (Airline) tableAirline.getSelectionModel().getSelectedItem();

            try {
                dao.deleteAirline(airline);
            } catch (Exception ex) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText("Deleting airline");
                alertError.setContentText(ex.getMessage());
                alertError.showAndWait();
                return;
            }
        }
        tableAirline.setItems(FXCollections.observableArrayList(dao.getAirlines()));
    }

    public void editAirline(ActionEvent actionEvent) {
        if (tableAirline.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        Airline airline = (Airline) tableAirline.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader();
            AirlineController airlineController = new AirlineController(dao, airline);
            loader.setController(airlineController);
            loader.setLocation(getClass().getResource("/fxml/airline.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Airline");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tableAirline.setItems(FXCollections.observableArrayList(dao.getAirlines()));
            });
            stage.setOnHiding(event -> {
                tableAirline.setItems(FXCollections.observableArrayList(dao.getAirlines()));
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void editFlightType(ActionEvent actionEvent) {
        if (tableFlightType.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        FlightType flightType = (FlightType) tableFlightType.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader();
            FlightTypeController flightTypeController = new FlightTypeController(dao, flightType);
            loader.setController(flightTypeController);
            loader.setLocation(getClass().getResource("/fxml/flightType.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Flight type");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tableFlightType.setItems(dao.getFlightTypes());
            });
            stage.setOnHiding(event -> {
                tableFlightType.setItems(dao.getFlightTypes());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addAirplane(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AirplaneController airplaneController = new AirplaneController(dao, null);
            loader.setController(airplaneController);
            loader.setLocation(getClass().getResource("/fxml/airplane.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Airplane");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tableAirplanes.setItems(dao.getAirplanes());
            });
            stage.setOnHiding(event -> {
                tableAirplanes.setItems(dao.getAirplanes());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeAirplane(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting Airplane");
        alert.setContentText("Do you want to delete an airplane?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tableAirplanes.getSelectionModel().getSelectedItems().size() == 0) {
                Alert alertNew = new Alert(Alert.AlertType.ERROR);
                alertNew.setHeaderText("Error!");
                alertNew.setContentText("Deleting is not possible!");

                alertNew.showAndWait();
                return;
            }
            Airplane airplane = (Airplane) tableAirplanes.getSelectionModel().getSelectedItem();

            try {
                dao.deleteAirplane(airplane);
            } catch (Exception ex) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText("Deleting airplane");
                alertError.setContentText(ex.getMessage());
                alertError.showAndWait();
                return;
            }
        }
        tableAirplanes.setItems(dao.getAirplanes());
    }

    public void editAirplane(ActionEvent actionEvent) {
        if (tableAirplanes.getSelectionModel().getSelectedItems() == null) {
            return;
        }
        Airplane airplane = (Airplane) tableAirplanes.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader();
            AirplaneController airplaneController = new AirplaneController(dao, airplane);
            loader.setController(airplaneController);
            loader.setLocation(getClass().getResource("/fxml/airplane.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Airplane");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tableAirplanes.setItems(dao.getAirplanes());
            });
            stage.setOnHiding(event -> {
                tableAirplanes.setItems(dao.getAirplanes());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeFlight(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting Flight");
        alert.setContentText("Do you want to delete a flight?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tableFlights.getSelectionModel().getSelectedItems().size() == 0) {
                Alert alertNew = new Alert(Alert.AlertType.ERROR);
                alertNew.setHeaderText("Error!");
                alertNew.setContentText("Deleting is not possible!");

                alertNew.showAndWait();
                return;
            }
            Flight flight = (Flight) tableFlights.getSelectionModel().getSelectedItem();

            try {
                dao.deleteFlight(flight);
            } catch (Exception ex) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText("Deleting airplane");
                alertError.setContentText(ex.getMessage());
                alertError.showAndWait();
                return;
            }
        }
        tableFlights.setItems(dao.getFlights());
    }

    public void editFlight(ActionEvent actionEvent) {
        if (tableFlights.getSelectionModel().getSelectedItems() == null) {
            return;
        }
        Flight flight = (Flight) tableFlights.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader();
            FlightController flightController = new FlightController(dao, flight);
            loader.setController(flightController);
            loader.setLocation(getClass().getResource("/fxml/flight.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Flight");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> {
                tableFlights.setItems(dao.getFlights());
            });
            stage.setOnHiding(event -> {
                tableFlights.setItems(dao.getFlights());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addFlight(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            FlightController flightController = new FlightController(dao, null);
            loader.setController(flightController);
            loader.setLocation(getClass().getResource("/fxml/flight.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Flight");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> {
                tableFlights.setItems(dao.getFlights());
            });
            stage.setOnHiding(event -> {
                tableFlights.setItems(dao.getFlights());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeFlightType(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting Flight Type");
        alert.setContentText("Do you want to delete a flight type?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tableFlightType.getSelectionModel().getSelectedItems().size() == 0) {
                Alert alertNew = new Alert(Alert.AlertType.ERROR);
                alertNew.setHeaderText("Error!");
                alertNew.setContentText("Deleting is not possible!");

                alertNew.showAndWait();
                return;
            }
            FlightType flightType = (FlightType) tableFlightType.getSelectionModel().getSelectedItem();

            try {
                dao.deleteFlightType(flightType);
            } catch (Exception ex) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText("Deleting flight type.");
                alertError.setContentText(ex.getMessage());
                alertError.showAndWait();
                return;
            }
        }
        tableFlightType.setItems(dao.getFlightTypes());
    }


    public void addLuggage(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            LuggageController luggageController = new LuggageController(dao, null);
            loader.setController(luggageController);
            loader.setLocation(getClass().getResource("/fxml/luggage.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Luggage");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tableLuggage.setItems(dao.getLuggages());
            });
            stage.setOnHiding(event -> {
                tableLuggage.setItems(dao.getLuggages());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeLuggage(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting luggage.");
        alert.setContentText("Do you want to delete a luggage?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tableLuggage.getSelectionModel().getSelectedItems().size() == 0) {
                Alert alertNew = new Alert(Alert.AlertType.ERROR);
                alertNew.setHeaderText("Error!");
                alertNew.setContentText("Deleting is not possible!");

                alertNew.showAndWait();
                return;
            }
            Luggage luggage = (Luggage) tableLuggage.getSelectionModel().getSelectedItem();

            try {
                dao.deleteLuggage(luggage);
            } catch (Exception ex) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText("Deleting luggage.");
                alertError.setContentText(ex.getMessage());
                alertError.showAndWait();
                return;
            }
        }
        tableLuggage.setItems(dao.getLuggages());
    }

    public void editLuggage(ActionEvent actionEvent) {
        if (tableLuggage.getSelectionModel().getSelectedItems() == null) {
            return;
        }
        Luggage luggage = (Luggage) tableLuggage.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader();
            LuggageController luggageController = new LuggageController(dao, luggage);
            loader.setController(luggageController);
            loader.setLocation(getClass().getResource("/fxml/luggage.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Luggage");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tableLuggage.setItems(dao.getLuggages());
            });
            stage.setOnHiding(event -> {
                tableLuggage.setItems(dao.getLuggages());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addPassenger(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            PassengerController passengerController = new PassengerController(dao, null);
            loader.setController(passengerController);
            loader.setLocation(getClass().getResource("/fxml/passenger.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Passenger");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tablePassenger.setItems(dao.getPassengers());
            });
            stage.setOnHiding(event -> {
                tablePassenger.setItems(dao.getPassengers());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removePassenger(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting passenger.");
        alert.setContentText("Do you want to delete a passenger?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tablePassenger.getSelectionModel().getSelectedItems().size() == 0) {
                Alert alertNew = new Alert(Alert.AlertType.ERROR);
                alertNew.setHeaderText("Error!");
                alertNew.setContentText("Deleting is not possible!");

                alertNew.showAndWait();
                return;
            }
            Passenger passenger = (Passenger) tablePassenger.getSelectionModel().getSelectedItem();

            try {
                dao.deletePassenger(passenger);
            } catch (Exception ex) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText("Deleting passenger.");
                alertError.setContentText(ex.getMessage());
                alertError.showAndWait();
                return;
            }
        }
        tablePassenger.setItems(dao.getPassengers());
    }

    public void editPassenger(ActionEvent actionEvent) {
        if (tablePassenger.getSelectionModel().getSelectedItems() == null) {
            return;
        }
        Passenger passenger = (Passenger) tablePassenger.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader();
            PassengerController passengerController = new PassengerController(dao, passenger);
            loader.setController(passengerController);
            loader.setLocation(getClass().getResource("/fxml/passenger.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Passenger");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tablePassenger.setItems(dao.getPassengers());
            });
            stage.setOnHiding(event -> {
                tablePassenger.setItems(dao.getPassengers());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addUser(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            UserController userController = new UserController(dao, null);
            loader.setController(userController);
            loader.setLocation(getClass().getResource("/fxml/user.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("User");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> {
                tableUsers.setItems(dao.getUsers());
            });
            stage.setOnHiding(event -> {
                tableUsers.setItems(dao.getUsers());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUser(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting user.");
        alert.setContentText("Do you want to delete an user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tableUsers.getSelectionModel().getSelectedItems().size() == 0) {
                Alert alertNew = new Alert(Alert.AlertType.ERROR);
                alertNew.setHeaderText("Error!");
                alertNew.setContentText("Deleting is not possible!");

                alertNew.showAndWait();
                return;
            }
            User user = (User) tableUsers.getSelectionModel().getSelectedItem();

            try {
                dao.deleteUser(user);
            } catch (Exception ex) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText("Deleting user.");
                alertError.setContentText(ex.getMessage());
                alertError.showAndWait();
                return;
            }
        }
        tableUsers.setItems(dao.getUsers());
    }

    public void editUser(ActionEvent actionEvent) {
        if (tableUsers.getSelectionModel().getSelectedItems() == null) {
            return;
        }
        User user = (User) tableUsers.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader();
            UserController userController = new UserController(dao, user);
            loader.setController(userController);
            loader.setLocation(getClass().getResource("/fxml/user.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("User");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tableUsers.setItems(dao.getUsers());
            });
            stage.setOnHiding(event -> {
                tableUsers.setItems(dao.getUsers());
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addRole(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            RoleController roleController = new RoleController(dao, null);
            loader.setController(roleController);
            loader.setLocation(getClass().getResource("/fxml/role.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Role");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> {
                tableRole.setItems(FXCollections.observableArrayList(dao.getRoles()));
            });
            stage.setOnHiding(event -> {
                tableRole.setItems(FXCollections.observableArrayList(dao.getRoles()));
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeRole(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting role.");
        alert.setContentText("Do you want to delete a role?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tableRole.getSelectionModel().getSelectedItems().size() == 0) {
                Alert alertNew = new Alert(Alert.AlertType.ERROR);
                alertNew.setHeaderText("Error!");
                alertNew.setContentText("Deleting is not possible!");

                alertNew.showAndWait();
                return;
            }
            Role role = (Role) tableRole.getSelectionModel().getSelectedItem();

            try {
                dao.deleteRole(role);
            } catch (Exception ex) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText("Deleting role.");
                alertError.setContentText(ex.getMessage());
                alertError.showAndWait();
                return;
            }
        }
        tableRole.setItems(FXCollections.observableArrayList(dao.getRoles()));
    }

    public void editRole(ActionEvent actionEvent) {

        Role role = (Role) tableRole.getSelectionModel().getSelectedItem();
        if (role == null) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            RoleController roleController = new RoleController(dao, role);
            loader.setController(roleController);
            loader.setLocation(getClass().getResource("/fxml/role.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Role");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                tableRole.setItems(FXCollections.observableArrayList(dao.getRoles()));
            });
            stage.setOnHiding(event -> {
                tableRole.setItems(FXCollections.observableArrayList(dao.getRoles()));
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handleKeyInput(KeyEvent keyEvent) {
    }

    public void viewReportAction(ActionEvent actionEvent) {
        List<Flight> flightsList = dao.getFlights();
        FlightsReport report = new FlightsReport();
        try {
            report.showReport(flightsList);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void viewReportActionUser(ActionEvent actionEvent) {
        UsersReport usersReport = new UsersReport();
        ArrayList<User> users = new ArrayList<User>(dao.getUsers());
        try {
            usersReport.showReport(users);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public void viewReportActionPassenger(ActionEvent actionEvent) {
        List<Passenger> passengerList = dao.getPassengers();
        //System.out.println(passengerList);
        PassengerListReport report = new PassengerListReport();
        try {
            report.showReport(passengerList);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void changeToBosnian(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("bs", "BA"));
        try {
            Main.loadView(Locale.getDefault());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToEnglish(ActionEvent actionEvent) {
        //System.out.println("to en_US");
        Locale.setDefault(new Locale("en", "US"));
        try {
            Main.loadView(Locale.getDefault());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToGerman(ActionEvent actionEvent) {
        //System.out.println("to de");
        Locale.setDefault(new Locale("de", "DE"));
        try {
            Main.loadView(Locale.getDefault());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void changeToFrench(ActionEvent actionEvent) {
        //System.out.println("to fr");
        Locale.setDefault(new Locale("fr", "FR"));
        try {
            Main.loadView(Locale.getDefault());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToArabian(ActionEvent actionEvent) {
        //System.out.println("to ar");
        Locale.setDefault(new Locale("ar", "AR"));
        try {
            Main.loadView(Locale.getDefault());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToChinese(ActionEvent actionEvent) {
        //System.out.println("to zh");
        Locale.setDefault(new Locale("zh", "ZH"));
        try {
            Main.loadView(Locale.getDefault());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToRussian(ActionEvent actionEvent) {
        //System.out.println("to ru");
        Locale.setDefault(new Locale("ru", "RU"));
        try {
            Main.loadView(Locale.getDefault());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeToSpanish(ActionEvent actionEvent) {
        //System.out.println("to es");
        Locale.setDefault(new Locale("es", "ES"));
        try {
            Main.loadView(Locale.getDefault());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void passengerListReport(ActionEvent actionEvent) {
        Flight flight = (Flight) tableFlights.getSelectionModel().getSelectedItem();
        if (flight == null) {
            return;
        }

        List<Passenger> passengerList = dao.getPassengersByFlightId(flight.getId());
        //System.out.println(passengerList);
        PassengerListReport report = new PassengerListReport();
        try {
            report.showReport(passengerList, flight);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    public Map<Airline, List<Airplane>> searchAirplane() {
        Map<Airline, List<Airplane>> map = new HashMap<>();
        int j = 0;
        int i = 0;
        List<Airplane> airplanes = dao.getAirplanes();
        List<Airline> airlines = dao.getAirlines();
        List<Airplane> list = new ArrayList<>();
        for (i = 0; i < airlines.size(); i++) {
            map.put(airlines.get(i), list);
            for (j = 0; j < map.size(); j++) {
                if (map.containsKey(airplanes.get(j).getAirline())) {
                    map.get(i).add(airplanes.get(j));
                }
            }
        }
        return map;
    }
}