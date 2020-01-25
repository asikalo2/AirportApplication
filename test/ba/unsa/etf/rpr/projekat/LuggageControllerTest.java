package ba.unsa.etf.rpr.projekat;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LuggageControllerTest {

    @Test
    public void checkFillForm() throws IllegalCode {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("qrcode.png").getFile());
        Image image = null;
        try
        {
            image = new Image(new FileInputStream(file.getAbsoluteFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AirportDAO dao = new AirportDAO();
        Luggage luggage = new Luggage(4,new Passenger(4,"Amila",new Flight(), image));
        LuggageController luggageController = new LuggageController(dao, luggage);
        luggageController.fillForm();
        assertEquals(luggageController.idProperty.get(), "4");
    }
    @Test
    public void checkFillForm2() throws IllegalCode {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("qrcode.png").getFile());
        Image image = null;
        try
        {
            image = new Image(new FileInputStream(file.getAbsoluteFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AirportDAO dao = new AirportDAO();
        Luggage luggage = new Luggage(4,new Passenger(4,"Amila",new Flight(), image));
        LuggageController luggageController = new LuggageController(dao, luggage);
        luggageController.fillForm();
        assertEquals(luggageController.passengerProperty.get().getName(), "Amila");
    }

    @Test
    public void checkFillForm3() throws IllegalCode {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("qrcode.png").getFile());
        Image image = null;
        try
        {
            image = new Image(new FileInputStream(file.getAbsoluteFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AirportDAO dao = new AirportDAO();
        Luggage luggage = new Luggage(4,new Passenger(4,"Amila",new Flight(), image));
        LuggageController luggageController = new LuggageController(dao, luggage);
        luggageController.fillForm();
        assertEquals(luggageController.passengerProperty.get().getQrCode(), image);
    }

    @Test
    public void checkFillForm4() throws IllegalCode {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("qrcode.png").getFile());
        Image image = null;
        try
        {
            image = new Image(new FileInputStream(file.getAbsoluteFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AirportDAO dao = new AirportDAO();
        Luggage luggage = new Luggage(4,new Passenger(4,"Amila",new Flight(), image));
        LuggageController luggageController = new LuggageController(dao, luggage);
        luggageController.fillForm();
        assertEquals(luggageController.passengerProperty.get().getId(), 4);
    }
}
