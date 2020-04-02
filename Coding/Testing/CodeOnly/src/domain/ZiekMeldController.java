package domain;

import code.PresentieStatus;
import code.Rooster;
import code.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ZiekMeldController implements Initializable {

    @FXML private Button confirmButton;
    @FXML private TextArea textField;
    @FXML private Button cancelButton;
    @FXML private Label informationLabel;
    /*
    @FXML private Button absentButton;
    @FXML private Button presentButton;
    @FXML private Button allesBekijkenButton;
    @FXML private DatePicker absentDate;
    @FXML private DatePicker presentDate;
    */

    private Student gebruiker = (Student) Rooster.getCurrentUser();
    private boolean studentIsZiek = false;
    private Date today = new Date();
    private String ziekmelden = "Ziek melden";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIsZiek();
//        informationBar.setTextAlignment(TextAlignment.LEFT);
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



    /*
    public void setHuidigeDatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        LocalDate nu = LocalDate.now();
        informationBar.setText("De datum is: " + String.valueOf(formatter.format((nu))));
    }

    public void getAbsentDateAction(javafx.event.ActionEvent event) {
//        Formatteren naar een nieuwe volgorde werkt nog niet
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        LocalDate date = absentDate.getValue();
        if (date != null) {
            informationBar.setText("Absent op: " + formatter.format(date));

        } else {
            informationBar.setText("Kies een datum");
        }

//        System.out.println(date);
    }

    public void getPresentDateAction(javafx.event.ActionEvent event) {
//        Formatteren naar een nieuwe volgorde werkt nog niet
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        LocalDate date = presentDate.getValue();
        if (date != null) {
            informationBar.setText("Present op: " + formatter.format(date));

        } else {
            informationBar.setText("Kies een datum");
        }

//        System.out.println(date);
    }*/
}


