package domain;

import code.Rooster;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AbsentiesController {
    ObservableList<String> absentiesVanStudent = FXCollections.observableArrayList();

    @FXML
    private ListView<String> AbsentieLijst;

    public void initialize() throws FileNotFoundException {
        loadData();
    }

    private void loadData() throws FileNotFoundException {
        absentiesVanStudent.clear();
        File afwezigheid = new File("Coding/Testing/CodeOnly/src/textfiles/Afmeldingen.txt");
        Scanner scan = new Scanner(afwezigheid);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.contains(Rooster.getCurrentUser().getNaam())) {
                absentiesVanStudent.add(line + " : afwezig");
                System.out.println(line);
            }
        }

        File absentieLijst = new File("Coding/Testing/CodeOnly/src/textfiles/Ziekmeldingen.txt");
        Scanner scan2 = new Scanner(absentieLijst);

        while (scan2.hasNextLine()) {
            String line2 = scan2.nextLine();
            if (line2.contains(Rooster.getCurrentUser().getNaam())) {
                absentiesVanStudent.add(line2 + " : ziek" );
                System.out.println(line2);
            }
        }
        AbsentieLijst.getItems().addAll(absentiesVanStudent);
    }
}
