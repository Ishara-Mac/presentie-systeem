package domain;

import code.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApp extends Application {
    public static void main(String[] args) throws IOException {
        Klas.procesKlas();
        Gebruiker.setAllUsers();
        College.procesCollege();
        RoosterRegel.procesRooster();
        Afmelding.procesAfmeldingen();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(LoginApp.class.getClassLoader().getResource("domain/login.fxml"));
        Parent root = loader.load();

        stage.setTitle("RoosterApp");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
