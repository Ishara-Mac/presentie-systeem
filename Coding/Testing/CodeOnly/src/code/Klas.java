package code;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Klas {
    private String klasNaam;
    private int klasNr;

    private ArrayList<Student> studenten = new ArrayList<>();

    private static int nextKlasNr = 1;
    private static ArrayList<Klas> klassen = new ArrayList<>();

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

    public void addStudent(Student student){studenten.add(student);}

    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append(String.format("In klas %s zitten %d studenten.\n", klasNaam, studenten.size()));
        for(Student student: studenten){
            output.append(student).append("\n");
        }
        return output.toString();
    }
}
