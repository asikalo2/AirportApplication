//package ba.unsa.etf.rpr.projekat;
//
//import javafx.application.Platform;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.DialogPane;
//import javafx.scene.input.KeyCode;
//import javafx.stage.Stage;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.testfx.api.FxRobot;
//import org.testfx.framework.junit5.ApplicationExtension;
//import org.testfx.framework.junit5.Start;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.ResourceBundle;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//
//@ExtendWith(ApplicationExtension.class)
//public class MainTest {
//    Stage theStage;
//    AirportDAO dao;
//    Controller controller;
//
//    @Start
//    public void start(Stage stage) throws Exception {
//        File dbfile = new File("AirportDB.db");
//        ClassLoader classLoader = getClass().getClassLoader();
//        File srcfile = new File(classLoader.getResource("AirportDBtest.db").getFile());
//        try {
//            dbfile.delete();
//            Files.copy(srcfile.toPath(), dbfile.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//            fail("Cannot init db!");
//        }
//
//        dao = new AirportDAO();
//
//        // Ovo bi trebalo da iskopira fajl iz resources u test-resources, a ipak radi i sa mavenom
//        File fxml = new File("resources/fxml/glavna.fxml");
//        if (fxml.exists()) {
//            File rsrc = new File("test-resources/fxml/glavna.fxml");
//            if (rsrc.exists()) rsrc.delete();
//            Files.copy(fxml.toPath(), rsrc.toPath());
//        }
//        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
//
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/glavna.fxml"), bundle);
//        stage.setTitle("Airport");
//        stage.setScene(new Scene(root, 800, 600));
//        stage.show();
//        stage.toFront();
//
//        theStage = stage;
//    }
//
//    @Test
//    public void testRemoveAirline(FxRobot robot) {
//
//        ObservableList<Airline> airlines = dao.getAirlines();
//        robot.clickOn("#tableAirline");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbRemoveAirline");
//        robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
//        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
//        robot.clickOn(okButton);
//
//        ObservableList<Airline> airlines1 = dao.getAirlines();
//        assertEquals(airlines.size() - 1, airlines1.size());
//    }
//
//    @Test
//    public void testEditAirline(FxRobot robot) {
//
//        ObservableList<Airline> airlines = dao.getAirlines();
//        robot.clickOn("#tableAirline");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditAirline");
//        robot.lookup("#nameField").tryQuery().isPresent();
//        robot.clickOn("#nameField");
//        robot.write("abc");
//
//        robot.clickOn("#okButton");
//        Airline airline = dao.getAirlines().get(0);
//        assertEquals(airline.getName(), "Adria Airwaysabc");
//    }
//
//    @Test
//    public void testRemoveAirplane(FxRobot robot) {
//
//        ObservableList<Airplane> airplanes = dao.getAirplanes();
//        robot.clickOn("#tableAirplanes");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbRemoveAirplane");
//        robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
//        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
//        robot.clickOn(okButton);
//
//        ObservableList<Airplane> airplanes1 = dao.getAirplanes();
//        assertEquals(airplanes.size() - 1, airplanes1.size());
//    }
//
//    @Test
//    public void testEditAirplane(FxRobot robot) {
//
//        ObservableList<Airplane> airplanes = dao.getAirplanes();
//        robot.clickOn("#tableAirplanes");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditAirplane");
//        robot.lookup("#manufacturerField").tryQuery().isPresent();
//        robot.clickOn("#manufacturerField");
//        robot.write("abc");
//
//        robot.clickOn("#okButton");
//        Airplane airplane = dao.getAirplanes().get(0);
//        assertEquals(airplane.getManufacturer(), "Boeingabc");
//    }
//
//    @Test
//    public void testRemoveFlight(FxRobot robot) {
//
//        ObservableList<Flight> flights = dao.getFlights();
//        robot.clickOn("#tableFlights");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbRemoveFlight");
//        robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
//        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
//        robot.clickOn(okButton);
//
//        ObservableList<Flight> flights1 = dao.getFlights();
//        assertEquals(flights.size() - 1, flights1.size());
//    }
//
//    @Test
//    public void testEditFlight(FxRobot robot) {
//
//        ObservableList<Flight> flights = dao.getFlights();
//        robot.clickOn("#tableFlight");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditFlight");
//        robot.lookup("#nameField").tryQuery().isPresent();
//        robot.clickOn("#nameField");
//        robot.write("abc");
//
//        robot.clickOn("#okButton");
//        Flight flight = dao.getFlights().get(0);
//        assertEquals(flight.getCode(), "Code");
//    }
//
//    @Test
//    public void testRemoveFlightType(FxRobot robot) {
//
//        ObservableList<FlightType> flightTypes = dao.getFlightTypes();
//        robot.clickOn("#tableFlightType");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbRemoveFlightType");
//        robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
//        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
//        robot.clickOn(okButton);
//
//        ObservableList<FlightType> flightTypes1 = dao.getFlightTypes();
//        assertEquals(flightTypes.size() - 1, flightTypes1.size());
//    }
//
//    @Test
//    public void testEditFlightType(FxRobot robot) {
//
//        ObservableList<FlightType> flightTypes = dao.getFlightTypes();
//        robot.clickOn("#tableFlightTypes");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditFlightTypes");
//        robot.lookup("#nameField").tryQuery().isPresent();
//        robot.clickOn("#nameField");
//        robot.write("abc");
//
//        robot.clickOn("#okButton");
//        FlightType flightType = dao.getFlightTypes().get(0);
//        assertEquals(flightType.getName(), "Adria Airwaysabc");
//    }
//
//    @Test
//    public void testRemoveGate(FxRobot robot) {
//
//        ObservableList<Gate> gates = dao.getGates();
//        robot.clickOn("#tableGate");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbRemoveGate");
//        robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
//        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
//        robot.clickOn(okButton);
//
//        ObservableList<Gate> gates1 = dao.getGates();
//        assertEquals(gates.size() - 1, gates1.size());
//    }
//
//    @Test
//    public void testEditGate(FxRobot robot) {
//
//        ObservableList<Gate> gates = dao.getGates();
//        robot.clickOn("#tableGate");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditGate");
//        robot.lookup("#nameField").tryQuery().isPresent();
//        robot.clickOn("#nameField");
//        robot.write("abc");
//
//        robot.clickOn("#okButton");
//        Gate gate = dao.getGates().get(0);
//        assertEquals(gate.getName(), "Adria Airwaysabc");
//    }
//
//    @Test
//    public void testRemoveLuggage(FxRobot robot) {
//
//        ObservableList<Luggage> luggages = dao.getLuggages();
//        robot.clickOn("#tableLuggage");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbRemoveLuggage");
//        robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
//        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
//        robot.clickOn(okButton);
//
//        ObservableList<Luggage> luggages1 = dao.getLuggages();
//        assertEquals(luggages.size() - 1, luggages1.size());
//    }
//
//    @Test
//    public void testEditLuggage(FxRobot robot) {
//
//        ObservableList<Luggage> luggages = dao.getLuggages();
//        robot.clickOn("#tableLuggage");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditLuggage");
//        robot.lookup("#nameField").tryQuery().isPresent();
//        robot.clickOn("#nameField");
//        robot.write("abc");
//
//        robot.clickOn("#okButton");
//        Luggage luggage = dao.getLuggages().get(0);
//        assertEquals(luggage.getPassenger(), "Adria Airwaysabc");
//    }
//
//    @Test
//    public void testRemovePassenger(FxRobot robot) {
//
//        ObservableList<Passenger> passengers = dao.getPassengers();
//        robot.clickOn("#tablePassenger");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbRemovePassenger");
//        robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
//        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
//        robot.clickOn(okButton);
//
//        ObservableList<Passenger> passengers1 = dao.getPassengers();
//        assertEquals(passengers.size() - 1, passengers1.size());
//    }
//
//    @Test
//    public void testEditPassenger(FxRobot robot) {
//
//        ObservableList<Airline> airlines = dao.getAirlines();
//        robot.clickOn("#tableAirline");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditAirline");
//        robot.lookup("#nameField").tryQuery().isPresent();
//        robot.clickOn("#nameField");
//        robot.write("abc");
//
//        robot.clickOn("#okButton");
//        Airline airline = dao.getAirlines().get(0);
//        assertEquals(airline.getName(), "Adria Airwaysabc");
//    }
//
//    @Test
//    public void testRemoveRole(FxRobot robot) {
//
//        ObservableList<Role> roles = dao.getRoles();
//        robot.clickOn("#tableRole");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbRemoveRole");
//        robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
//        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
//        robot.clickOn(okButton);
//
//        ObservableList<Role> roles1 = dao.getRoles();
//        assertEquals(roles.size() - 1, roles1.size());
//    }
//
//    @Test
//    public void testEditRole(FxRobot robot) {
//
//        ObservableList<Role> roles = dao.getRoles();
//        robot.clickOn("#tableRole");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditRole");
//        robot.lookup("#nameField").tryQuery().isPresent();
//        robot.clickOn("#nameField");
//        robot.write("abc");
//
//        robot.clickOn("#okButton");
//        Role role = dao.getRoles().get(0);
//        assertEquals(role.getName(), "Adria Airwaysabc");
//    }
//
//    @Test
//    public void testRemoveUser(FxRobot robot) {
//
//        ObservableList<User> users = dao.getUsers();
//        robot.clickOn("#tableUser");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbRemoveUser");
//        robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
//        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
//        robot.clickOn(okButton);
//
//        ObservableList<User> users1 = dao.getUsers();
//        assertEquals(users.size() - 1, users1.size());
//    }
//
//    @Test
//    public void testEditUser(FxRobot robot) {
//
//        ObservableList<User> users = dao.getUsers();
//        robot.clickOn("#tableUser");
//        robot.clickOn("JP");
//
//        robot.clickOn("#tbEditUser");
//        robot.lookup("#nameField").tryQuery().isPresent();
//        robot.clickOn("#nameField");
//        robot.write("abc");
//
//        robot.clickOn("#okButton");
//        User user = dao.getUsers().get(0);
//        assertEquals(user.getName(), "Adria Airwaysabc");
//    }
//
//
//}