package userInterfaceLaag;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class aangevenVanAanwezigheidController implements Initializable {

    @FXML
    private Label informationBar;

    @FXML
    private Button absentButton;

    @FXML
    private Button presentButton;

    @FXML
    private Button allesBekijkenButton;

    @FXML
    private DatePicker absentDate;

    @FXML
    private DatePicker presentDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        informationBar.setTextAlignment(TextAlignment.RIGHT);
        setHuidigeDatum();
    }

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
    }
}


