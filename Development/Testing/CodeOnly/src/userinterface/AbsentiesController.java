package userinterface;

import domain.Rooster;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AbsentiesController {
    ObservableList<String> absentiesVanStudent = FXCollections.observableArrayList();

    @FXML private ListView<String> AbsentieLijst;

    public void initialize() throws FileNotFoundException {
        loadData();
    }

    private void loadData() throws FileNotFoundException {
        absentiesVanStudent.clear();
        File afwezigheid = new File("Development/Testing/CodeOnly/src/textfiles/Afmeldingen.txt");
        Scanner scan = new Scanner(afwezigheid);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.contains(Rooster.getCurrentUser().getNaam())) {
                absentiesVanStudent.add(line + " : afwezig");
            }
        }

        File absentieLijst = new File("Development/Testing/CodeOnly/src/textfiles/Ziekmeldingen.txt");
        Scanner scan2 = new Scanner(absentieLijst);

        while (scan2.hasNextLine()) {
            String line2 = scan2.nextLine();
            if (line2.contains(Rooster.getCurrentUser().getNaam())) {
                absentiesVanStudent.add(line2 + " : ziek" );
            }
        }
        AbsentieLijst.getItems().addAll(absentiesVanStudent);
    }
}
