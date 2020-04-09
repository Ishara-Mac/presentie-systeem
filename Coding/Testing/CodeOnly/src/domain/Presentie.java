package domain;

public class Presentie {
    Student student;
    PresentieStatus presentieStatus;
    String reden;

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
