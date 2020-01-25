package ba.unsa.etf.rpr.projekat;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDateTime;

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

    }

    @Test
    public void getAirlines() {
        dao = new AirportDAO();
        assertEquals(107, dao.getAirlines().size());

    }

    @Test
    void getUsers() {
        dao = new AirportDAO();
        assertEquals(1, dao.getUsers().size());
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

    @Test
    void addDeleteUser() {
        dao = new AirportDAO();
        User user = new User(dao.highestIdUser() + 1, "Lufthansa1", new Role(6, "NewHehe"));
        dao.addUser(user);
        ObservableList<User> users = dao.getUsers();
        assertEquals(1, users.size());
        dao.removeInstance();
        dao = new AirportDAO();
        dao.deleteUser(user);
        ObservableList<User> users1 = dao.getUsers();
        assertEquals(1, users1.size());
    }

    @Test
    void addDeleteGate() {
        dao = new AirportDAO();
        Gate gate = new Gate(7,"Name");
        dao.addGate(gate);
        ObservableList<Gate> gates = dao.getGates();
        assertEquals(6, gates.size());
        dao.removeInstance();
        dao = new AirportDAO();
        dao.deleteGate(gate);
        ObservableList<Gate> gates1 = dao.getGates();
        assertEquals(5, gates1.size());
    }

    @Test
    void addDeleteFlight() throws IllegalNumberOfSeats {
        dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(dao.highestIdFlight() + 1, "7657", dao.getPlaneById(2),
                             sour, eur, dao.getFlightTypes().get(0), dao.getUserById(1),
                                dao.getGates().get(0));
        dao.addFlight(flight);
        ObservableList<Flight> flights = dao.getFlights();
        assertEquals(2, flights.size());
        dao.removeInstance();
        dao = new AirportDAO();
        dao.deleteFlight(flight);
        ObservableList<Flight> flights1 = dao.getFlights();
        assertEquals(1, flights1.size());
    }

    @Test
    public void testHighestIdAirplane(){
        dao = new AirportDAO();
        assertEquals(dao.highestIdAirplane(), 7);
    }

    @Test
    public void testHighestIdFlightType(){
        dao = new AirportDAO();
        assertEquals(dao.highestIdFlightTypes(), 3);
    }

    @Test
    public void testHighestIdLuggage(){
        dao = new AirportDAO();
        assertEquals(dao.highestIdLuggage(), 3);
    }

    @Test
    public void testHighestIdPassenger(){
        dao = new AirportDAO();
        assertEquals(dao.highestIdPassenger(), 4);
    }

    @Test
    public void testHighestIdFlight(){
        dao = new AirportDAO();
        assertEquals(dao.highestIdFlight(), 1);
    }

    @Test
    public void testHighestIdRole(){
        dao = new AirportDAO();
        assertEquals(dao.highestIdRole(), 2);
    }

    @Test
    public void testHighestIdUser(){
        dao = new AirportDAO();
        assertEquals(dao.highestIdUser(), 1);
    }

    @Test
    public void testDoesAirExist(){
        dao = new AirportDAO();
        assertEquals(dao.doesAirlineExist(1), true);
    }
    @Test
    public void testDoesFliExist(){
        dao = new AirportDAO();
        assertEquals(dao.doesFlightExist(1), true);
    }
    @Test
    public void testDoesLuggExist(){
        dao = new AirportDAO();
        assertEquals(dao.doesLuggageExist(1), true);
    }

    @Test
    public void testDoesRoleExist(){
        dao = new AirportDAO();
        assertEquals(dao.doesRoleExist(1), true);
    }
    @Test
    public void testDoesPassExist(){
        dao = new AirportDAO();
        assertEquals(dao.doesPassengerExist(1), true);
    }

    @Test
    public void testDoesFTExist(){
        dao = new AirportDAO();
        assertEquals(dao.doesFlightTypeExist(1), true);
    }
    @Test
    public void testDoesUserExist(){
        dao = new AirportDAO();
        assertEquals(dao.doesUserExist(1), true);
    }
}