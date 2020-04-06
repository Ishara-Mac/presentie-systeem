package domain;

import code.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.Locale;

import static code.ZiekMelding.readingZiekMeldingen;

public class RoosterController {
    @FXML private Button ziekmeldKnop;

    @FXML private ListView<Student> listCurrentCollege = new ListView<>();
    @FXML private ListView<RoosterRegel> thisDay = new ListView<>();
    @FXML private DatePicker huidigeDatum;

    @FXML private Label maandagLabel;
    @FXML private Label dinsdagLabel;
    @FXML private Label woensdagLabel;
    @FXML private Label donderdagLabel;
    @FXML private Label vrijdagLabel;

    @FXML private ListView<RoosterRegel> maandagListview = new ListView<>();
    @FXML private ListView<RoosterRegel> dinsdagListview = new ListView<>();
    @FXML private ListView<RoosterRegel> woensdagListview = new ListView<>();
    @FXML private ListView<RoosterRegel> donderdagListview = new ListView<>();
    @FXML private ListView<RoosterRegel> vrijdagListview = new ListView<>();

    private Rooster rooster = Rooster.getRooster();
    private Student gebruiker;
    private ArrayList<ListView<RoosterRegel>> weekdagen = new ArrayList<>();
    private ArrayList<Label> weekDagLabels = new ArrayList<>();
    private boolean dagIsVisible = true;
    private boolean isUserDocent;

    LocalDate vandaag = LocalDate.now();
    String dayOfWeek;

    public void initialize() throws IOException {
        readingZiekMeldingen();

        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.valueOf(vandaag));

        dayOfWeek = vandaag.getDayOfWeek().toString();

        huidigeDatum.setValue(vandaag);
        setWeekdagen();

        if(Rooster.getCurrentUser() instanceof Docent){
            isUserDocent = true;
        }else if(Rooster.getCurrentUser() instanceof Student){
            gebruiker = (Student) Rooster.getCurrentUser();
            isUserDocent = false;
            setZiekMeldKnop();
        }
        setUpLayout();
    }

    public void setUpLayout(){
        listCurrentCollege.setVisible(isUserDocent);
        if(isUserDocent){
            thisDay.setPrefWidth(550);
        }else{
            thisDay.setPrefWidth(1120);
        }
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

    public void toonVorigeDag() {
        if(dagIsVisible){
            huidigeDatum.setValue( huidigeDatum.getValue().minusDays(1));
            setDag();
        }
        else{
            huidigeDatum.setValue( huidigeDatum.getValue().minusDays(7));
            setWeek();
        }
    }

    public void toonVandaag() {
        huidigeDatum.setValue(vandaag);
        if(dagIsVisible){ setDag(); }else{ setWeek(); }
    }

    public void toonVolgendeDag() {
        if(dagIsVisible){
            huidigeDatum.setValue( huidigeDatum.getValue().plusDays(1));
            setDag();
        }
        else{
            huidigeDatum.setValue( huidigeDatum.getValue().plusDays(7));
            setWeek();
        }
    }

    public void toonDag() {
        dagVisible(true);
        setDag();
    }

    public void toonWeek() {
        dagVisible(false);
        setWeek();
    }

    public void setCollege(){
        RoosterRegel thisRegel = thisDay.getSelectionModel().getSelectedItem();
        ObservableList<Student> alleStudenten = FXCollections.observableArrayList();
        System.out.println(thisRegel.getCollege().getKlas());
        for(Student student : thisRegel.getCollege().getKlas().getStudenten()){
            PresentieStatus ps = student.getPresentie();
            if(ps == PresentieStatus.Ziek){
                alleStudenten.add(0, student);
            }else{alleStudenten.add(student); }
            System.out.println(ps);
        }
        listCurrentCollege.setItems(alleStudenten);
    }

    public void setDag(){
        ObservableList<RoosterRegel> regels = FXCollections.observableArrayList();
        ArrayList<RoosterRegel> alleRegels = rooster.getRegels();
        LocalDate huidigeDag = huidigeDatum.getValue();

        for(RoosterRegel regel : alleRegels){
            LocalDate dagCollege = regel.getDag();
            if(dagCollege.compareTo(huidigeDag) == 0){
                regels.add(regel);
            }
        }
        thisDay.setItems(regels);
    }

    public void setWeek(){
        for(int i = 1; i < 6; i++ ){
            ObservableList<RoosterRegel> regels = FXCollections.observableArrayList();
            ArrayList<RoosterRegel> alleRegels = rooster.getRegels();
            LocalDate huidigeDag = huidigeDatum.getValue().with(WeekFields.of(Locale.FRANCE).dayOfWeek(), i);

            for(RoosterRegel regel : alleRegels){
                LocalDate dagCollege = regel.getDag();
                if(dagCollege.compareTo(huidigeDag) == 0){
                    regels.add(regel);
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
        listCurrentCollege.setVisible(isDagVisible);
        for(ListView<RoosterRegel> dag : weekdagen){
            dag.setVisible(!isDagVisible);
        }
        for(Label dag : weekDagLabels){
            dag.setVisible(!isDagVisible);
        }
    }

    public void setZiekMeldKnop(){
        ziekmeldKnop.setVisible(true);
        if(gebruiker.getPresentie() == PresentieStatus.Ziek){
            ziekmeldKnop.setText("Beter melden");
        }
        else{ ziekmeldKnop.setText("Ziek Melden"); }
    }

    public void toonZiekMeldScherm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ziekMelden.fxml"));
        Parent root = loader.load();

        Stage ziekmeldStage = new Stage();
        ziekmeldStage.setTitle(ziekmeldKnop.getText().toLowerCase());
        ziekmeldStage.setScene(new Scene(root));
        ziekmeldStage.initModality(Modality.APPLICATION_MODAL);
        ziekmeldStage.showAndWait();

        initialize();
    }

    public void logOut() throws IOException {
        Stage stage = (Stage) ziekmeldKnop.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root));
        loginStage.show();

        initialize();
    }


//    gebruiker.ziekMelden(Date.valueOf( LocalDate.now()));
//    setZiekMeldKnop();
//    gebruiker.getZiekMeldingen();
}
