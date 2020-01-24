package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirlineControllerTest {


    @Test
    public void controllerInit(){
        AirportDAO dao = new AirportDAO();
        Airline airline = new Airline(130, "New airline", "78HGT");
        dao.addAirline(airline);
        assertEquals(airline.getName(), "New airline");
    }
}
