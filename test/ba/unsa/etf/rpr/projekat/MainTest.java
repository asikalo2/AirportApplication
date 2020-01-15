package ba.unsa.etf.rpr.projekat;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(ApplicationExtension.class)
public class MainTest {
    Stage theStage;
    AirportDAO dao;
    Controller controller;

    @Start
    public void start(Stage stage) throws Exception {
        File dbfile = new File("AirportDB.db");
        ClassLoader classLoader = getClass().getClassLoader();
        File srcfile = new File(classLoader.getResource("AirportDBtest.db").getFile());
        try {
            dbfile.delete();
            Files.copy(srcfile.toPath(), dbfile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Cannot init db!");
        }

        dao = new AirportDAO();

        // Ovo bi trebalo da iskopira fajl iz resources u test-resources, a ipak radi i sa mavenom
        File fxml = new File("resources/fxml/glavna.fxml");
        if (fxml.exists()) {
            File rsrc = new File("test-resources/fxml/glavna.fxml");
            if (rsrc.exists()) rsrc.delete();
            Files.copy(fxml.toPath(), rsrc.toPath());
        }
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/glavna.fxml"), bundle);
        stage.setTitle("Airport");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
        stage.toFront();

        theStage = stage;
    }

    @Test
    public void testRemoveAirline(FxRobot robot) {

        ObservableList<Airline> airlines = dao.getAirlines();
        robot.clickOn("#tableAirline");
        robot.clickOn("JP");

        robot.clickOn("#tbRemoveAirline");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        ObservableList<Airline> airlines1 = dao.getAirlines();
        assertEquals(airlines.size() - 1, airlines1.size());
    }

    @Test
    public void testEditAirline(FxRobot robot) {

        ObservableList<Airline> airlines = dao.getAirlines();
        robot.clickOn("#tableAirline");
        robot.clickOn("JP");

        robot.clickOn("#tbEditAirline");
        robot.lookup("#nameField").tryQuery().isPresent();
        robot.clickOn("#nameField");
        robot.write("abc");

        robot.clickOn("#okButton");
        Airline airline = dao.getAirlines().get(0);
        assertEquals(airline.getName(), "Adria Airwaysabc");
    }

}