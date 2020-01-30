package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GateControllerTest {

    @Test
    public void checkFillForm() {
        AirportDAO dao = new AirportDAO();
        Gate gate = new Gate(4,"null");
        GateController gateController = new GateController(dao, gate);
        gateController.fillForm();
        assertEquals(gateController.nameProperty.get(), "null");
    }
    @Test
    public void checkFillForm2() {
        AirportDAO dao = new AirportDAO();
        Gate gate = new Gate(4,"null");
        GateController gateController = new GateController(dao, gate);
        gateController.fillForm();
        assertEquals(gateController.idProperty.get(), "4");
    }
}
