package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirlineControllerTest {

    @Test
    public void checkFillForm(){
        AirportDAO dao = new AirportDAO();
        Airline airline = new Airline(7,"Air","COD3");
        AirlineController airlineController = new AirlineController(dao, airline);
        airlineController.fillForm();
        assertEquals(airlineController.idProperty.get(),"7");
    }

    @Test
    public void checkFillForm2(){
        AirportDAO dao = new AirportDAO();
        Airline airline = new Airline(7,"Air","COD3");
        AirlineController airlineController = new AirlineController(dao, airline);
        airlineController.fillForm();
        assertEquals(airlineController.nameProperty.get(), "Air");
    }

    @Test
    public void checkFillForm3(){
        AirportDAO dao = new AirportDAO();
        Airline airline = new Airline(7,"Air","COD3");
        AirlineController airlineController = new AirlineController(dao, airline);
        airlineController.fillForm();
        assertEquals(airlineController.codeProperty.get(), "COD3");
    }
}
