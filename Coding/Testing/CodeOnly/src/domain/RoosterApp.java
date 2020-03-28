package domain;

import code.College;
import code.Klas;
import code.Rooster;
import code.RoosterRegel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RoosterApp extends Application {
    public static void main(String[] args) throws IOException {
        Klas.procesKlas();
        College.procesCollege();
        RoosterRegel.procesRooster();

        Rooster rooster = new Rooster();
        Rooster.setRooster(rooster);

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(RoosterApp.class.getClassLoader().getResource("domain/rooster.fxml"));
        Parent root = loader.load();

        stage.setTitle("RoosterApp");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
