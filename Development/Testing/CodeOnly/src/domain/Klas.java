package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Klas {
    private String klasNaam;
    private int klasNr;

    private ArrayList<Student> studenten = new ArrayList<>();

    private static int nextKlasNr = 1;
    private static ArrayList<Klas> klassen = new ArrayList<>();

    /*
    Bij het aanmaken van een klas, wordt er een klasNaam en een klasNr gegeven.
    Ook wordt deze klas bij alle klassen toegevoegd en - doormiddel van de gegeven data in een tekstfile -
    worden de bijbehorende studenten toegevoegd.
     */
    public Klas(String klasNaam){
        this.klasNaam = klasNaam;
        this.klasNr = nextKlasNr;

        nextKlasNr++;
        klassen.add(this);
    }

    public String getKlasNaam(){return klasNaam;}
    public int getKlasNr(){return klasNr;}
    public ArrayList<Student> getStudenten(){return studenten;}
    public static ArrayList<Klas> getAllKlassen(){return klassen;}

    public static void procesKlas() throws IOException{
        FileReader reader = new FileReader("Development/Testing/CodeOnly/src/textfiles/Klassen");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            new Klas(line);
        }
        reader.close();
    }

    public void addStudent(Student student){studenten.add(student);}

    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append(String.format("In klas %s %d zitten %d studenten.\n", klasNaam, klasNr, studenten.size()));
        for(Student student: studenten){
            output.append(student).append("\n");
        }
        return output.toString();
    }
}
