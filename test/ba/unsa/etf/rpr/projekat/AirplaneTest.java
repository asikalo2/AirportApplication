package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AirplaneTest {

    @Test
    public void gettingAirplaneName(){
        Airplane airplane = new Airplane();
        airplane.setManufacturer("NewAirplane");
        assertEquals(airplane.getManufacturer(), "NewAirplane");
    }

    @Test
    public void doStuffThrowsIndexOutOfBoundsException() {
        Throwable exception = assertThrows(IllegalNumberOfSeats.class, ()
                -> new Airplane(1,new Airline(5,"Asda","JHDS"),"JEH","JEEE",400));
        assertEquals("Illegal number of seats!", exception.getMessage());
    }

    @Test
    public void gettingAirplaneName2(){
        Airplane airplane = new Airplane();
        airplane.setManufacturer("NewAirplane");
        assertNotEquals(airplane.getManufacturer(), "NewAirplane2");
    }

    @Test
    public void gettingAirplaneName3(){
        Airplane airplane = new Airplane();
        airplane.setManufacturer("NewAirplane");
        assertNotEquals(airplane.getManufacturer(), 1234567);
    }

    @Test
    public void gettingAirplaneId(){
        Airplane airplane = new Airplane();
        airplane.setId(5);
        assertEquals(airplane.getId(), 5);
    }

    @Test
    public void gettingAirplaneId2(){
        Airplane airplane = new Airplane();
        airplane.setId(5);
        assertNotEquals(airplane.getId(), 6);
    }

    @Test
    public void gettingAirplaneId3(){
        Airplane airplane = new Airplane();
        airplane.setId(5);
        assertNotEquals(airplane.getId(), "5");
    }

    @Test
    public void gettingNumberOfSeats(){
        Airplane airplane = new Airplane();
        airplane.setNumberOfSeats(23);
        assertEquals(airplane.getNumberOfSeats(), 23);
    }

    @Test
    public void gettingNumberOfSeats2(){
        Airplane airplane = new Airplane();
        airplane.setNumberOfSeats(23);
        assertNotEquals(airplane.getNumberOfSeats(), "23");
    }

    @Test
    public void gettingAirlineFromAirplane1(){
        Airplane airplane = new Airplane();
        airplane.setAirline(new Airline(150, "NewAirline", "CV67Z"));
        assertEquals(airplane.getAirline().getName(), "NewAirline");
    }

    @Test
    public void gettingAirlineFromAirplane2(){
        Airplane airplane = new Airplane();
        airplane.setAirline(new Airline(150, "NewAirline", "CV67Z"));
        assertEquals(airplane.getAirline().getCode(), "CV67Z");
    }

    @Test
    public void gettingAirlineFromAirplane3(){
        Airplane airplane = new Airplane();
        airplane.setAirline(new Airline(150, "NewAirline", "CV67Z"));
        assertEquals(airplane.getAirline().getId(), 150);
    }

    @Test
    public void gettingAirplaneType(){
        Airplane airplane = new Airplane();
        airplane.setType("BHZ628");
        assertEquals(airplane.getType(), "BHZ628");
    }

    @Test
    public void gettingAirplaneType2(){
        Airplane airplane = new Airplane();
        airplane.setType("");
        assertNotEquals(airplane.getType(), "NewAirline2");
    }

    @Test
    public void gettingAirplaneType3(){
        Airplane airplane = new Airplane();
        airplane.setType("321");
        assertNotEquals(airplane.getType(), 231);
    }

//    @Test
//    public void gettingException1() throws IllegalNumberOfSeats {
//        assertDoesNotThrow((Executable) new Airplane(7,new Airline(),"Manu", "M7HZ", 200));
//
//    }

}
