package domain;

import code.PresentieStatus;
import code.Rooster;
import code.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ZiekMeldController implements Initializable {
    @FXML private Button confirmButton;
    @FXML private TextArea textField;
    @FXML private Button cancelButton;
    @FXML private Label informationLabel;

    private Student gebruiker = (Student) Rooster.getCurrentUser();
    private boolean studentIsZiek = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIsZiek();
        setText();
    }

    public void setIsZiek(){ if(gebruiker.getPresentie() == PresentieStatus.Ziek){ studentIsZiek = true; } }

    public void setText(){
        if(studentIsZiek){
            informationLabel.setText("Wil jij je betermelden?");
            textField.setText("Voel je jezelf weer beter en ben je van plan weer naar school te komen? Meld jezelf dan weer beter.");
            confirmButton.setText("Beter melden");
        }else{
            informationLabel.setText("Wil jij je ziekmelden?");
            textField.setText("Wanneer je niet lekker bent, kun je jezelf ziekmelden. Deze ziekmelding zal doorlopen totdat jij je weer beter meldt. Zo kunnen jouw vakdocenten bijhouden wanneer jij wel of niet aanwezig bent.");
            confirmButton.setText("Ziek melden");
        }
    }

    public void confirmButton() throws IOException {
        gebruiker.ziekMelden();
        System.out.println(gebruiker.getPresentie());
        backToRooster();
    }

    public void backToRooster() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}


