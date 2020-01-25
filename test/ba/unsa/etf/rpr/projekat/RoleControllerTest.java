package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleControllerTest {

    @Test
    public void checkFillForm() {
        AirportDAO dao = new AirportDAO();
        Role role = new Role(4,"null");
        RoleController roleController = new RoleController(dao, role);
        roleController.fillForm();
        assertEquals(roleController.nameProperty.get(), "null");
    }

    @Test
    public void checkFillForm2(){
        AirportDAO dao = new AirportDAO();
        Role role = new Role(4,"null");
        RoleController roleController = new RoleController(dao, role);
        roleController.fillForm();
        assertEquals(roleController.idProperty.get(), "4");
    }
}
