package code;

public class College {
    private String collegenaam;

    private Docent docent;
    private Klas klas;
    private Lokaal lokaal;
    private CollegeType collegeType;

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

    public String toString(){
        return collegenaam;
    }
}
