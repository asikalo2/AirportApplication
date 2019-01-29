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
}