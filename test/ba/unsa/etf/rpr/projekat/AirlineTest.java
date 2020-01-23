package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AirlineTest {

    @Test
    public void gettingAirlineName(){
        Airline airline = new Airline();
        airline.setName("NewAirline");
        assertEquals(airline.getName(), "NewAirline");
    }

    @Test
    public void gettingAirlineName2(){
        Airline airline = new Airline();
        airline.setName("NewAirline");
        assertNotEquals(airline.getName(), "NewAirline2");
    }

    @Test
    public void gettingAirlineName3(){
        Airline airline = new Airline();
        airline.setName("Airlinenew");
        assertNotEquals(airline.getName(), 1234567);
    }

    @Test
    public void gettingUserId(){
        Airline airline = new Airline();
        airline.setId(5);
        assertEquals(airline.getId(), 5);
    }

    @Test
    public void gettingUserId2(){
        Airline airline = new Airline();
        airline.setId(5);
        assertNotEquals(airline.getId(), 6);
    }

    @Test
    public void gettingUserId3(){
        Airline airline = new Airline();
        airline.setId(5);
        assertNotEquals(airline.getId(), "5");
    }

    @Test
    public void gettingAirlineCode(){
        Airline airline = new Airline();
        airline.setName("BHZ628");
        assertEquals(airline.getName(), "BHZ628");
    }

    @Test
    public void gettingAirlineCode2(){
        Airline airline = new Airline();
        airline.setName("");
        assertNotEquals(airline.getName(), "NewAirline2");
    }

    @Test
    public void gettingAirlineCode3(){
        Airline airline = new Airline();
        airline.setCode("321");
        assertNotEquals(airline.getName(), 231);
    }
}
