package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LuggageTest {

    @Test
    public void gettingLuggageId1() {
        Luggage luggage = new Luggage(5, new Passenger());
        assertEquals(luggage .getId(), 5);
    }

    @Test
    public void gettingLuggageId2()  {
        Luggage luggage = new Luggage(5, new Passenger());
        assertNotEquals(luggage.getId(), 7);
    }

    @Test
    public void gettingLuggagePassenger1() {
        Luggage luggage = new Luggage(5, new Passenger());
        assertEquals(luggage .getId(), 5);
    }

    @Test
    public void gettingLuggagePassenger2()  {
        Passenger passenger = new Passenger();
        Luggage luggage = new Luggage(5, passenger);
        assertEquals(luggage.getPassenger(), passenger);
    }

    @Test
    public void gettingLuggagePassenger3()  {
        Passenger passenger = new Passenger();
        Luggage luggage = new Luggage();
        luggage.setPassenger(passenger);
        assertEquals(luggage.getPassenger(), passenger);
    }
    @Test
    public void gettingLuggagePassenger4()  {
        Luggage luggage = new Luggage();
        luggage.setId(4);
        assertEquals(luggage.getId(), 4);
    }
}
