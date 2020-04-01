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
    private String naam;
    private Klas klas;
    private PresentieStatus presentie;

    private ArrayList<Afmelding> afmeldingen = new ArrayList<>();
    private ArrayList<ZiekMelding> ziekMeldingen = new ArrayList<>();
    private ZiekMelding ziekMelding = new ZiekMelding(this);

    private static int nextStudNr = 1;
    private static ArrayList<Student> studenten = new ArrayList<>();

    public Student(String naam, String klasStr){
        super(naam);
        for(Klas klas : getAllKlassen()){
            if(klas.getKlasNaam().equals(klasStr)){this.klas = klas;}
        }

        this.studentNr = nextStudNr;

        nextStudNr++;
        presentie = PresentieStatus.Present;
    }

    public Student(String naam, Klas klas){
        super(naam);
        this.klas = klas;
        this.studentNr = nextStudNr;

        nextStudNr++;
        presentie = PresentieStatus.Present;
    }

    public int getStudentNr(){
        return studentNr;
    }
    public String getNaam(){
        return naam;
    }

    public Klas getKlas(){
        return klas;
    }
    public ArrayList<Student> getAllStudents(){
        return studenten;
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
            System.out.println( naam + " heeft zich nog nooit afgemeld!");
        }else{
            for(Afmelding afmelding : afmeldingen){
                String output = afmelding.toString();
                System.out.println(output);
            }
        }
    }

    public void getZiekMeldingen(){
        if(presentie == PresentieStatus.Ziek){
            System.out.println(String.format("%s is vanaf %s ziek en is nog steeds ziek.\n", naam, ziekMelding.getBeginDatum()));
        }else if(ziekMeldingen.isEmpty()){System.out.println( naam + " heeft zich nog nooit ziekgemeld!\n");}
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
            //ziekMelding.setBeginDatum(date);
            ziekMelding.verwerkZiekmelding();
            presentie = PresentieStatus.Ziek;
        }
    }

    public String toString(){
        return String.format("Naam %s || Studentnr %d", naam, studentNr);
    }
}
