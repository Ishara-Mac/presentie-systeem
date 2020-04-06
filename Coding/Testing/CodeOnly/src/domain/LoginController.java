package domain;

import code.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static code.ZiekMelding.*;

public class LoginController {
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Button confirm;
    @FXML
    private Button cancel;

    public void initialize() throws IOException {}

    public void closeLoginWindow() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void handleconfirm() throws IOException {
        String pass = password.getText();
        String usn = username.getText();
        boolean isLoginCorrect = false;

        ArrayList<Gebruiker> accounts = Gebruiker.getAllUsers();

        for (Gebruiker account : accounts) {
            String naam = account.getNaam();
            String email = account.getEmail();
            String ww = account.getWachtwoord();
            if ((email.equals(usn)) || (naam.equals(usn))) {
                if (ww.equals(pass)) {
                    System.out.println("yes, we're in.");
                    Rooster.setCurrentUser(account);
                    isLoginCorrect = true;
                    break;
                }else{
                    System.out.println("Wrong password");
                }
            }
        }
        if (isLoginCorrect) {
            openRoosterApp();
        }
    }

    public void openRoosterApp() throws IOException {
        Rooster rooster = new Rooster();
        Rooster.setRooster(rooster);

        closeLoginWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("rooster.fxml"));
        Parent root = loader.load();

        Stage ziekmeldStage = new Stage();
        ziekmeldStage.setScene(new Scene(root));
        ziekmeldStage.show();

        initialize();

    }
}
