package domain;

import code.Rooster;
import code.RoosterRegel;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.awt.image.ImageObserver;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

public class RoosterController {
    @FXML private Label jaarNr;
    @FXML private Label maandNr;
    @FXML private Label weekNr;
    @FXML private ListView<String> thisDay = new ListView<>();
    @FXML private DatePicker huidigeDatum;

    @FXML private Button prevDay;
    @FXML private Button today;
    @FXML private Button nextDay;

    @FXML private ListView<String> maandag = new ListView<>();
    @FXML private ListView<String> dinsdag = new ListView<>();
    @FXML private ListView<String> woensdag = new ListView<>();
    @FXML private ListView<String> donderdag = new ListView<>();
    @FXML private ListView<String> vrijdag = new ListView<>();

    private Rooster rooster = Rooster.getRooster();

    LocalDate vandaag = LocalDate.now();
    String dayOfWeek;

    public void initialize(){
        jaarNr.setText(Integer.toString( vandaag.getYear() ));
        maandNr.setText(vandaag.getMonth().toString());

        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.valueOf(vandaag));
        int dezeWeek = cal.get(Calendar.WEEK_OF_YEAR);
        weekNr.setText(Integer.toString(dezeWeek));

        dayOfWeek = vandaag.getDayOfWeek().toString();

        huidigeDatum.setValue(vandaag);
        toonDag();
    }

    public void toonVorigeDag(ActionEvent actionEvent) {
        huidigeDatum.setValue( huidigeDatum.getValue().minusDays(1));
        toonDag();
    }

    public void toonVandaag(ActionEvent actionEvent) {
        huidigeDatum.setValue(vandaag);
        toonDag();
    }

    public void toonVolgendeDag(ActionEvent actionEvent) {
        huidigeDatum.setValue( huidigeDatum.getValue().plusDays(1));
        toonDag();
    }

    public void toonDag(){
        ObservableList<String> regels = FXCollections.observableArrayList();
        ArrayList<RoosterRegel> alleRegels = rooster.getRegels();
        LocalDate huidigeDag = huidigeDatum.getValue();

        for(RoosterRegel regel : alleRegels){
            LocalDate dagCollege = regel.getDag();
            if(dagCollege.compareTo(huidigeDag) == 0){
                System.out.println(regel);
                regels.add(regel.toString());
            }
        }
        thisDay.setItems(regels);
    }

//            int val = dagCollege.getDayOfWeek().getValue();
//            switch(val){
//                case 1:
//                    maandag.getItems().add(maandag.getItems().size(), regel.toString());
//                    break;
//                case 2:
//                    dinsdag.getItems().add(dinsdag.getItems().size(), regel.toString());
//                    break;
//                case 3:
//                    woensdag.getItems().add(woensdag.getItems().size(), regel.toString());
//                    break;
//                case 4:
//                    donderdag.getItems().add(donderdag.getItems().size(), regel.toString());
//                    break;
//                case 5:
//                    vrijdag.getItems().add(vrijdag.getItems().size(), regel.toString());
//                    break;
//            }
}
