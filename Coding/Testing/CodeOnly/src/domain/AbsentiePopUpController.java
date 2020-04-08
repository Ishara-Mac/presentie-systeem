package domain;

import code.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AbsentiePopUpController {
    @FXML private Button confirmButton;
    @FXML private Label informationLabel;
    @FXML private TextArea redenTextArea;

    private Student student = (Student) Rooster.getCurrentUser();
    private static RoosterRegel regel;

    public void initialize() {
        setLayout();
    }

    public static void setRegel(RoosterRegel regel){AbsentiePopUpController.regel = regel;}

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
        System.out.println(am);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
