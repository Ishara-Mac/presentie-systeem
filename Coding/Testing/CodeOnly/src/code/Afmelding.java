package code;

import java.io.FileWriter;
import java.io.IOException;

public class Afmelding {
    private RoosterRegel regel;
    private Student student;

    public Afmelding(RoosterRegel regel, Student student) throws IOException {
        this.regel = regel;
        this.student = student;

        writeAbsentie();
    }

    public void writeAbsentie() throws IOException {
        FileWriter writer=new FileWriter("Coding/Testing/CodeOnly/src/textfiles/Absenties.txt");
        writer.write(String.format("%s : %s : %s : %s : %s", student.getNaam(), student.getStudentNr(), regel.getDag(), regel.getCollege(), regel.getTijd()));
    }

    public String toString(){
        return String.format("Van %s tijdens het %s was %s afwezig.\n", regel.getTijd(), regel.getCollege(), student.getNaam());
    }

}
