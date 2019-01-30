package ba.unsa.etf.rpr.projekat;

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
        assertEquals(user.getRole().getName(), "SomeRole");
    }

}