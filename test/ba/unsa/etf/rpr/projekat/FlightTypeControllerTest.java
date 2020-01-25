package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightTypeControllerTest {

    @Test
    public void checkFillForm(){
        AirportDAO dao = new AirportDAO();
        FlightType flightType = new FlightType(7,"test");
        FlightTypeController flightTypeController = new FlightTypeController(dao, flightType);
        flightTypeController.fillForm();
        assertEquals(flightTypeController.nameProperty.get(), "test");
    }

}
