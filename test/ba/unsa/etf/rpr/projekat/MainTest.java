package ba.unsa.etf.rpr.projekat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(ApplicationExtension.class)
public class MainTest {
    Stage theStage;
    AirportDAO dao;
    Controller controller;

    @Start
    public void start(Stage stage) throws Exception {
        File dbfile = new File("AirportDB.db");
        ClassLoader classLoader = getClass().getClassLoader();
        File srcfile = new File(classLoader.getResource("AirportDBtest.db").getFile());
        try {
            dbfile.delete();
            Files.copy(srcfile.toPath(), dbfile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Cannot init db!");
        }

        dao = new AirportDAO();

        // Ovo bi trebalo da iskopira fajl iz resources u test-resources, a ipak radi i sa mavenom
        File fxml = new File("resources/fxml/glavna.fxml");
        if (fxml.exists()) {
            File rsrc = new File("test-resources/fxml/glavna.fxml");
            if (rsrc.exists()) rsrc.delete();
            Files.copy(fxml.toPath(), rsrc.toPath());
        }
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/glavna.fxml"), bundle);
        stage.setTitle("Airport");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
        stage.toFront();

        theStage = stage;
    }

    @Test
    public void testRemoveAirline(FxRobot robot) {

        ObservableList<Airline> airlines = dao.getAirlines();
        robot.clickOn("#tableAirline");
        robot.clickOn("JP");

        robot.clickOn("#tbRemoveAirline");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        ObservableList<Airline> airlines1 = dao.getAirlines();
        assertEquals(airlines.size() - 1, airlines1.size());
    }

    @Test
    public void testEditAirline(FxRobot robot) throws InterruptedException {

        ObservableList<Airline> airlines = dao.getAirlines();
        robot.clickOn("#tableAirline");
        robot.clickOn("JP");

        robot.clickOn("#tbEditAirline");
        robot.lookup("#nameField").tryQuery().isPresent();

        robot.clickOn("#nameField");
        robot.write("abc");
        Thread.sleep(8000);
        robot.clickOn("#countryBox");
        robot.clickOn("#okButton");
        robot.clickOn("#okButton");
        Airline airline = dao.getAirlines().get(0);
        assertEquals(airline.getName(), "Adria Airwaysabc");
    }

    @Test
    public void testRemoveAirplane(FxRobot robot) {

        ObservableList<Airplane> airplanes = dao.getAirplanes();
        robot.clickOn("#airplanesTab");
        robot.clickOn("JP");

        robot.clickOn("#tbRemoveAirplane");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        ObservableList<Airplane> airplanes1 = dao.getAirplanes();
        assertEquals(airplanes.size() - 1, airplanes1.size());
    }

    @Test
    public void testEditAirplane(FxRobot robot) {

        ObservableList<Airplane> airplanes = dao.getAirplanes();
        robot.clickOn("#airplanesTab");
        robot.clickOn("JP");

        robot.clickOn("#tbEditAirplane");
        robot.lookup("#manufacturerField").tryQuery().isPresent();
        robot.clickOn("#manufacturerField");
        robot.write("abc");

        robot.clickOn("#okButton");
        Airplane airplane = dao.getAirplanes().get(0);
        assertEquals(airplane.getManufacturer(), "Airbusabc");
    }

    @Test
    public void testRemoveFlight(FxRobot robot) {

        ObservableList<Flight> flights = dao.getFlights();
        robot.clickOn("#flightsTab");
        robot.clickOn("JP");

        robot.clickOn("#tbRemoveFlight");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        ObservableList<Flight> flights1 = dao.getFlights();
        assertEquals(flights.size() - 1, flights1.size());
    }

    @Test
    public void testEditFlight(FxRobot robot) {

        ObservableList<Flight> flights = dao.getFlights();
        robot.clickOn("#flightsTab");
        robot.clickOn("JP");

        robot.clickOn("#tbEditFlight");
        robot.lookup("#codeField").tryQuery().isPresent();
        robot.clickOn("#codeField");
        robot.write("HZTZ");

        robot.clickOn("#okButton");
        Flight flight = dao.getFlights().get(0);
        assertEquals(flight.getCode(), "LH 876HZTZ");
    }

    @Test
    public void testRemoveFlightType(FxRobot robot) {

        ObservableList<FlightType> flightTypes = dao.getFlightTypes();
        robot.clickOn("#flightTypeTab");
        robot.clickOn("JP");

        robot.clickOn("#tbRemoveFlightType");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        ObservableList<FlightType> flightTypes1 = dao.getFlightTypes();
        assertEquals(flightTypes.size() - 1, flightTypes1.size());
    }

    @Test
    public void testEditFlightType(FxRobot robot) {

        ObservableList<FlightType> flightTypes = dao.getFlightTypes();
        robot.clickOn("#flightTypeTab");
        robot.clickOn("JP");

        robot.clickOn("#tbEditFlightType");
        robot.lookup("#nameFT").tryQuery().isPresent();
        robot.clickOn("#nameFT");
        robot.write("abc");

        robot.clickOn("#okButton");
        FlightType flightType = dao.getFlightTypes().get(0);
        assertEquals(flightType.getName(), "abc");
    }

    @Test
    public void testRemoveLuggage(FxRobot robot) {

        ObservableList<Luggage> luggages = dao.getLuggages();
        robot.clickOn("#luggageTab");
        robot.clickOn("JP");

        robot.clickOn("#tbRemoveLuggage");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        ObservableList<Luggage> luggages1 = dao.getLuggages();
        assertEquals(luggages.size() - 1, luggages1.size());
    }

        @Test
        public void testAddRole(FxRobot robot) {
        ObservableList<Role> roles = dao.getRoles();
        robot.clickOn("#rolesTab");
        robot.clickOn("#tbAddRole");
        robot.lookup("#idField").tryQuery().isPresent();
        robot.clickOn("#idField");
        robot.write("3");
        robot.clickOn("#nameField");
        robot.write("Regular");
        robot.clickOn("#okButton");
        Role role = dao.getRoles().get(2);
        assertEquals(role.getId(), 3);
    }



//    @Test
//    public void testEditLuggage(FxRobot robot) {
//
//        ObservableList<Luggage> luggages = dao.getLuggages();
//        robot.clickOn("#luggageTab");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditLuggage");
//        robot.lookup("#passenger").tryQuery().isPresent();
//        robot.clickOn("#passenger");
//   //   robot.clickOn();
//
//        robot.clickOn("#okButton");
//        Luggage luggage = dao.getLuggages().get(0);
//        assertEquals(luggage.getId(), "3");
//    }

    @Test
    public void testRemovePassenger(FxRobot robot) {

        ObservableList<Passenger> passengers = dao.getPassengers();
        robot.clickOn("#passengersTab");
        robot.clickOn("JP");

        robot.clickOn("#tbRemovePassenger");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        ObservableList<Passenger> passengers1 = dao.getPassengers();
        assertEquals(passengers.size() - 1, passengers1.size());
    }

    @Test
    public void testEditPassenger(FxRobot robot) {

        ObservableList<Passenger> passengers = dao.getPassengers();
        robot.clickOn("#passengersTab");
        robot.clickOn("JP");

        robot.clickOn("#tbEditPassenger");
        robot.lookup("#nameField").tryQuery().isPresent();
        robot.clickOn("#nameField");
        robot.write("abc");

        robot.clickOn("#okButton");
        Passenger passenger = dao.getPassengers().get(0);
        assertEquals(passenger.getName(), "Amila Sikaloabc");
    }

    @Test
    public void testRemoveRole(FxRobot robot) {

        ObservableList<Role> roles = dao.getRoles();
        robot.clickOn("#rolesTab");
        robot.clickOn("JP");

        robot.clickOn("#tbRemoveRole");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        ObservableList<Role> roles1 = dao.getRoles();
        assertEquals(roles.size() - 1, roles1.size());
    }

    @Test
    public void testEditRole(FxRobot robot) {

        ObservableList<Role> roles = dao.getRoles();
        robot.clickOn("#rolesTab");
        robot.clickOn("JP");

        robot.clickOn("#tbEditRole");
        robot.lookup("#nameField").tryQuery().isPresent();
        robot.clickOn("#nameField");
        robot.write("abc");

        robot.clickOn("#okButton");
        Role role = dao.getRoles().get(0);
        assertEquals(role.getName(), "Administratorabc");
    }

    @Test
    public void testRemoveUser(FxRobot robot) {

        ObservableList<User> users = dao.getUsers();
        robot.clickOn("#userTab");
        robot.clickOn("JP");

        robot.clickOn("#tbRemoveUser");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        ObservableList<User> users1 = dao.getUsers();
        assertEquals(users.size() - 1, users1.size());
    }

    @Test
    public void testEditUser(FxRobot robot) {

        ObservableList<User> users = dao.getUsers();
        robot.clickOn("#userTab");
        robot.clickOn("JP");

        robot.clickOn("#tbEditUser");
        robot.lookup("#nameField").tryQuery().isPresent();
        robot.clickOn("#nameField");
        robot.write("abc");

        robot.clickOn("#okButton");
        User user = dao.getUsers().get(0);
        assertEquals(user.getName(), "Amila Sikaloabc");
    }

    @Test
    public void testAddAirlineWithExistingID(FxRobot robot) throws InterruptedException {

        ObservableList<Airline> airlines = dao.getAirlines();
        robot.clickOn("#tableAirline");
        robot.clickOn("JP");

        robot.clickOn("#tbAddAirline");
        robot.lookup("#idField").tryQuery().isPresent();
        robot.clickOn("#idField");
        robot.write("2");
        robot.lookup("#nameField").tryQuery().isPresent();
        robot.clickOn("#nameField");
        robot.write("NewAirline");
        robot.lookup("#codeField").tryQuery().isPresent();
        robot.clickOn("#codeField");
        robot.write("HZTR");
        Thread.sleep(8000);
        robot.clickOn("#countryBox");
        robot.clickOn("#okButton");
        robot.clickOn("#okButton");

        ObservableList<Airline> airlines1 = dao.getAirlines();
        assertNotEquals(airlines.size(), airlines1.size()-1);
    }

    @Test
    public void testAddAirline(FxRobot robot) throws InterruptedException {

        ObservableList<Airline> airlines = dao.getAirlines();
        robot.clickOn("#tableAirline");
        robot.clickOn("JP");

        robot.clickOn("#tbAddAirline");
        robot.lookup("#idField").tryQuery().isPresent();
        robot.clickOn("#idField");
        robot.write("200");
        robot.lookup("#nameField").tryQuery().isPresent();
        robot.clickOn("#nameField");
        robot.write("NewAirline");
        robot.lookup("#codeField").tryQuery().isPresent();
        robot.clickOn("#codeField");
        robot.write("HZTR");
        Thread.sleep(6000);
        robot.clickOn("#countryBox");
        Thread.sleep(2000);
        robot.lookup("Algeria").tryQuery().isPresent();
        robot.clickOn("Algeria");
        robot.clickOn("#okButton");

        ObservableList<Airline> airlines1 = dao.getAirlines();
        assertEquals(airlines.size(), airlines1.size()-1);
    }

    @Test
    public void testAddUser(FxRobot robot) throws InterruptedException {
        ObservableList<User> users = dao.getUsers();
        robot.clickOn("#userTab");
        robot.clickOn("#tbAddUser");
        Thread.sleep(1000);
        robot.lookup("#idField").tryQuery().isPresent();
        robot.clickOn("#idField");
        robot.write("4");
        robot.lookup("#nameField").tryQuery().isPresent();
        robot.clickOn("#nameField");
        robot.write("NewName");
        robot.clickOn("#role");
        Thread.sleep(2000);
        robot.lookup("Operator").tryQuery().isPresent();
        robot.clickOn("Operator");

        robot.clickOn("#okButton");
        ObservableList<User> users1 = dao.getUsers();
        assertEquals(users.size(), users1.size()-1);
    }
    @Test
    public void testAddLuggage(FxRobot robot) throws InterruptedException {
        ObservableList<Luggage> luggages = dao.getLuggages();
        robot.clickOn("#luggageTab");
        robot.clickOn("#tbAddLuggage");
        robot.lookup("#idField").tryQuery().isPresent();
        robot.clickOn("#idField");
        robot.write("4");
        robot.clickOn("#optionsLuggage");
        Thread.sleep(2000);
        robot.clickOn("Hand Luggage");
        robot.lookup("#weightField").tryQuery().isPresent();
        robot.clickOn("#weightField");
        robot.write("4");
        robot.lookup("#payExtraField").tryQuery().isPresent();
        robot.clickOn("#payExtraField");
        robot.write("4");
        robot.clickOn("#passenger");
        robot.lookup("#Novi Noovic").tryQuery().isPresent();
        robot.clickOn("Novi Noovic");

        robot.clickOn("#okButton");
        ObservableList<Luggage> luggages1 = dao.getLuggages();
        assertEquals(luggages.size(), luggages1.size()-1);
    }

    @Test
    public void testAddLuggage2(FxRobot robot) throws InterruptedException {
        ObservableList<Luggage> luggages = dao.getLuggages();
        robot.clickOn("#luggageTab");
        robot.clickOn("#tbAddLuggage");
        robot.lookup("#idField").tryQuery().isPresent();
        robot.clickOn("#idField");
        robot.write("4");
        robot.clickOn("#optionsLuggage");
        Thread.sleep(2000);
        robot.clickOn("Additional Luggage");
        robot.lookup("#weightField").tryQuery().isPresent();
        robot.clickOn("#weightField");
        robot.write("4");
        robot.lookup("#payExtraField").tryQuery().isPresent();
        robot.clickOn("#payExtraField");
        robot.write("4");
        robot.clickOn("#passenger");
        robot.lookup("#Novi Noovic").tryQuery().isPresent();
        robot.clickOn("Novi Noovic");

        robot.clickOn("#addLuggageType");
        robot.lookup("METAL").tryQuery().isPresent();
        robot.clickOn("METAL");


        robot.clickOn("#okButton");
        ObservableList<Luggage> luggages1 = dao.getLuggages();
        assertEquals(luggages.size(), luggages1.size()-1);
    }
//    @Test
//    public void testGermanLanguage(FxRobot robot) {
//
//        ObservableList<Airline> airlines = dao.getAirlines();
//        robot.clickOn("#clickView");
//        robot.clickOn("#clickLanguages");
//        robot.clickOn("#clickGerman");
//      }


}