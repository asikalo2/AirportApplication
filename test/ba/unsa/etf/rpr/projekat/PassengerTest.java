package ba.unsa.etf.rpr.projekat;

import javafx.scene.image.Image;
import net.sf.jasperreports.util.IdentitySecretsProviderFactory;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.InputStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {

    @Test
    public void gettingUserName(){
        Passenger passenger = new Passenger();
        passenger.setName("123456");
        assertEquals(passenger.getName(), "123456");
    }

    @Test
    public void gettingUserName2(){
        Passenger passenger = new Passenger();
        passenger.setName("123456");
        assertNotEquals(passenger.getName(), "1234567");
    }

    @Test
    public void gettingUserName3(){
        Passenger passenger = new Passenger();
        passenger.setName("123456");
        assertNotEquals(passenger.getName(), 1234567);
    }

    @Test
    public void gettingUserId(){
        Passenger passenger = new Passenger();
        passenger.setId(5);
        assertEquals(passenger.getId(), 5);
    }

    @Test
    public void gettingUserId2(){
        Passenger passenger = new Passenger();
        passenger.setId(5);
        assertNotEquals(passenger.getId(), 6);
    }

    @Test
    public void gettingUserId3(){
        Passenger passenger = new Passenger();
        passenger.setId(5);
        assertNotEquals(passenger.getId(), "5");
    }

    @Test
    public void gettingFlightFromPassenger1() throws IllegalNumberOfSeats {
        Passenger passenger = new Passenger();
        passenger.setFlight(new Flight(10, "LH78",
                new Airplane(150, new Airline(5, "NewNew",
                        "HZ76"), "Airbus", "CV67Z",200),null,null,
                new FlightType(5, "Regular"),
                new User(3, "Dea", new Role (6, "CEO")),
                new Gate(1, "FirstGate")));
        assertEquals(passenger.getFlight().getCode(), "LH78");
    }

    @Test
    public void gettingFlightFromPassenger2() throws IllegalNumberOfSeats {
        Passenger passenger = new Passenger();
        passenger.setFlight(new Flight(10, "LH78",
                new Airplane(150, new Airline(5, "NewNew",
                        "HZ76"), "Airbus", "CV67Z",200),null,null,
                new FlightType(5, "Regular"),
                new User(3, "Dea", new Role (6, "CEO")),
                new Gate(1, "FirstGate")));
        assertNotEquals(passenger.getFlight().getCode(), "CV67Z");
    }
    @Test
    public void gettingFlightFromPassenger3() throws IllegalNumberOfSeats {
        Passenger passenger = new Passenger();
        passenger.setFlight(new Flight(10, "LH78",
                new Airplane(150, new Airline(5, "NewNew",
                        "HZ76"), "Airbus", "CV67Z",200),null,null,
                new FlightType(5, "Regular"),
                new User(3, "Dea", new Role (6, "CEO")),
                new Gate(1, "FirstGate")));
        assertNotEquals(passenger.getFlight().getAirplane().getId(),15);
    }

    @Test
    public void gettingFlightFromPassenger4() throws IllegalNumberOfSeats {
        Passenger passenger = new Passenger();
        passenger.setFlight(new Flight(10, "LH78",
                new Airplane(150, new Airline(5, "NewNew",
                        "HZ76"), "Airbus", "CV67Z",200),null,null,
                new FlightType(5, "Regular"),
                new User(3, "Dea", new Role (6, "CEO")),
                new Gate(1, "FirstGate")));
        assertEquals(passenger.getFlight().getAirplane().getAirline().getName(), "NewNew");
    }

    @Test
    public void gettingFlightFromPassenger5() throws IllegalNumberOfSeats {
        Passenger passenger = new Passenger();
        passenger.setFlight(new Flight(10, "LH78",
                new Airplane(150, new Airline(5, "NewNew",
                        "HZ76"), "Airbus", "CV67Z",200),null,null,
                new FlightType(5, "Regular"),
                new User(3, "Dea", new Role (6, "CEO")),
                new Gate(1, "FirstGate")));
        assertEquals(passenger.getFlight().getAirplane().getNumberOfSeats(), 200);
    }

    @Test
    public void gettingFlightFromPassenger6() throws IllegalNumberOfSeats {
        Passenger passenger = new Passenger();
        passenger.setFlight(new Flight(10, "LH78",
                new Airplane(150, new Airline(5, "NewNew",
                        "HZ76"), "Airbus", "CV67Z",200),null,null,
                new FlightType(5, "Regular"),
                new User(3, "Dea", new Role (6, "CEO")),
                new Gate(1, "FirstGate")));
        assertEquals(passenger.getFlight().getStartOfUsingTheRunway(), null);
    }

    @Test
    public void gettingFlightFromPassenger() throws IllegalNumberOfSeats {
        Passenger passenger = new Passenger();
        passenger.setFlight(new Flight(10, "LH78",
                new Airplane(150, new Airline(5, "NewNew",
                        "HZ76"), "Airbus", "CV67Z",200),null,null,
                new FlightType(5, "Regular"),
                new User(3, "Dea", new Role (6, "CEO")),
                new Gate(1, "FirstGate")));
        assertEquals(passenger.getFlight().getEndOfUsingTheRunway(), null);
    }
}
