package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Gebruiker{
    private static ArrayList<Gebruiker> allUsers = new ArrayList<>();

    private String naam;
    private String wachtwoord;
    private String email = "hu.nl";

    public Gebruiker(String voornaam, String achternaam, String wachtwoord){
        this.naam = voornaam + " " + achternaam;
        if(this instanceof Student){
            email = "student." + email;
        }
        this.email = String.format("%s.%s@%s", voornaam, achternaam, email);
        this.wachtwoord = wachtwoord;
    }

    public static void setAllUsers() {
        try {
            FileReader reader = new FileReader("Development/Testing/CodeOnly/src/textfiles/InlogGegevens");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] arrOfStr = line.split(" : ");
                String voornaam = arrOfStr[0];
                String achternaam = arrOfStr[1];
                Gebruiker gebr = null;
                // if ... then create new student
                String password = arrOfStr[2];
                if (arrOfStr.length == 4){
                    String huidigeKlas = arrOfStr[3];
                    Klas klas = null;

                    for(Klas thisKlas : Klas.getAllKlassen()){
                        if(thisKlas.getKlasNaam().equals( huidigeKlas )){
                            klas = thisKlas;
                        }
                    }
                    if(voornaam != null && achternaam != null && klas!= null && password != null){
                        gebr = new Student(voornaam, achternaam, klas, password);
                        klas.addStudent((Student) gebr);
                        Student.addStudent((Student) gebr);
                    }
                }else{
                    if(voornaam != null && achternaam != null && password != null){
                        gebr= new Docent(voornaam, achternaam, password);
                        Docent.addDocent((Docent) gebr);
                    }
                }
                allUsers.add(gebr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Gebruiker> getAllUsers(){ return allUsers; }

    public String getNaam(){return naam;}
    public String getWachtwoord() { return wachtwoord; }
    public String getEmail() { return email; }

    public ArrayList<RoosterRegel> procesRooster(){ System.out.println("Methode niet gemaakt in subklasse!"); return null; }

    public String toString(){
        return String.format("%s : : %s : %s" ,naam,wachtwoord,email);
    }
}
