package code;

import code.Afmelding;
import code.College;
import code.Gebruiker;
import code.PresentieStatus;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import static code.Klas.getAllKlassen;

public class Student extends Gebruiker{
    private int studentNr;
    private Klas klas;
    private PresentieStatus presentie;

    private ArrayList<Afmelding> afmeldingen = new ArrayList<>();
    private ArrayList<ZiekMelding> ziekMeldingen = new ArrayList<>();
    private ZiekMelding ziekMelding = new ZiekMelding(this);

    private static int nextStudNr = 1;
    private static ArrayList<Student> studenten = new ArrayList<>();

    public static void addStudent(Student student){studenten.add(student);}

    public Student(String voornaam, String achternaam, String klasStr, String wachtwoord){
        super(voornaam, achternaam, wachtwoord);
        for(Klas klas : getAllKlassen()){
            if(klas.getKlasNaam().equals(klasStr)){this.klas = klas;}
        }

        this.studentNr = nextStudNr;

        nextStudNr++;
        presentie = PresentieStatus.Present;
    }

    public Student(String voornaam, String achternaam, Klas klas, String wachtwoord){
        super(voornaam, achternaam, wachtwoord);
        this.klas = klas;
        this.studentNr = nextStudNr;

        nextStudNr++;
        presentie = PresentieStatus.Present;
    }

//    public Student(String voornaam, Klas klas){
//        super(voornaam, achternaam, wachtwoord);
//        this.klas = klas;
//        this.studentNr = nextStudNr;
//
//        nextStudNr++;
//        presentie = PresentieStatus.Present;
//    }

    public static ArrayList<Student> getAllStudents(){
        return studenten; }

    public int getStudentNr(){
        return studentNr;
    }
    public Klas getKlas(){
        return klas;
    }
    public PresentieStatus getPresentie(){return presentie;}

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

    public void getAfmeldingen(){
        if(afmeldingen.isEmpty()){
            System.out.println( super.getNaam() + " heeft zich nog nooit afgemeld!");
        }else{
            for(Afmelding afmelding : afmeldingen){
                String output = afmelding.toString();
                System.out.println(output);
            }
        }
    }

    public void setPresentie(PresentieStatus ps){
        this.presentie = ps;
    }

    public void setHuidigeZiekMelding(ZiekMelding ziekMelding){this.ziekMelding = ziekMelding;}

    public void getZiekMeldingen(){
        if(presentie == PresentieStatus.Ziek){
            System.out.println(String.format("%s is vanaf %s ziek en is nog steeds ziek.\n", super.getNaam(), ziekMelding.getBeginDatum()));
        }else if(ziekMeldingen.isEmpty()){System.out.println( super.getNaam() + " heeft zich nog nooit ziekgemeld!\n");}
        else{
            for(ZiekMelding ziekmelding : ziekMeldingen){
                String output = ziekmelding.toString();
                System.out.println(output);
            }
        }
    }

    public void voegAfmeldingToe(College college){
        Afmelding afmelding = new Afmelding(college, this);
        if(!afmeldingen.contains(afmelding)){afmeldingen.add(afmelding);}
        else{System.out.println("Je bent al afgemeld voor college" + college);}
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
