package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AdditionalLuggageTest {

    @Test
    public void gettingAdditionalLuggage(){
    AdditionalLuggage luggage = new AdditionalLuggage(6,new Passenger(), 30, 10,null);
    assertEquals(luggage.getPayExtra(), 10);
    }

    @Test
    public void gettingAdditionalLuggage2(){
        AdditionalLuggage luggage = new AdditionalLuggage(6,new Passenger(), 30, 10,null);
        assertEquals(luggage.getWeight(), 30);
    }

    @Test
    public void gettingAdditionalLuggage3(){
        AdditionalLuggage luggage = new AdditionalLuggage(6,new Passenger(), 30, 10,null);
        assertEquals(luggage.getId(), 6);
    }

    @Test
    public void gettingAdditionalLuggage4(){
        AdditionalLuggage luggage = new AdditionalLuggage(6,new Passenger(), 30, 10,null);
        assertNotEquals(luggage.getId(), 10);
    }
    @Test
    public void gettingAdditionalLuggage5(){
        AdditionalLuggage luggage = new AdditionalLuggage();
        luggage.setWeight(10);
        assertEquals(luggage.getWeight(), 10);
    }

    @Test
    public void gettingAdditionalLuggage6(){
        AdditionalLuggage luggage = new AdditionalLuggage();
        luggage.setPayExtra(10);
        assertEquals(luggage.getPayExtra(), 10);
    }

    @Test
    public void gettingAdditionalV2(){
        AdditionalLuggage luggage = new AdditionalLuggage();
        luggage.setAddLuggageType(AdditionalLuggage.Type.METAL);
        assertEquals(luggage.getAddLuggageType(), AdditionalLuggage.Type.METAL);
    }

}
