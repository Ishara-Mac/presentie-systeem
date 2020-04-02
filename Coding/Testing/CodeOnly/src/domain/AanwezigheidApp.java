package domain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AanwezigheidApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(AanwezigheidApp.class.getClassLoader().getResource("domain/aangevenVanAanwezigheid.fxml"));
        Parent root = loader.load();

        stage.setTitle("RoosterApp");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
