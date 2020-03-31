package domain;

import code.Acces;
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
        ArrayList<Acces> accounts= ReadDoc.readShadow();
        for (Acces acces:accounts
             ) {if ((acces.getWachwoord()==pass)&(acces.getUsername()==usn)){
                 acces.setLoggedin(true);
                 break;
        }

        }

    }
}
