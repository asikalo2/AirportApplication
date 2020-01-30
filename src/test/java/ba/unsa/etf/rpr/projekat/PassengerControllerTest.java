package ba.unsa.etf.rpr.projekat;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassengerControllerTest {

    @Test
    public void checkFillForm() {
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
        Passenger passenger = new Passenger(4,"Amila",new Flight(),image);
        PassengerController passengerController = new PassengerController(dao, passenger);
        passengerController.fillForm();
        assertEquals(passengerController.idProperty.get(), "4");

    }

    @Test
    public void checkFillForm2() {
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
        Passenger passenger = new Passenger(4,"Amila",new Flight(),image);
        PassengerController passengerController = new PassengerController(dao, passenger);
        passengerController.fillForm();
        assertEquals(passengerController.nameProperty.get(), "Amila");

    }

    @Test
    public void checkFillForm3() {
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
        Passenger passenger = new Passenger(4,"Amila",new Flight(),image);
        PassengerController passengerController = new PassengerController(dao, passenger);
        passengerController.fillForm();
        assertEquals(passengerController.qrCodeProperty.get(), image);

    }
}
