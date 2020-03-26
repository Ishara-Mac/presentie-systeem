package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class College {
    private String collegenaam;

    private Docent docent;
    private Klas klas;
    private Lokaal lokaal;
    private CollegeType collegeType;

    private static ArrayList<College> allColleges = new ArrayList<>();

    public College(String collegenaam, CollegeType collegeType, Klas klas){
        this.collegenaam = collegenaam;
        this.collegeType = collegeType;
        this.klas = klas;
    }

    public College(String collegenaam, CollegeType collegeType, Docent docent, Klas klas, Lokaal lokaal){
        this.collegenaam = collegenaam;
        this.collegeType = collegeType;
        this.docent = docent;
        this.klas = klas;
        this.lokaal = lokaal;
    }

    public void setType(CollegeType collegeType){this.collegeType = collegeType;}
    public void setNaam(String collegenaam){this.collegenaam = collegenaam;}
    public void setLokaal(Lokaal lokaal){this.lokaal = lokaal;}
    public void setDocent(Docent docent){this.docent = docent;}
    public void setKlas(Klas klas){this.klas = klas;}

    public CollegeType getType(){return collegeType;}
    public String getNaam(){return collegenaam;}
    public Lokaal getLokaal(){return lokaal;}
    public Docent getDocent(){return docent;}
    public Klas getKlas(){return klas;}
    public static ArrayList<College> getAllCollege(){return allColleges;}

    public static void procesCollege() throws IOException {
        FileReader reader = new FileReader("Coding/Testing/CodeOnly/src/textfiles/Colleges");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arrOfStr = line.split(" : ", 3);
            CollegeType typeNieuw = null;
            Klas klasNieuw = null;

            for(CollegeType bestaandeType : CollegeType.values()){
                if(bestaandeType.toString().equals(arrOfStr[1])){
                    typeNieuw = bestaandeType;
                }
            }
            for(Klas bestaandeKlas : Klas.getAllKlassen()){
                if(bestaandeKlas.getKlasNaam().equals(arrOfStr[2])){
                    klasNieuw = bestaandeKlas;
                }
            }
            if( arrOfStr[0] != null && typeNieuw != null && klasNieuw!= null ){
                allColleges.add(new College(arrOfStr[0], typeNieuw, klasNieuw));
            }
        }
        reader.close();
    }

    public String toString(){return String.format("%s %s voor klas %s", collegeType, collegenaam, klas.getKlasNaam());}
}
