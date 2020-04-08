package domain;

import code.Presentie;
import code.PresentieStatus;
import code.Student;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AbsentiePopUpController {
    @FXML private Button confirmButton;
    @FXML private Label informationLabel;
    @FXML private TextArea redenTextArea;

    private Student student;

    public void initialize(Student student) {
        setLayout();
        this.student = student;
    }

    public void setLayout(){
        informationLabel.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(informationLabel, 0.0);
        AnchorPane.setRightAnchor(informationLabel, 0.0);
        informationLabel.setAlignment(Pos.CENTER);
    }

    public void confirmButton(){ backToRooster(); }

    public void backToRooster() {
        String reden = redenTextArea.getText();
        if(reden.isEmpty() || reden.equals(" ")){ reden = "Geen reden gegeven"; }
        new Presentie(student, PresentieStatus.Afwezig, reden);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
