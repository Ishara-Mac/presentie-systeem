package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginController {
    File file = new File("");//bestand locatie van LoginTest.txt 
    Scanner scan = new Scanner(file);

    @FXML
    private TextField loginGebruikersnaam;

    @FXML
    private PasswordField loginWacthwoord;

    @FXML
    private Label txtLabel;

    public void Login(ActionEvent event) throws Exception {
        String gebruikersnaam = "";
        String wachtwoord = "";
        boolean found = false;
        scan.useDelimiter("[,\n]");
        gebruikersnaam = scan.next();
        wachtwoord = scan.next();

        while(scan.hasNext() && !found){
            if(gebruikersnaam.trim().equals(loginGebruikersnaam.getText()) && wachtwoord.trim().equals(loginWacthwoord.getText())) {
                found = true;
                txtLabel.setText("Correcte login!");
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Hoofdscherm.fxml"));
                primaryStage.setTitle("Hoofdscherm");
                primaryStage.setScene(new Scene(root, 300, 300));
                primaryStage.show();
            }else{
                found = false;
                txtLabel.setText("Fout! incorrecte login");
            }
        }
    }

    public void Reset(ActionEvent event){
        loginGebruikersnaam.setText(null);
        loginWacthwoord.setText(null);
    }

    public MainController() throws FileNotFoundException {
    }

}
