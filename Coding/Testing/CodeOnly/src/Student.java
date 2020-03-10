import java.util.ArrayList;
import java.util.Date;

public class Student extends Gebruiker{
    private int studentNr;
    private String naam;
    private PresentieStatus presentie;
    private ArrayList<Afmelding> afmeldingen = new ArrayList<>();
    private ArrayList<ZiekMelding> ziekMeldingen = new ArrayList<>();
    private ZiekMelding ziekMelding = new ZiekMelding(this);

    public Student(int studentNr, String email, String wachtwoord, String naam){
        this.studentNr = studentNr;
        this.naam = naam;
        presentie = PresentieStatus.Present;
        //super(email, wachtwoord, naam);
    }

    public int getStudentNr(){
        return studentNr;
    }
    public String getNaam(){
        return naam;
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

    public void ziekMelden(Date date){
        if(presentie == PresentieStatus.Ziek){
            ziekMelding.setEindDatum(date);
            presentie = PresentieStatus.Present;
            ziekMeldingen.add(ziekMelding);
            ziekMelding = new ZiekMelding(this);
        }else{
            ziekMelding.setBeginDatum(date);
            presentie = PresentieStatus.Ziek;
        }
    }
}
