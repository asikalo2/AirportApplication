package ba.unsa.etf.rpr.projekat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    public TableView tableAirline;
    public TableColumn idAirline;
    public TableColumn nameAirline;
    public TableColumn codeAirline;
    public TableView tabelaAirplanes;
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
    public TableView tableFlightType;
    public TableColumn idFT;
    public TableColumn nameFT;
    public TableView tableLuggage;
    public TableColumn idLuggage;
    public TableColumn passengerLuggage;
    public TableView tabelePassenger;
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

    public void setSQLite(ActionEvent actionEvent) {
        dao = new AirportDAO();
        fillTableAirlines();
        fillTableAirplanes();
        fillTableFlights();
        fillTableFlightTypes();
        fillTableLuggages();
        fillTablePassengers();
        fillTableRoles();
        fillTableUsers();
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new AirportDAO();
        fillTableAirlines();
        fillTableAirplanes();
        fillTableFlights();
        fillTableFlightTypes();
        fillTableLuggages();
        fillTablePassengers();
        fillTableRoles();
        fillTableUsers();
    }

    private void fillTableAirlines() {
        ObservableList<Airline> listAirlines = dao.getAirlines();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("nazivProizvodjaca"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("model"));
        tableAirline.setItems(listAirlines);
    }

    private void fillTableAirplanes() {
        ObservableList<Airplane> listAirplanes = dao.getAirplanes();

        idAirplane.setCellValueFactory(new PropertyValueFactory("idAirplane"));
        airlineAirplane.setCellValueFactory(new PropertyValueFactory("airlineAirplane"));
        manufacturerAirplane.setCellValueFactory(new PropertyValueFactory("manufacturerAirplane"));
        typeAirplane.setCellValueFactory(new PropertyValueFactory("typeAirplane"));
        numberOfSeatsAirplane.setCellValueFactory(new PropertyValueFactory("numberOfSeatsAirplane"));
        tabelaAirplanes.setItems(listAirplanes);
    }

    private void fillTableFlights() {
        ObservableList<Flight> listFlights = dao.getFlights();

        idFlight.setCellValueFactory(new PropertyValueFactory("idFlight"));
        airplaneFlight.setCellValueFactory(new PropertyValueFactory("airplaneFlight"));
        codeFlight.setCellValueFactory(new PropertyValueFactory("codeFlight"));
        startOfUsingTheRunwayFlight.setCellValueFactory(new PropertyValueFactory("startOfUsingTheRunwayFlight"));
        endOfUsingTheRunwayFlight.setCellValueFactory(new PropertyValueFactory("endOfUsingTheRunwayFlight"));
        userFlight.setCellValueFactory(new PropertyValueFactory("userFlight"));
        tableFlights.setItems(listFlights);
    }

    private void fillTableFlightTypes() {
        ObservableList<FlightType> listFlightType = dao.getFlightTypes();

        idFT.setCellValueFactory(new PropertyValueFactory("idFT"));
        nameFT.setCellValueFactory(new PropertyValueFactory("nameFT"));
        tableFlightType.setItems(listFlightType);
    }

    private void fillTableLuggages() {
        ObservableList<Luggage> listLuggage = dao.getLuggages();

        idLuggage.setCellValueFactory(new PropertyValueFactory("idLuggage"));
        passengerLuggage.setCellValueFactory(new PropertyValueFactory("passengerLuggage"));
        tableLuggage.setItems(listLuggage);
    }

    private void fillTablePassengers() {
        ObservableList<Passenger> listPassengers = dao.getPassengers();

        idPassenger.setCellValueFactory(new PropertyValueFactory("idPassenger"));
        namePassenger.setCellValueFactory(new PropertyValueFactory("namePassenger"));
        flightPassenger.setCellValueFactory(new PropertyValueFactory("flightPassenger"));
        qrPassenger.setCellValueFactory(new PropertyValueFactory("qrPassenger"));
        tabelePassenger.setItems(listPassengers);
    }

    private void fillTableUsers() {
        ObservableList<User> listUsers = dao.getUsers();

        idUser.setCellValueFactory(new PropertyValueFactory("idUser"));
        nameUser.setCellValueFactory(new PropertyValueFactory("nameUser"));
        roleUser.setCellValueFactory(new PropertyValueFactory("roleUser"));
        tableUsers.setItems(listUsers);
    }

    private void fillTableRoles() {
        ObservableList<Role> listRoles = dao.getRoles();

        idRole.setCellValueFactory(new PropertyValueFactory("idRole"));
        nameRole.setCellValueFactory(new PropertyValueFactory("nameRole"));
        tableRole.setItems(listRoles);
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
            stage.setOnCloseRequest(event-> {
                tableAirline.setItems(dao.getAirlines());
            });
            stage.setOnHiding(event-> {
                tableAirline.setItems(dao.getAirlines());
            });
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeAirline(ActionEvent actionEvent) {
    }

    public void editAirline(ActionEvent actionEvent) {
    }

    public void addAirplane(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AirplaneController airplaneController = new AirplaneController(dao, null);
            loader.setController(airplaneController);
            loader.setLocation(getClass().getResource("/fxml/airplane.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Airplane");
            stage.setScene(scene);
            stage.setOnCloseRequest(event-> {
                tabelaAirplanes.setItems(dao.getAirplanes());
            });
            stage.setOnHiding(event-> {
                tabelaAirplanes.setItems(dao.getAirplanes());
            });
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeAirplane(ActionEvent actionEvent) {
    }

    public void editAirplane(ActionEvent actionEvent) {
    }

    public void addFlightType(ActionEvent actionEvent) {
        try {
        FXMLLoader loader = new FXMLLoader();
        FlightTypeController flightTypeController = new FlightTypeController(dao, null);
        loader.setController(flightTypeController);
        loader.setLocation(getClass().getResource("/fxml/flightType.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Flight Type");
        stage.setScene(scene);
        stage.setOnCloseRequest(event-> {
            tableFlightType.setItems(dao.getFlightTypes());
        });
        stage.setOnHiding(event-> {
            tableFlightType.setItems(dao.getFlightTypes());
        });
        stage.show();
    }
    catch (IOException ex) {
        ex.printStackTrace();
    }
    }

    public void removeFlight(ActionEvent actionEvent) {
    }

    public void editFlight(ActionEvent actionEvent) {
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
            stage.setOnCloseRequest(event-> {
                tableFlights.setItems(dao.getFlights());
            });
            stage.setOnHiding(event-> {
                tableFlights.setItems(dao.getFlights());
            });
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeFlightType(ActionEvent actionEvent) {
    }

    public void editFlightType(ActionEvent actionEvent) {
    }

    public void addLuggage(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            LuggageController luggageController = new LuggageController(dao, null);
            loader.setController(luggageController);
            loader.setLocation(getClass().getResource("/fxml/luggage.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Luggage");
            stage.setScene(scene);
            stage.setOnCloseRequest(event-> {
                tableLuggage.setItems(dao.getLuggages());
            });
            stage.setOnHiding(event-> {
                tableLuggage.setItems(dao.getLuggages());
            });
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeLuggage(ActionEvent actionEvent) {
    }

    public void editLuggage(ActionEvent actionEvent) {
    }

    public void addPassenger(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            PassengerController passengerController = new PassengerController(dao, null);
            loader.setController(passengerController);
            loader.setLocation(getClass().getResource("/fxml/passenger.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Passenger");
            stage.setScene(scene);
            stage.setOnCloseRequest(event-> {
                tabelePassenger.setItems(dao.getPassengers());
            });
            stage.setOnHiding(event-> {
                tabelePassenger.setItems(dao.getPassengers());
            });
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removePassenger(ActionEvent actionEvent) {
    }

    public void editPassenger(ActionEvent actionEvent) {
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
            stage.setOnCloseRequest(event-> {
                tableUsers.setItems(dao.getUsers());
            });
            stage.setOnHiding(event-> {
                tableUsers.setItems(dao.getUsers());
            });
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUser(ActionEvent actionEvent) {
    }

    public void editUser(ActionEvent actionEvent) {
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
            stage.setOnCloseRequest(event-> {
                tableRole.setItems(dao.getRoles());
            });
            stage.setOnHiding(event-> {
                tableRole.setItems(dao.getRoles());
            });
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeRole(ActionEvent actionEvent) {
    }

    public void editRole(ActionEvent actionEvent) {
    }
}
