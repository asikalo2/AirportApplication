package ba.unsa.etf.rpr.projekat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void gettingUserName(){
        User user = new User();
        user.setName("123456");
        assertEquals(user.getName(), "123456");
    }

    @Test
    public void gettingUserName2(){
        User user = new User();
        user.setName("123456");
        assertNotEquals(user.getName(), "1234567");
    }

    @Test
    public void gettingUserName3(){
        User user = new User();
        user.setName("123456");
        assertNotEquals(user.getName(), 1234567);
    }

    @Test
    public void gettingUserId(){
        User user = new User();
        user.setId(5);
        assertEquals(user.getId(), 5);
    }

    @Test
    public void gettingUserId2(){
        User user = new User();
        user.setId(5);
        assertNotEquals(user.getId(), 6);
    }

    @Test
    public void gettingUserId3(){
        User user = new User();
        user.setId(5);
        assertNotEquals(user.getId(), "5");
    }

    @Test
    public void gettingRole(){
        User user = new User();
        user.setRole(new Role(5,"Amila"));
        assertEquals(user.getRole().getName(), "Amila");
    }

    @Test
    public void gettingRole1(){
        User user = new User();
        user.setRole(new Role(5,"Amila"));
        assertNotEquals(user.getRole().getName(), "Dea");
    }
    @Test
    public void gettingRole2(){
        User user = new User();
        user.setRole(new Role(5,"Amila"));
        assertNotEquals(user.getRole().getName(), 2);
    }

    @Test
    public void gettingRole3(){
        User user = new User();
        user.setRole(new Role(5,"Amila"));
        assertEquals(user.getRole().getId(), 5);
    }

    @Test
    public void gettingRole4(){
        User user = new User();
        user.setRole(new Role(5,"Amila"));
        assertNotEquals(user.getRole().getId(), "Amila");
    }

    @Test
    public void gettingRole5(){
        User user = new User();
        user.setRole(new Role(5,"Amila"));
        assertNotEquals(user.getRole().getId(), 6);
    }
}
