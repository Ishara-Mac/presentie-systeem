package domain;

import code.Acces;
import code.Gebruiker;
import code.Rooster;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.ReadDoc;

import java.util.ArrayList;

public class LoginController {
    public TextField password;
    public TextField username;
    public Button confirm;
    public Button cancel;

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }


    public void handleconfirm(ActionEvent actionEvent) {
        String pass =password.getText();
        String usn = username.getText();
        ArrayList<Gebruiker> accounts= ReadDoc.readShadow();
        for (Gebruiker acces:accounts
             ) {if ((acces.getWachwoord()==pass)&(acces.getNaam()==usn)){
                 Rooster.setCurrentUser(new Gebruiker(acces.getNaam(),acces.getWachwoord(),acces.getAcces(),acces.getEmail()));
                 //acces.setLoggedin(true);
                 break;
        }

        }

    }
}
