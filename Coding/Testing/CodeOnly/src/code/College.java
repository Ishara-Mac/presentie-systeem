package code;

public class College {
    private String naam;

    private TijdBlok tijdBlok;
    private CollegeType collegeType;


    public void setTijd(TijdBlok tijdBlok){this.tijdBlok = tijdBlok;}
    public void setType(CollegeType collegeType){this.collegeType = collegeType;}
    public void setNaam(String naam){this.naam = naam;}

    public String getTijd(){return tijdBlok.toString();}
    public CollegeType getType(){return collegeType;}
    public String getNaam(){return naam;}
}
