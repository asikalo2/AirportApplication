package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserControllerTest {

    @Test
    public void checkFillForm() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        User user = new User(4,"Useruser",new Role(3,"CEO"));
        UserController userController = new UserController(dao, user);
        userController.fillForm();
        assertEquals(userController.idProperty.get(), "4");
    }

    @Test
    public void checkFillForm2() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        User user = new User(4,"Useruser",new Role(3,"CEO"));
        UserController userController = new UserController(dao, user);
        userController.fillForm();
        assertEquals(userController.nameProperty.get(), "Useruser");
    }

    @Test
    public void checkFillForm3() throws IllegalNumberOfSeats {
        AirportDAO dao = new AirportDAO();
        User user = new User(4,"Useruser",new Role(3,"CEO"));
        UserController userController = new UserController(dao, user);
        userController.fillForm();
        assertEquals(false, userController.roleProperty.isBound());
    }
}
