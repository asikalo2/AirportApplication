package ba.unsa.etf.rpr.projekat;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AirportDAOTest {

    AirportDAO dao;


    @BeforeEach
    void initDb() {
        File dbfile = new File("AirportDB.db");
        ClassLoader classLoader = getClass().getClassLoader();
        File srcfile = new File(classLoader.getResource("AirportDBtest.db").getFile());
        try {
            if (dbfile.exists()) {
                //System.out.println("Deleting file...");
                dbfile.delete();
            }
            Files.copy(srcfile.toPath(), dbfile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Cannot init db!");
        }
    }

/*
    @Test
    public void okButton (FxRobot robot) {
        robot.write("anonymous");
        robot.clickOn("#prijavaBtn");
        assertEquals("anonymous", prijavaBtn.getText());
    }*/

    @AfterEach
    void deleteDb() {
        File dbfile = new File("AirportDBtest.db");
        dbfile.delete();
        dao.removeInstance();
    }

    @Test
    void getFlightTypes() {
        dao = new AirportDAO();

        assertEquals(3, dao.getFlightTypes().size());
        // Za sada samo ispis da vidim da li radi

    }

    @Test
    void getUsers() {
        dao = new AirportDAO();
        assertEquals(1, dao.getUsers().size());
        // Za sada samo ispis da vidim da li radi
    }

    @Test
    void getPlaneById() {
        dao = new AirportDAO();
        Airplane plane = dao.getPlaneById(1);
        assertEquals(plane.getId(), 1);
        assertEquals(plane.getAirline().getName(), "Adria Airways");
    }

    @Test
    void getUserById() {
        dao = new AirportDAO();
        User user = dao.getUserById(1);
        assertEquals(user.getId(), 1);
        assertEquals(user.getRole().getName(), "Administrator");
    }

    @Test
    void getFlights() {
        dao = new AirportDAO();
        ObservableList<Flight> flights = dao.getFlights();
        assertEquals(flights.size(), 1);
    }

    @Test
    void getFlightsById() {
        dao = new AirportDAO();
        Flight flight = dao.getFlightById(1);
        assertEquals(flight.getId(), 1);
        assertEquals(flight.getCode(), "LH 876");
    }

    @Test
    void getRoles() {
        dao = new AirportDAO();
        ObservableList<Role> roles = dao.getRoles();
        assertEquals(roles.size(), 2);
    }

    @Test
    void getLuggages() {
        dao = new AirportDAO();
        ObservableList<Luggage> luggages = dao.getLuggages();
        assertEquals(luggages.size(), 3);
    }

    @Test
    void getAirplanes() {
        dao = new AirportDAO();
        ObservableList<Airplane> planes = dao.getAirplanes();
        assertEquals(planes.size(), 6);
    }

    @Test
    void getPassengers() {
        dao = new AirportDAO();
        ObservableList<Passenger> passengers = dao.getPassengers();
        assertEquals(passengers.size(), 4);
    }

    @Test
    public void getGate() {
        dao = new AirportDAO();
        ObservableList<Gate> gates = dao.getGates();
        assertEquals(gates.size(), 5);
    }

    @Test
    void addDeleteRole() {
        dao = new AirportDAO();
        Role role = new Role(3, "Security");
        dao.addRole(role);
        ObservableList<Role> roles = dao.getRoles();
        assertEquals(3, roles.size());
        dao.deleteRole(role);
        ObservableList<Role> roles1 = dao.getRoles();
        assertEquals(2, roles1.size());

    }

    @Test
    void addDeleteAirplane() {
        dao = new AirportDAO();
        try {
            Airplane plane = new Airplane(1000, new Airline(1000, "one", "two"), "man",
                    "type", 50);

            dao.addAirplane(plane);
            ObservableList<Airplane> planes = dao.getAirplanes();
            assertEquals(7, planes.size());
            dao.deleteAirplane(plane);
            ObservableList<Airplane> planes1 = dao.getAirplanes();
            assertEquals(6, planes1.size());
        } catch (IllegalNumberOfSeats ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void addDeleteLuggage() {
        dao = new AirportDAO();
        Luggage luggage = new Luggage(1000, dao.getPassengers().get(0));
        dao.addLuggage(luggage);
        ObservableList<Luggage> luggages = dao.getLuggages();
        assertEquals(4, luggages.size());
        dao.deleteLuggage(luggage);
        ObservableList<Luggage> luggages1 = dao.getLuggages();
        assertEquals(3, luggages1.size());
    }

    @Test
    void addDeleteFlightType() {
        dao = new AirportDAO();
        FlightType flightType = new FlightType(dao.highestIdFlightTypes() + 1, "test type");
        dao.addFlightType(flightType);
        ObservableList<FlightType> flightTypes = dao.getFlightTypes();
        assertEquals(4, flightTypes.size());
        dao.deleteFlightType(flightType);
        ObservableList<FlightType> flightTypes1 = dao.getFlightTypes();
        assertEquals(3, flightTypes1.size());

    }

    @Test
    void addDeleteAirline() {
        dao = new AirportDAO();
        Airline airline = new Airline(dao.highestIdAirline() + 1, "Lufthansa1", "LH C871");
        dao.addAirline(airline);
        ObservableList<Airline> airlines = dao.getAirlines();
        assertEquals(108, airlines.size());
        dao.removeInstance();
        dao = new AirportDAO();
        dao.deleteAirline(airline);
        ObservableList<Airline> airlines1 = dao.getAirlines();
        assertEquals(107, airlines1.size());
    }
}