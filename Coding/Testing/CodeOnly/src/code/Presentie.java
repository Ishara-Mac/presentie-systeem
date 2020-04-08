package code;


import java.util.ArrayList;

public class Presentie {
    private static ArrayList<Presentie> presenties;
    Student student;
    PresentieStatus presentieStatus;
    String reden;

    public Presentie(Student student, PresentieStatus presentieStatus){
        this.student = student;
        this.presentieStatus = presentieStatus;
        this.reden = "";
    }

    public Presentie(Student student, PresentieStatus presentieStatus, String reden){
        this.student = student;
        this.presentieStatus = presentieStatus;
        this.reden = reden;
    }

    public PresentieStatus getPresentieStatus(){return presentieStatus;}

    public String toString(){
        return String.format("%s %s %s", student, presentieStatus, reden);
    }
}
