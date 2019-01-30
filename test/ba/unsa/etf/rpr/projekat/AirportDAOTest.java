package ba.unsa.etf.rpr.projekat;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportDAOTest {

    AirportDAO dao;

    @Test
    void getFlightTypes() {
        dao = new AirportDAO();
        System.out.println(dao.getFlightTypes());
        assertEquals(2, 2);
        // Za sada samo ispis da vidim da li radi
    }

    @Test
    void getPlaneById() {
        dao = new AirportDAO();
        Airplane plane = dao.getPlaneById(1);
        assertEquals(plane.getId(), 1);
        assertEquals(plane.getAirline().getName(), "Lufthansa");
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
        assertEquals(flights.get(0).getAirplane().getAirline().getName(), "Lufthansa");
    }
}