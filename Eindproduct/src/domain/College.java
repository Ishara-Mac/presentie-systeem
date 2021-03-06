package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class College {
    private CollegeType collegeType;
    private Docent docent;
    private Klas klas;

    private String collegeNaam;
    private String collegeCode;

    private static ArrayList<College> allColleges = new ArrayList<>();

    public College(String collegenaam, String collegeCode, CollegeType collegeType, Klas klas){
        this.collegeNaam = collegenaam;
        this.collegeCode = collegeCode;
        this.collegeType = collegeType;
        this.klas = klas;
    }

    public College(String collegenaam, String collegeCode, CollegeType collegeType, Klas klas, Docent docent){
        this.collegeNaam = collegenaam;
        this.collegeCode = collegeCode;
        this.collegeType = collegeType;
        this.klas = klas;
        this.docent = docent;
    }

    public String getNaam(){return collegeNaam;}
    public String getCode(){return collegeCode;}
    public Klas getKlas(){return klas;}
    public Docent getDocent(){return docent;}
    public static ArrayList<College> getAllCollege(){return allColleges;}

    public static void procesCollege() throws IOException {
        FileReader reader = new FileReader("src/textfiles/Colleges");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arrOfStr = line.split(" : ");
            CollegeType typeNieuw = null;
            Klas klasNieuw = null;
            Docent docentnieuw = null;
            int docentInt = Integer.parseInt(arrOfStr[4]);

            for(CollegeType bestaandeType : CollegeType.values()){
                if(bestaandeType.toString().equals(arrOfStr[2])){ typeNieuw = bestaandeType; }
            }
            for(Klas bestaandeKlas : Klas.getAllKlassen()){
                if(bestaandeKlas.getKlasNaam().equals(arrOfStr[3])){ klasNieuw = bestaandeKlas; }
            }
            for(Docent docent: Docent.getDocenten() ){
                if(docentInt == docent.getDocentNr()){ docentnieuw = docent; }
            }
            if( arrOfStr[0] != null && typeNieuw != null && klasNieuw!= null && docentnieuw != null){
                College nieuwCollege = new College(arrOfStr[0], arrOfStr[1] + klasNieuw.getKlasNaam(), typeNieuw, klasNieuw, docentnieuw);
                allColleges.add(nieuwCollege);
                docentnieuw.addCollege(nieuwCollege);
            }else if(arrOfStr[0] != null && typeNieuw != null && klasNieuw!= null){
                allColleges.add(new College(arrOfStr[0], arrOfStr[1] + klasNieuw.getKlasNaam(), typeNieuw, klasNieuw));
            }
        }
        reader.close();
    }

    public String toString(){
        return String.format("%s   %s   %s",collegeNaam, klas.getKlasNaam(), collegeType);}
}
