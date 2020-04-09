package domain;

import java.util.ArrayList;

public class Docent extends Gebruiker{
    private int docentNr;

    private static int nextDocNr = 1;
    private static ArrayList<Docent> docenten = new ArrayList<>();

    public static ArrayList<Docent> getDocenten(){return docenten;}
    public static void addDocent(Docent docent){docenten.add(docent);}

    private ArrayList<College> colleges = new ArrayList<>();

    public Docent(String voorletter, String achternaam, String wachtwoord){
        super(voorletter, achternaam, wachtwoord);

        docenten.add(this);
        this.docentNr = nextDocNr;
        nextDocNr++;
    }

    public void addCollege(College college){colleges.add((college));}
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
