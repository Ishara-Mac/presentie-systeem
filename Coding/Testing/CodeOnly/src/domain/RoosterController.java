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

import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.ImageObserver;
import java.sql.Array;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.Locale;

public class RoosterController {
    public Button ziekmeldKnop;
    @FXML private ListView<String> thisDay = new ListView<>();
    @FXML private DatePicker huidigeDatum;

    @FXML private Button prevDay;
    @FXML private Button today;
    @FXML private Button nextDay;

    @FXML private Label maandagLabel;
    @FXML private Label dinsdagLabel;
    @FXML private Label woensdagLabel;
    @FXML private Label donderdagLabel;
    @FXML private Label vrijdagLabel;

    @FXML private ListView<String> maandagListview = new ListView<>();
    @FXML private ListView<String> dinsdagListview = new ListView<>();
    @FXML private ListView<String> woensdagListview = new ListView<>();
    @FXML private ListView<String> donderdagListview = new ListView<>();
    @FXML private ListView<String> vrijdagListview = new ListView<>();

    private Rooster rooster = Rooster.getRooster();
    private ArrayList<ListView<String>> weekdagen = new ArrayList<>();
    private ArrayList<Label> weekDagLabels = new ArrayList<>();
    private boolean dagIsVisible = true;

    LocalDate vandaag = LocalDate.now();
    String dayOfWeek;

    public void initialize(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.valueOf(vandaag));
        int dezeWeek = cal.get(Calendar.WEEK_OF_YEAR);

        dayOfWeek = vandaag.getDayOfWeek().toString();

        huidigeDatum.setValue(vandaag);
        setWeekdagen();
        setDag();
    }

    public void setWeekdagen(){
        weekDagLabels.add(maandagLabel);
        weekDagLabels.add(dinsdagLabel);
        weekDagLabels.add(woensdagLabel);
        weekDagLabels.add(donderdagLabel);
        weekDagLabels.add(vrijdagLabel);

        weekdagen.add(maandagListview);
        weekdagen.add(dinsdagListview);
        weekdagen.add(woensdagListview);
        weekdagen.add(donderdagListview);
        weekdagen.add(vrijdagListview);
    }

    public void toonVorigeDag(ActionEvent actionEvent) {
        if(dagIsVisible){
            huidigeDatum.setValue( huidigeDatum.getValue().minusDays(1));
            setDag();
        }
        else{
            huidigeDatum.setValue( huidigeDatum.getValue().minusDays(7));
            setWeek();
        }
    }

    public void toonVandaag(ActionEvent actionEvent) {
        huidigeDatum.setValue(vandaag);
        if(dagIsVisible){ setDag(); }else{ setWeek(); }
    }

    public void toonVolgendeDag(ActionEvent actionEvent) {
        if(dagIsVisible){
            huidigeDatum.setValue( huidigeDatum.getValue().plusDays(1));
            setDag();
        }
        else{
            huidigeDatum.setValue( huidigeDatum.getValue().plusDays(7));
            setWeek();
        }
    }

    public void toonDag(ActionEvent actionEvent) {
        dagVisible(true);
        setDag();
    }

    public void toonWeek(ActionEvent actionEvent) {
        dagVisible(false);
        setWeek();
    }

    public void setDag(){
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

    public void setWeek(){
        for(int i = 1; i < 6; i++ ){
            ObservableList<String> regels = FXCollections.observableArrayList();
            ArrayList<RoosterRegel> alleRegels = rooster.getRegels();
            LocalDate huidigeDag = huidigeDatum.getValue().with(WeekFields.of(Locale.FRANCE).dayOfWeek(), i);

            for(RoosterRegel regel : alleRegels){
                LocalDate dagCollege = regel.getDag();
                if(dagCollege.compareTo(huidigeDag) == 0){
                    System.out.println(regel);
                    regels.add(regel.toString());
                }
            }

            switch(i){
                case 1:
                    maandagListview.setItems(regels);
                    break;
                case 2:
                    dinsdagListview.setItems(regels);
                    break;
                case 3:
                    woensdagListview.setItems(regels);
                    break;
                case 4:
                    donderdagListview.setItems(regels);
                    break;
                case 5:
                    vrijdagListview.setItems(regels);
                    break;
            }
        }
    }

    public void dagVisible(Boolean isDagVisible){
        dagIsVisible = isDagVisible;
        thisDay.setVisible(isDagVisible);
        for(ListView<String> dag : weekdagen){
            dag.setVisible(!isDagVisible);
        }
        for(Label dag : weekDagLabels){
            dag.setVisible(!isDagVisible);
        }
    }
}
