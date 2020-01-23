package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FlightTest {

    @Test
    public void gettingFlightAirplane() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setAirplane(new Airplane(20, new Airline(200, "NewAir", "N87U"), "NewAirplaneV", "BHU76",200));
        assertNotEquals(flight.getAirplane().getManufacturer(), "NewAir");
    }

    @Test
    public void gettingFlightAirplane2() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setAirplane(new Airplane(20, new Airline(200, "NewAir", "N87U"), "NewAirplaneV", "BHU76",200));
        assertEquals(flight.getAirplane().getId(), 20);
    }

    @Test
    public void gettingFlightAirplane3() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setAirplane(new Airplane(20, new Airline(200, "NewAir", "N87U"), "NewAirplaneV", "BHU76",200));
        assertNotEquals(flight.getAirplane().getManufacturer(), "NB7U");
    }

    @Test
    public void gettingFlightAirplane4() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setAirplane(new Airplane(20, new Airline(200, "NewAir", "N87U"), "NewAirplaneV", "BHU76",200));
        assertNotEquals(flight.getId(), 21);
    }

    @Test
    public void gettingFlightId(){
        Flight flight = new Flight();
        flight.setId(5);
        assertEquals(flight.getId(), 5);
    }

    @Test
    public void gettingFlightId2(){
        Flight flight = new Flight();
        flight.setId(5);
        assertNotEquals(flight.getId(), 6);
    }

    @Test
    public void gettingFlightId3(){
        Flight flight = new Flight();
        flight.setId(5);
        assertNotEquals(flight.getId(), "5");
    }

    @Test
    public void gettingAirlineFromAirplane1() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setAirplane(new Airplane(150, new Airline(5, "NewNew", "HZ76"), "Airbus", "CV67Z",200));
        assertEquals(flight.getAirplane().getAirline().getId(), 5);
    }

    @Test
    public void gettingAirlineFromAirplane2() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setAirplane(new Airplane(150, new Airline(5, "NewNew", "HZ76"), "Airbus", "CV67Z",200));
        assertEquals(flight.getAirplane().getAirline().getName(), "NewNew");
    }

    @Test
    public void gettingAirlineFromAirplane3() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setAirplane(new Airplane(150, new Airline(5, "NewNew", "HZ76"), "Airbus", "CV67Z",200));
        assertEquals(flight.getAirplane().getNumberOfSeats(), 200);
    }

//    @Test
//    public void gettingAirplaneType(){
//        Airplane airplane = new Airplane();
//        airplane.setType("BHZ628");
//        assertEquals(airplane.getType(), "BHZ628");
//    }
//
//    @Test
//    public void gettingAirplaneType2(){
//        Airplane airplane = new Airplane();
//        airplane.setType("");
//        assertNotEquals(airplane.getType(), "NewAirline2");
//    }
//
//    @Test
//    public void gettingAirplaneType3(){
//        Airplane airplane = new Airplane();
//        airplane.setType("321");
//        assertNotEquals(airplane.getType(), 231);
//    }

}
