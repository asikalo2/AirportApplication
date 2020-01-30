package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightControllerTest {

    @Test
    public void checkFillForm() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(4,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.idProperty.get(), "4");
    }
    @Test
    public void checkFillForm2() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(4,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.codeProperty.get(), "ZUTG7");
    }
    @Test
    public void checkFillForm3() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(4,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.endOfUsingTheRunwayProperty.get(), eur);
    }
    @Test
    public void checkFillForm4() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(10,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.startOfUsingTheRunwayProperty.get(), sour);
    }
    @Test
    public void checkFillForm5() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(10,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.airplaneProperty.get().getId(), 6);
    }

    @Test
    public void checkFillForm6() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(10,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.airplaneProperty.get().getAirline().getName(), "HZU");
    }

    @Test
    public void checkFillForm7() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(10,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.flightTypeProperty.get().getId(), 7);
    }
    @Test
    public void checkFillForm8() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(10,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.userProperty.get().getId(), 9);
    }
    @Test
    public void checkFillForm9() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(10,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.userProperty.get().getRole().getName(), "Name");
    }
    @Test
    public void checkFillForm10() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        LocalDateTime sour = LocalDateTime.MIN;
        LocalDateTime eur = LocalDateTime.MAX;
        Flight flight = new Flight(10,"ZUTG7", new Airplane(6,
                new Airline(109,"HZU","JZD"),"Manuf","Private",56),
                sour, eur,new FlightType(7,"Name"),new User(9,"Amila",
                new Role(5,"Name")),new Gate(8,"New"));
        FlightController flightController = new FlightController(dao, flight);
        flightController.fillForm();
        assertEquals(flightController.gateProperty.get().getName(), "New");
    }
}
