package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {

    @Test
    public void charCheck1() {
        Validation unit = new Validation();
        char c='S';
        Boolean result = unit.charCheck(c);

        assertEquals(false, result);

    }

}
