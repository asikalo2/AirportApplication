package ba.unsa.etf.rpr.projekat;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AirportDAOTest {

    AirportDAO dao;



    @BeforeAll

    void initDb() {
        File dbfile = new File("AirportDBtest.db");
        ClassLoader classLoader = getClass().getClassLoader();
        File srcfile = new File(classLoader.getResource("AirportDBtest.db").getFile());
        try {
            if (dbfile.exists())
                dbfile.delete();
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

    @AfterAll
    void deleteDb() {
        File dbfile = new File("AirportDBtest.db");
        dbfile.delete();
    }

    @Test
    void getFlightTypes() {
        dao = new AirportDAO();
        assertEquals(4, dao.getFlightTypes().size());
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
        assertEquals(flights.get(0).getId(), 1);
        assertEquals(flights.get(0).getAirplane().getAirline().getName(), "Adria Airways");
    }


    @Test
    void deleteRole() {
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
    void deleteFlightType() {
        dao = new AirportDAO();
        FlightType flightType = new FlightType(5, "Security");
        dao.addFlightType(flightType);
        ObservableList<FlightType> flightTypes = dao.getFlightTypes();
        assertEquals(5, flightTypes.size());
        dao.deleteFlightType(flightType);
        ObservableList<FlightType> flightTypes1 = dao.getFlightTypes();
        assertEquals(4, flightTypes1.size());

    }


    @Test
    void addFlightType() {
        dao = new AirportDAO();
        FlightType flightType = new FlightType(4, "Type1");
        dao.addFlightType(flightType);
        ObservableList<FlightType> flightTypes = dao.getFlightTypes();
        assertEquals(4, flightTypes.size());
    }

   /* @Test
    void deleteFlightType() {
        dao = new AirportDAO();
        FlightType flightType = new FlightType(3, "Type1");
        dao.addFlightType(flightType);
        ObservableList<FlightType> flightTypes = dao.getFlightTypes();
        assertEquals(3, flightTypes.size());
        dao.deleteFlightType(flightType);
        ObservableList<FlightType> flightTypes1 = dao.getFlightTypes();
        assertEquals(2, flightTypes1.size());

    }*/

    @Test
    void addAirline() {
        dao = new AirportDAO();
        Airline airline = new Airline(109, "Lufthansa", "LH C87");
        dao.addAirline(airline);
        ObservableList<Airline> airlines = dao.getAirlines();
        assertEquals(109, airlines.size());
    }

   /* @Test
    void deleteAirline() {
        dao = new AirportDAO();
        Airline airline = new Airline(3, "Lufthansa", "LH C87");
        dao.addAirline(airline);
        ObservableList<Airline> airlines = dao.getAirlines();
        assertEquals(3, airlines.size());
        dao.deleteAirline(airline);
        ObservableList<Airline> airlines1 = dao.getAirlines();
        assertEquals(2, airlines1.size());

    }*/
}