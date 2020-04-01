package code;

import java.util.ArrayList;

public class Gebruiker{
    private String naam;
    private String wachwoord;
    private String acces;
    
    public Gebruiker(String username,String password,String acces) {}

    private String email;
    public Gebruiker(String username,String password,String acces,String email) {
        this.wachwoord=password;
        this.acces=acces;
        this.naam=username;
        this.email=email;
    }

    public Gebruiker(String naam){
        this.naam = naam;
    }

    public String getNaam(){return naam;}

    public ArrayList<RoosterRegel> procesRooster(){
        System.out.println("Methode niet gemaakt in subklasse!");
        return null;
    }

    public String getWachwoord() {
        return wachwoord;
    }

    public String getAcces() {
        return acces;
    }

    public String getEmail() {
        return email;
    }
    public String toString(){
        return String.format("%s : %s : %s : %s" ,naam,wachwoord,acces,email);
    }
}
