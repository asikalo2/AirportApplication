package ba.unsa.etf.rpr.projekat;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LuggageTest {

    @Test
    public void gettingLuggageId1() {
        Luggage luggage = new Luggage(5, new Passenger());
        assertEquals(luggage .getId(), 5);
    }

    @Test
    public void gettingLuggageId10() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("qrcode.png").getFile());
        Image image = null;
        try
        {
            image = new Image(new FileInputStream(file.getAbsoluteFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Luggage luggage = new Luggage(5, new Passenger(7,"New",new Flight(),image));
        assertEquals(luggage.getPassengerName(), "New");
    }

    @Test
    public void gettingLuggageId2()  {
        Luggage luggage = new Luggage(5, new Passenger());
        assertNotEquals(luggage.getId(), 7);
    }

    @Test
    public void gettingType(){
        HandLuggage luggage1 = new HandLuggage(5,new Passenger(),10.0,10.0);
        assertEquals(luggage1.getPayExtra(), 10.0);
    }

    @Test
    public void gettingType2(){
        HandLuggage luggage1 = new HandLuggage(5,new Passenger(),10.0,10.0);
        assertEquals(luggage1.getWeight(), 10.0);
    }

    @Test
    public void gettingType3(){
        AdditionalLuggage luggage1 = new AdditionalLuggage(5,new Passenger(),10.0,10.0, AdditionalLuggage.Type.METAL);
        assertEquals(luggage1.getPayExtra(), 10.0);
    }

    @Test
    public void gettingType4(){
        AdditionalLuggage luggage1 = new AdditionalLuggage(5,new Passenger(),10.0,10.0, AdditionalLuggage.Type.METAL);
        assertEquals(luggage1.getWeight(), 10.0);
    }

    @Test
    public void gettingType5(){
        AdditionalLuggage luggage1 = new AdditionalLuggage(5,new Passenger(),10.0,10.0, AdditionalLuggage.Type.METAL);
        assertEquals(luggage1.getAddLuggageType(), AdditionalLuggage.Type.METAL);
    }

    @Test
    public void gettingType6(){
        Luggage luggage1 = new AdditionalLuggage(5,new Passenger(),10.0,10.0, AdditionalLuggage.Type.METAL);
        assertEquals(luggage1.getLuggageType(), "Additional Luggage");
    }

    @Test
    public void gettingType7(){
        Luggage luggage1 = new HandLuggage(5,new Passenger(),10.0,10.0);
        assertEquals(luggage1.getLuggageType(), "Hand Luggage");
    }

    @Test
    public void gettingType9(){
        HandLuggage luggage1 = new HandLuggage(5,new Passenger(),10.0,10.0);
        luggage1.setPayExtra(11.6);
        assertNotEquals(luggage1.getPayExtra(), 11.6);
    }

    @Test
    public void gettingType10(){
        HandLuggage luggage1 = new HandLuggage();
        luggage1.setWeight(10);
        assertNotEquals(luggage1.getWeight(), 10);
    }

    @Test
    public void gettingType8(){
        Luggage luggage1 = new Luggage(5,new Passenger());
        assertEquals(luggage1.getLuggageType(), "Standard");
    }

    @Test
    public void gettingLuggagePassenger1() {
        Luggage luggage = new Luggage(5, new Passenger());
        assertEquals(luggage .getId(), 5);
    }

    @Test
    public void gettingLuggagePassenger2()  {
        Passenger passenger = new Passenger();
        Luggage luggage = new Luggage(5, passenger);
        assertEquals(luggage.getPassenger(), passenger);
    }

    @Test
    public void gettingLuggagePassenger3()  {
        Passenger passenger = new Passenger();
        Luggage luggage = new Luggage();
        luggage.setPassenger(passenger);
        assertEquals(luggage.getPassenger(), passenger);
    }
    @Test
    public void gettingLuggagePassenger4()  {
        Luggage luggage = new Luggage();
        luggage.setId(4);
        assertEquals(luggage.getId(), 4);
    }
}
