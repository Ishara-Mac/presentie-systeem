package domain;

import code.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    @FXML private PasswordField password;
    @FXML private TextField username;
    @FXML private Button cancel;
    @FXML private Label emailInvalid;
    @FXML private Label passwordInvalid;

    public void initialize(){ }

    public void handleconfirm() throws IOException {
        String pass = password.getText();
        String usn = username.getText();
        emailInvalid.setVisible(false);
        passwordInvalid.setVisible(false);
        ArrayList<Gebruiker> accounts = Gebruiker.getAllUsers();

        for (Gebruiker account : accounts) {
            String email = account.getEmail();
            String ww = account.getWachtwoord();

            if (email.equals(usn)) {
                if (ww.equals(pass)) {
                    Rooster.setCurrentUser(account);
                    openRoosterApp();
                }else{ passwordInvalid.setVisible(true); }
                break;
            }else{ emailInvalid.setVisible(true); }
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

    public void closeLoginWindow() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
