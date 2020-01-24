package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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

    @Test
    public void gettingSOURofFight1() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setStartOfUsingTheRunway(LocalDateTime.of(2020, 01,10,14,16,10));
        assertEquals(flight.getStartOfUsingTheRunway(), LocalDateTime.of(2020, 01,10,14,16,10));
    }

    @Test
    public void gettingSOURofFight2() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setStartOfUsingTheRunway(LocalDateTime.of(2020, 01,10,14,16,10));
        assertNotEquals(flight.getStartOfUsingTheRunway(), LocalDateTime.of(2020, 10,01,14,16,10));
    }

    @Test
    public void gettingEOURofFight1() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setEndOfUsingTheRunway(LocalDateTime.of(2020, 01,10,14,16,10));
        assertEquals(flight.getEndOfUsingTheRunway(), LocalDateTime.of(2020, 01,10,14,16,10));
    }

    @Test
    public void gettingEOURofFight2() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setEndOfUsingTheRunway(LocalDateTime.of(2020, 01,10,14,16,10));
        assertNotEquals(flight.getEndOfUsingTheRunway(), LocalDateTime.of(2020, 10,01,14,16,10));
    }

    @Test
    public void gettingUserFromFlight1() throws IllegalNumberOfSeats {
        Flight flight = new Flight();
        flight.setUser(new User(3, "Dea", new Role (6, "CEO")));
        assertEquals(flight.getUser().getId(), 3);
    }

    @Test
    public void gettingUserFromFlight2(){
        Flight flight = new Flight();
        flight.setUser(new User(3, "Dea", new Role (6, "CEO")));
        assertNotEquals(flight.getUser().getRole().getId(), 3);
    }

    @Test
    public void gettingGateFromFlight1(){
        Flight flight = new Flight();
        flight.setGate(new Gate(3, "NewGate"));
        assertEquals(flight.getGate().getId(), 3);
    }

    @Test
    public void gettingGateFromFlight2(){
        Flight flight = new Flight();
        flight.setGate(new Gate(3, "NewGate"));
        assertNotEquals(flight.getGate().getName(), "Gate");
    }

    @Test
    public void gettingGateFromFlight3(){
        Flight flight = new Flight();
        flight.setGate(new Gate(3, "NewGate"));
        assertEquals(flight.getGateName(), "NewGate");
    }
    @Test
    public void gettingFlightCode(){
        Flight flight = new Flight();
        flight.setCode("BHZ628");
        assertEquals(flight.getCode(), "BHZ628");
    }

    @Test
    public void gettingFlightCode2(){
        Flight flight = new Flight();
        flight.setCode("");
        assertNotEquals(flight.getCode(), "NewAirline2");
    }

    @Test
    public void gettingFlightCode3(){
        Flight flight = new Flight();
        flight.setCode("321");
        assertNotEquals(flight.getCode(), 231);
    }

}
