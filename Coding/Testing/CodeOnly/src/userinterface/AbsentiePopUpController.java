package userinterface;

import domain.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import static domain.Afmelding.getAfmeldingen;

public class AbsentiePopUpController {
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private Label informationLabel;
    @FXML private TextArea redenTextArea;

    private Student student = (Student) Rooster.getCurrentUser();
    private String labelText = String.format("Wil jij je afmelden voor college %s? Wat is je reden?", regel.getCollege().getNaam());
    private static RoosterRegel regel;


    public void initialize(){
        setValidationAfmelding();
        setLayout();
        setText();
    }

    public static void setRegel(RoosterRegel regel){AbsentiePopUpController.regel = regel;}

    public void setValidationAfmelding(){
        for(Afmelding afmelding : getAfmeldingen()){
            if(afmelding.equals(new Afmelding(regel, student))){
                labelText = String.format("Je hebt je al afgemeld voor college %s.", regel.getCollege().getNaam());
                cancelButton.setText("Ok");
                confirmButton.setVisible(false);
                redenTextArea.setVisible(false);
                break;
            }else{
                labelText = String.format("Wil jij je afmelden voor college %s? Wat is je reden?", regel.getCollege().getNaam());
            }
        }

        if(regel.getDag().compareTo(LocalDate.now()) < 0){
            labelText = "Je kant je niet afmelden voor een college in het verleden.";
            cancelButton.setText("Ok");
            confirmButton.setVisible(false);
            redenTextArea.setVisible(false);
        }
    }

    public void setText(){
        informationLabel.setText(labelText);
    }

    public void setLayout(){
        informationLabel.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(informationLabel, 0.0);
        AnchorPane.setRightAnchor(informationLabel, 0.0);
        informationLabel.setAlignment(Pos.CENTER);
    }

    public void confirmButton() throws IOException {
        String reden = redenTextArea.getText();
        if(reden.isEmpty() || reden.equals(" ")){ reden = "Geen reden gegeven"; }
        Afmelding am = new Afmelding(regel, student, reden);
        am.addAbsentie();
        backToRooster();
    }

    public void backToRooster(){
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
