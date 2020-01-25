package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirplaneControllerTest {

    @Test
    public void checkFillForm() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        Airplane airplane = new Airplane(7,new Airline(),"COD3","HZU87",27);
        AirplaneController airplaneController = new AirplaneController(dao, airplane);
        airplaneController.fillForm();
        assertEquals(airplaneController.idProperty.get(), "7");
    }

    @Test
    public void checkFillForm2() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        Airplane airplane = new Airplane(7,new Airline(165,"HNTF","JDSZ"),"COD3","HZU87",27);
        AirplaneController airplaneController = new AirplaneController(dao, airplane);
        airplaneController.fillForm();
        assertEquals(airplaneController.airlineProperty.get().getName(), "HNTF");
    }
    @Test
    public void checkFillForm3() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        Airplane airplane = new Airplane(7,new Airline(),"COD3","HZU87",27);
        AirplaneController airplaneController = new AirplaneController(dao, airplane);
        airplaneController.fillForm();
        assertEquals(airplaneController.manufacturerProperty.get(), "COD3");
    }
    @Test
    public void checkFillForm4() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        Airplane airplane = new Airplane(7,new Airline(),"COD3","HZU87",27);
        AirplaneController airplaneController = new AirplaneController(dao, airplane);
        airplaneController.fillForm();
        assertEquals(airplaneController.typeProperty.get(), "HZU87");
    }
    @Test
    public void checkFillForm5() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        Airplane airplane = new Airplane(7,new Airline(),"COD3","HZU87",27);
        AirplaneController airplaneController = new AirplaneController(dao, airplane);
        airplaneController.fillForm();
        assertEquals(airplaneController.numberOfSeatsProperty.get(), "27");
    }

}
