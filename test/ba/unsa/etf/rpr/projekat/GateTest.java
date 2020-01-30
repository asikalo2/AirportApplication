package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GateTest {

    @Test
    public void gettingGateName(){
        Gate gate = new Gate();
        gate.setName("123456");
        assertEquals(gate.getName(), "123456");
    }

    @Test
    public void gettingGateName2(){
        Gate gate = new Gate();
        gate.setName("123456");
        assertNotEquals(gate.getName(), "1234567");
    }

    @Test
    public void gettingGateName3(){
        Gate gate = new Gate();
        gate.setName("123456");
        assertNotEquals(gate.getName(), 1234567);
    }

    @Test
    public void gettingGateId(){
        Gate gate = new Gate();
        gate.setId(5);
        assertEquals(gate.getId(), 5);
    }

    @Test
    public void gettingGateId2(){
        Gate gate = new Gate();
        gate.setId(5);
        assertNotEquals(gate.getId(), 6);
    }

    @Test
    public void gettingGateId3(){
        Gate gate = new Gate(3,"H2");
        assertEquals(gate.getId(), 3);
    }
}
