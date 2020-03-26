package code;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RoosterRegel {
    private LocalDate dag;
    private College college;
    private TijdBlok tijdBlok;
    private static ArrayList<RoosterRegel> regels = new ArrayList<>();

//    public RoosterRegel() {}

    public RoosterRegel(LocalDate dag, College college, TijdBlok tijdBlok) {
        this.dag = dag;
        this.college = college;
        this.tijdBlok = tijdBlok;
        regels.add(this);
    }

    public static ArrayList<RoosterRegel> getRegels() {
        return regels;
    }

    public LocalDate getDag(){return dag;}
    public College getCollege(){return college;}
    public TijdBlok getTijd(){return tijdBlok;}

    public void setDag(LocalDate dag){this.dag = dag;}
    public void setCollege(College college) {
        this.college = college;
    }
    public void setTijd(TijdBlok tijdBlok){this.tijdBlok = tijdBlok;}

    public String toString(){
        return String.format("Op %s van %s is %s %s",dag, tijdBlok, college.getType(), college);
    }
}
