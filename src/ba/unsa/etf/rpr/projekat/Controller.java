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
        napuniTabeluVlasnici();
        napuniTabeluVozila();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new AirportDAO();
        napuniTabeluVlasnici();
        napuniTabeluVozila();
    }

    private void fillTableAirline() {
        ObservableList<Airline> listAirlines = dao.getAirlines();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("nazivProizvodjaca"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("model"));
        tableAirline.setItems(listAirlines);
    }

    private void fillTableAirline() {
        ObservableList<Airline> listAirlines = dao.getAirlines();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("nazivProizvodjaca"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("model"));
        tableAirline.setItems(listAirlines);
    }

    private void fillTableAirline() {
        ObservableList<Airline> listAirlines = dao.getAirlines();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("nazivProizvodjaca"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("model"));
        tableAirline.setItems(listAirlines);
    }

    private void fillTableAirline() {
        ObservableList<Airline> listAirlines = dao.getAirlines();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("nazivProizvodjaca"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("model"));
        tableAirline.setItems(listAirlines);
    }

    private void fillTableAirline() {
        ObservableList<Airline> listAirlines = dao.getAirlines();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("nazivProizvodjaca"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("model"));
        tableAirline.setItems(listAirlines);
    }

    private void fillTableAirline() {
        ObservableList<Airline> listAirlines = dao.getAirlines();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("nazivProizvodjaca"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("model"));
        tableAirline.setItems(listAirlines);
    }

    private void fillTableAirline() {
        ObservableList<User> listUsers = dao.getUsers();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("nazivProizvodjaca"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("model"));
        tableUsers.setItems(listUsers);
    }

    private void fillTableRolee() {
        ObservableList<Role> listRoles = dao.getRoles();

        idAirline.setCellValueFactory(new PropertyValueFactory("id"));
        nameAirline.setCellValueFactory(new PropertyValueFactory("nazivProizvodjaca"));
        codeAirline.setCellValueFactory(new PropertyValueFactory("model"));
        tableRole.setItems(listRoles);
    }

    public void addAirline(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AirlineController controller = new AirlineController(dao, null);
            loader.setController(controller);
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
    }

    public void removeAirplane(ActionEvent actionEvent) {
    }

    public void editAirplane(ActionEvent actionEvent) {
    }

    public void addFlight(ActionEvent actionEvent) {
    }

    public void removeFlight(ActionEvent actionEvent) {
    }

    public void editFlight(ActionEvent actionEvent) {
    }

    public void addFlightType(ActionEvent actionEvent) {
    }

    public void removeFlightType(ActionEvent actionEvent) {
    }

    public void editFlightType(ActionEvent actionEvent) {
    }

    public void addLuggage(ActionEvent actionEvent) {
    }

    public void removeLuggage(ActionEvent actionEvent) {
    }

    public void editLuggage(ActionEvent actionEvent) {
    }

    public void addPassenger(ActionEvent actionEvent) {
    }

    public void removePassenger(ActionEvent actionEvent) {
    }

    public void editPassenger(ActionEvent actionEvent) {
    }

    public void addUser(ActionEvent actionEvent) {
    }

    public void removeUser(ActionEvent actionEvent) {
    }

    public void editUser(ActionEvent actionEvent) {
    }

    public void addRole(ActionEvent actionEvent) {
    }

    public void removeRole(ActionEvent actionEvent) {
    }

    public void editRole(ActionEvent actionEvent) {
    }
}
