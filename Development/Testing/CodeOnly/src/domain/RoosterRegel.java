package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class RoosterRegel {
    private LocalDate dag;
    private College college;
    private TijdBlok tijdBlok;
    private static ArrayList<RoosterRegel> regels = new ArrayList<>();

    public RoosterRegel(LocalDate dag, College college, TijdBlok tijdBlok) {
        this.dag = dag;
        this.college = college;
        this.tijdBlok = tijdBlok;
    }

    public static ArrayList<RoosterRegel> getRegels() {
        return regels;
    }

    public LocalDate getDag(){return dag;}
    public College getCollege(){return college;}
    public TijdBlok getTijd(){return tijdBlok;}

    public static void procesRooster() throws IOException {
        FileReader reader = new FileReader("Development/Testing/CodeOnly/src/textfiles/RoosterRegel");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arrOfStr = line.split(" : ", 3);
            LocalDate huidigeDatum;
            College huidigCollege = null;
            TijdBlok huidigeTijd = null;

            try{
                huidigeDatum = LocalDate.parse(arrOfStr[0]);
            }catch(IllegalArgumentException e){
                huidigeDatum = null;
            }
            for(College college : College.getAllCollege()){
                if(college.getCode().equals(arrOfStr[1])){
                    huidigCollege = college;
                }
            }
            for(TijdBlok tijdBlok : TijdBlok.values()){
                if(tijdBlok.getBlok().equals(arrOfStr[2])){
                    huidigeTijd = tijdBlok;
                }
            }
            if( huidigeDatum != null && huidigeTijd != null && huidigCollege!= null ){
                regels.add(new RoosterRegel(huidigeDatum, huidigCollege, huidigeTijd));
            }
        }
        reader.close();
    }

    public boolean equals(Object andereObject){
        boolean isGelijk = false;

        if(andereObject instanceof RoosterRegel){
            RoosterRegel ownedGame = (RoosterRegel) andereObject;
            if(this.dag.compareTo(ownedGame.dag) == 0 &&
                    this.college.getCode().equals(ownedGame.college.getCode()) &&
                    this.tijdBlok.getBlok().equals(ownedGame.tijdBlok.getBlok())){
                isGelijk = true;
            }
        }

        return isGelijk;
    }

    public String toString(){
        return String.format("%s %s\n\n%s\n",dag, tijdBlok, college);}
}
