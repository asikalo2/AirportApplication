package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

public class IllegalNumberOfSeatsTest {

    @Test
    public void testExceptionNoS(){
        Airplane airplane = new Airplane();
        airplane.setNumberOfSeats(301);
    }
}
