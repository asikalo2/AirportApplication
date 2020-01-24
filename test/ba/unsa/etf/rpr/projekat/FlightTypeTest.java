package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FlightTypeTest {

    @Test
    public void gettingFlightTypeName(){
        FlightType flightType = new FlightType();
        flightType.setName("123456");
        assertEquals(flightType.getName(), "123456");
    }

    @Test
    public void gettingFlightTypeName2(){
        FlightType flightType = new FlightType();
        flightType.setName("123456");
        assertNotEquals(flightType.getName(), "1234567");
    }

    @Test
    public void gettingFlightTypeName3(){
        FlightType flightType = new FlightType();
        flightType.setName("123456");
        assertNotEquals(flightType.getName(), 1234567);
    }

    @Test
    public void gettingFlightTypeId(){
        FlightType flightType = new FlightType();
        flightType.setId(5);
        assertEquals(flightType.getId(), 5);
    }

    @Test
    public void gettingFlightTypeId2(){
        FlightType flightType = new FlightType();
        flightType.setId(5);
        assertNotEquals(flightType.getId(), 6);
    }

    @Test
    public void gettingFlightTypeId3(){
        FlightType flightType = new FlightType();
        flightType.setId(5);
        assertNotEquals(flightType.getId(), "5");
    }

    @Test
    public void gettingFlightTypeToString(){
        FlightType flightType = new FlightType(5, "Regular");
        assertEquals(flightType.toString(), "5 Regular");
    }
}
