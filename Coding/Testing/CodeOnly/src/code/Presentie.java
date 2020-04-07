package code;

import java.time.LocalDate;

public class Presentie {
    Student student;
    PresentieStatus presentieStatus;

    public Presentie(Student student, PresentieStatus presentieStatus){
        this.student = student;
        this.presentieStatus = presentieStatus;
    }

    public String toString(){
        return String.format("%s %s", student, presentieStatus);
    }
}
