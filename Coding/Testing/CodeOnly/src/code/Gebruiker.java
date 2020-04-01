package code;

import java.util.ArrayList;

public class Gebruiker{
    private String naam;
    private String wachwoord;
    private String acces;
    public Gebruiker(String username,String password,String acces) {

    }

    public Gebruiker(String naam){
        this.naam = naam;
    }

    public String getNaam(){return naam;}

    public ArrayList<RoosterRegel> procesRooster(){
        System.out.println("Methode niet gemaakt in subklasse!");
        return null;
    }
}
