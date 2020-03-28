package code;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static code.Klas.getAllKlassen;

public class Docent extends Gebruiker{
    private int docentNr;

    private static int nextDocNr = 1;
    private static ArrayList<Docent> docenten = new ArrayList<>();

    public static ArrayList<Docent> getDocenten(){return docenten;}

    private ArrayList<College> colleges = new ArrayList<>();

    public Docent(String naam){
        super(naam);

        docenten.add(this);
        this.docentNr = nextDocNr;
        nextDocNr++;
    }

    public void addCollege(College college){colleges.add((college));}
    public ArrayList<College> getColleges(){return colleges;}
    public int getDocentNr(){return docentNr;}

    @Override
    public ArrayList<RoosterRegel> procesRooster(){
        ArrayList<RoosterRegel> regels = new ArrayList<>();
        for(RoosterRegel regel : RoosterRegel.getRegels()){
            if(colleges.contains(regel.getCollege())){
                regels.add(regel);
            }
        }
        return regels;
    }
}
