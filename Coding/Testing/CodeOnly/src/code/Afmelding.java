package code;

import javax.xml.stream.util.XMLEventAllocator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Afmelding {
    private RoosterRegel regel;
    private Student student;
    private String reden;
    private static ArrayList<Afmelding> afmeldingen = new ArrayList<>();

    public Afmelding(RoosterRegel regel, Student student){
        this.regel = regel;
        this.student = student;
    }

    public Afmelding(RoosterRegel regel, Student student, String reden) throws IOException {
        this.regel = regel;
        this.student = student;
        this.reden = reden;
    }

    public static ArrayList<Afmelding> getAfmeldingen(){return afmeldingen;}

    public static void procesAfmeldingen() throws IOException {
        FileReader reader = new FileReader("Coding/Testing/CodeOnly/src/textfiles/Afmeldingen.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arrOfStr = line.split(" : ");
            Student currentStud = null;
            //roosterregel
            LocalDate huidigeDatum;
            College currentcollege = null;
            TijdBlok tb = null;
            RoosterRegel rr = null;

            for(Student student : Student.getAllStudents()){ if(student.getStudentNr() == Integer.parseInt(arrOfStr[1])){ currentStud = student; break; } }

            try{ huidigeDatum = LocalDate.parse(arrOfStr[2]); }catch(IllegalArgumentException e){ huidigeDatum = null; }

            for(RoosterRegel regel : RoosterRegel.getRegels()){ if(regel.getCollege().getCode().equals(arrOfStr[3])){ currentcollege = regel.getCollege(); break; } }

            for(TijdBlok tijdBlok : TijdBlok.values()){ if(tijdBlok.getBlok().equals(arrOfStr[4])){ tb = tijdBlok; } }

            for(RoosterRegel regel : RoosterRegel.getRegels()){
                if(regel.getCollege().getCode().equals(arrOfStr[3])){
                    if(regel.equals(new RoosterRegel(huidigeDatum, regel.getCollege(), tb))){
                        rr = regel;
                    }
                }
            }

            if( huidigeDatum != null && currentStud != null && rr!= null){
                Afmelding am = new Afmelding(rr, currentStud ,arrOfStr[5]);
                System.out.println(am);
            }
        }
        reader.close();
    }

    public void addAbsentie() throws IOException {
        afmeldingen.add(this);

        FileWriter fw = new FileWriter("Coding/Testing/CodeOnly/src/textfiles/Afmeldingen.txt", true);
        fw.write(String.format("%s : %s : %s : %s : %s : %s\r\n", student.getNaam(), student.getStudentNr(), regel.getDag(), regel.getCollege().getCode(), regel.getTijd().getBlok(), reden));
        fw.close();
    }

    public boolean equals(Object andereObject){
        boolean isGelijk = false;

        if(andereObject instanceof Afmelding){
            Afmelding ownedGame = (Afmelding) andereObject;
            if(this.regel == ownedGame.regel &&
                    this.student == ownedGame.student){
                isGelijk = true;
            }
        }

        return isGelijk;


    }

    public String toString(){
        return String.format("Van %s tijdens het %s was %s afwezig.\n", regel.getTijd(), regel.getCollege().getCode(), student.getNaam());
    }

}
