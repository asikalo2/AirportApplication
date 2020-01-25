package ba.unsa.etf.rpr.projekat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/glavna.fxml"));
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/glavna.fxml"), bundle);
        primaryStage.setTitle("Airport");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void loadView(Locale locale) throws IOException {
        // ova metoda se poziva kada se odabare promjena jezika da bi ponovo izgenerisala
        // prikaz forme. Ucitava se resourceBundle sa novoodabranom localeom i STAGE objektu se dodjeljuje
        // ponovno izgenerisana forma
        System.out.println("test");
        ResourceBundle bundle = ResourceBundle.getBundle("Translation", locale);
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/glavna.fxml"), bundle);
        stage.setTitle("Airlines");
        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

