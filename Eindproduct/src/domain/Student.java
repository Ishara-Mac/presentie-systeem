package domain;

import java.io.IOException;
import java.util.ArrayList;
 
public class Student extends Gebruiker{
    private int studentNr;
    private Klas klas;
    private PresentieStatus presentie;

    private ArrayList<ZiekMelding> ziekMeldingen = new ArrayList<>();
    private ZiekMelding ziekMelding = new ZiekMelding(this);

    private static int nextStudNr = 1;
    private static ArrayList<Student> studenten = new ArrayList<>();

    public static void addStudent(Student student){studenten.add(student);}

    public Student(String voornaam, String achternaam, Klas klas, String wachtwoord){
        super(voornaam, achternaam, wachtwoord);
        this.klas = klas;
        this.studentNr = nextStudNr;

        nextStudNr++;
        presentie = PresentieStatus.Present;
    }

    public static ArrayList<Student> getAllStudents(){ return studenten; }
    public int getStudentNr(){
        return studentNr;
    }
    public PresentieStatus getPresentie(){return presentie;}

    public void setPresentie(PresentieStatus ps){
        this.presentie = ps;
    }
    public void setHuidigeZiekMelding(ZiekMelding ziekMelding){this.ziekMelding = ziekMelding;}

    @Override
    public ArrayList<RoosterRegel> procesRooster(){
        ArrayList<RoosterRegel> regels = new ArrayList<>();

        for(RoosterRegel regel : RoosterRegel.getRegels()){
            if(regel.getCollege().getKlas().getKlasNr() == klas.getKlasNr()){
                regels.add(regel);
            }
        }
        return regels;
    }

    public void ziekMelden() throws IOException {
        if(presentie == PresentieStatus.Ziek){
            ziekMelding.setEindDatum();
            ziekMeldingen.add(ziekMelding);
            ziekMelding.verwerkZiekmelding();
            presentie = PresentieStatus.Present;
        }else{
            this.ziekMelding = new ZiekMelding(this);
            ziekMelding.verwerkZiekmelding();
            presentie = PresentieStatus.Ziek;
        }
    }

    public String toString(){
        return String.format("Naam %s || StudentenNr %d ", super.getNaam(), studentNr);
    }
}
