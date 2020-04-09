package domain;

import java.util.ArrayList;

public class Rooster {
    private static Rooster rooster;
    private static Gebruiker huidigegebruiker;

    public Rooster(){ regels.addAll(huidigegebruiker.procesRooster()); }

    public static void setRooster(Rooster hetrooster){rooster = hetrooster;}
    public static void setCurrentUser(Gebruiker nieuwgebruiker){huidigegebruiker = nieuwgebruiker;}
    public static Gebruiker getCurrentUser(){return huidigegebruiker;}
    public static Rooster getRooster(){return rooster;}

    private ArrayList<RoosterRegel> regels = new ArrayList<>();

    public ArrayList<RoosterRegel> getRegels(){return regels;}


    public String toString(){return regels.toString();}
}