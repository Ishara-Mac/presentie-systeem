package userinterface;

import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import static domain.ZiekMelding.readingZiekMeldingen;

public class RoosterController {
    @FXML
    private Label loginNaam;
    @FXML
    private Button allAbsenties;
    @FXML
    private Button ziekmeldKnop;

    @FXML
    private ListView<Presentie> listCurrentCollege = new ListView<>();
    @FXML
    private ListView<RoosterRegel> thisDay = new ListView<>();
    @FXML
    private DatePicker huidigeDatum;
    @FXML
    private Label currentCollegeLabel;

    @FXML
    private Label maandagLabel;
    @FXML
    private Label dinsdagLabel;
    @FXML
    private Label woensdagLabel;
    @FXML
    private Label donderdagLabel;
    @FXML
    private Label vrijdagLabel;

    @FXML
    private ListView<RoosterRegel> maandagListview = new ListView<>();
    @FXML
    private ListView<RoosterRegel> dinsdagListview = new ListView<>();
    @FXML
    private ListView<RoosterRegel> woensdagListview = new ListView<>();
    @FXML
    private ListView<RoosterRegel> donderdagListview = new ListView<>();
    @FXML
    private ListView<RoosterRegel> vrijdagListview = new ListView<>();

    private Rooster rooster = Rooster.getRooster();
    private Student gebruiker;
    private ArrayList<ListView<RoosterRegel>> weekdagen = new ArrayList<>();
    private ArrayList<Label> weekDagLabels = new ArrayList<>();
    private boolean dagIsVisible;
    private boolean isUserDocent;

    LocalDate vandaag = LocalDate.now();

    public void initialize() throws IOException {
        readingZiekMeldingen();
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
        loginNaam.setText(String.format("Momenteel ingelogd als: %s", Rooster.getCurrentUser().getNaam()));
    }

    public void setUpLayout(){
        listCurrentCollege.setVisible(isUserDocent);
        currentCollegeLabel.setVisible(isUserDocent);
        allAbsenties.setVisible(!isUserDocent);
        if(isUserDocent){
            thisDay.setPrefWidth(550);
            toonDag();
        }else{
            thisDay.setPrefWidth(1120);
            toonWeek();
        }
    }

    public void dagVisible(Boolean isDagVisible){
        dagIsVisible = isDagVisible;
        thisDay.setVisible(isDagVisible);
        listCurrentCollege.setVisible(isDagVisible && isUserDocent);
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

    public void toonDag() {
        dagVisible(true);
        setDag();
    }

    public void toonWeek() {
        dagVisible(false);
        setWeek();
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

    public void setCollege() throws IOException {
        // valt de huidige geselecteerde dag tussen een ziekmelding van een leerling?
        RoosterRegel thisRegel = thisDay.getSelectionModel().getSelectedItem();
        ObservableList<Student> alleStudenten = FXCollections.observableArrayList();
        ObservableList<Presentie> aanwezigheidStudenten = FXCollections.observableArrayList();
        //krijgt alle studenten van de klas
        alleStudenten.addAll(thisRegel.getCollege().getKlas().getStudenten());

        for(Student student : alleStudenten) { aanwezigheidStudenten.add(getPresentie(student)); }

        listCurrentCollege.setItems(aanwezigheidStudenten);
        currentCollegeLabel.setText( thisRegel.getCollege() + " " + thisRegel);
    }

    /*
    zit student huidige in klas?
    valt thisDay onder de begin- en einddatum ziektemelding. Zo ja, new Presentie(student, PresentieStatus.Ziek)
    zo nee, new Presentie(student, PresentieStatus.Present)*/
    public Presentie getPresentie(Student student) throws IOException {
        Presentie ps = checkZiekmelding(student);
        if(ps.getPresentieStatus() == PresentieStatus.Present){
            ps = checkAfmelding(student);
            return ps;
        }
        return ps;
    }

    public Presentie checkZiekmelding(Student student) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/textfiles/ZiekMeldingen.txt"));
        LocalDate thisDayDate = thisDay.getSelectionModel().getSelectedItem().getDag();
        String line; LocalDate eindDatum;

        while ((line = bufferedReader.readLine()) != null) {
            String[] arrOfStr = line.split(" : ");
            try { eindDatum = LocalDate.parse(arrOfStr[3]); } catch(Exception e) { eindDatum = LocalDate.now().plusDays(1); }

            if(student.getStudentNr() == Integer.parseInt(arrOfStr[1])){
                if(thisDayDate.compareTo(LocalDate.parse(arrOfStr[2])) >= 0){
                    if(thisDayDate.compareTo(eindDatum) <= 0){
                        return new Presentie(student, PresentieStatus.Ziek, "Ziek");
                    }
                }
            }
        }
        return new Presentie(student, PresentieStatus.Present, "Aanwezig.");
    }

    public Presentie checkAfmelding(Student student) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/textfiles/Afmeldingen.txt"));
        LocalDate thisDayRegel = thisDay.getSelectionModel().getSelectedItem().getDag();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] arrOfStr = line.split(" : ");
            if(student.getStudentNr() == Integer.parseInt(arrOfStr[1])){
                if(thisDayRegel.compareTo(LocalDate.parse(arrOfStr[2])) == 0){
                    return new Presentie(student, PresentieStatus.Afwezig, arrOfStr[5]);
                }
            }
        }
        return new Presentie(student, PresentieStatus.Present, "Aanwezig.");
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

    public void handleAbsentie() throws IOException {
        RoosterRegel regel;
        for(ListView<RoosterRegel> lw : weekdagen){
            regel= lw.getSelectionModel().getSelectedItem();
            if(regel != null){
                AbsentiePopUpController.setRegel(regel);
                lw.getSelectionModel().clearSelection();
                break;
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AbsentiePopUp.fxml"));
        Parent root = loader.load();

        Stage loginStage = new Stage();
        loginStage.setTitle("Afmelden.");
        loginStage.setScene(new Scene(root));
        loginStage.show();

        initialize();
    }

    public void toonAbsentieScherm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Absenties.fxml"));
        Parent root = loader.load();

        Stage absentieScherm = new Stage();
        absentieScherm.setTitle("Alle Absenties.");
        absentieScherm.setScene(new Scene(root));
        absentieScherm.initModality(Modality.APPLICATION_MODAL);
        absentieScherm.show();

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
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.show();

        initialize();
    }
}
