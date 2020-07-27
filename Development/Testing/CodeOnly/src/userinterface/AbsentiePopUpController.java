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
    @FXML private Button confirmAbsenceButton;
    @FXML private Button cancelAbsenceButton;
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
        boolean passOnAbsence = false;
        if(regel.getDag().compareTo(LocalDate.now()) < 0){
            labelText = "Je kant je niet afmelden voor een college in het verleden.";
            cancelAbsenceButton.setVisible(false);
            confirmAbsenceButton.setVisible(false);
            redenTextArea.setVisible(false);
        }else{
            if(getAfmeldingen().isEmpty()){
                labelText = String.format("Wil jij je afmelden voor college %s? Wat is je reden?", regel.getCollege().getNaam());
                passOnAbsence = true;
            }else{
                for(Afmelding afmelding : getAfmeldingen()){
                    if(afmelding.equals(new Afmelding(regel, student))){
                        labelText = String.format("Je hebt je al afgemeld voor college %s. Wil jij jouw afmelding annuleren?", regel.getCollege().getNaam());
                        passOnAbsence = false;
                    break;
                    }else{
                        labelText = String.format("Wil jij je afmelden voor college %s? Wat is je reden?", regel.getCollege().getNaam());
                        passOnAbsence = true;
                    }
                }
            }
            cancelAbsenceButton.setVisible(!passOnAbsence);
            confirmAbsenceButton.setVisible(passOnAbsence);
            redenTextArea.setVisible(passOnAbsence);
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

    public void confirmAbsenceButton() throws IOException {
        String reden = redenTextArea.getText();
        if(reden.isEmpty() || reden.equals(" ")){ reden = "Geen reden gegeven"; }
        Afmelding afmelding = new Afmelding(regel, student, reden);
        afmelding.addAbsentie();

        System.out.println(String.format("Afgemeld voor college %s.", regel.getCollege()));

        backToRooster();
    }

    public void cancelAbsenceButton() throws IOException{
        boolean errorNotFound = true;
        for(Afmelding tempAfmelding : Afmelding.getAfmeldingen()){
            if(tempAfmelding.equals(new Afmelding(regel, student))){
                tempAfmelding.removeAbsentie();
                errorNotFound = false;
                break;
            }
        }
        if(errorNotFound){
            System.out.println("Couldn't find Afmelding.");
        }else{
            System.out.println(String.format("Afmelding voor %s geanulleerd.", regel.toString()));
        }

        backToRooster();
    }

    public void backToRooster(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
