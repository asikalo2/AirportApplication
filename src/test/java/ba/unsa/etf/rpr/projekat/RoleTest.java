package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RoleTest {

    @Test
    public void gettingUserName(){
        Role role = new Role();
        role.setName("123456");
        assertEquals(role.getName(), "123456");
    }

    @Test
    public void gettingUserName2(){
        Role role = new Role();
        role.setName("123456");
        assertNotEquals(role.getName(), "1234567");
    }

    @Test
    public void gettingUserName3(){
        Role role = new Role();
        role.setName("123456");
        assertNotEquals(role.getName(), 1234567);
    }

    @Test
    public void gettingUserId(){
        Role role = new Role();
        role.setId(5);
        assertEquals(role.getId(), 5);
    }

    @Test
    public void gettingRoleId2(){
        Role role = new Role();
        role.setId(5);
        assertNotEquals(role.getId(), 6);
    }

    @Test
    public void gettingRoleId3(){
        Role role = new Role();
        role.setId(5);
        assertNotEquals(role.getId(), "5");
    }
}
