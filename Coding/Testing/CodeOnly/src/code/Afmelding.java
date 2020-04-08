package code;

import java.io.FileWriter;
import java.io.IOException;

public class Afmelding {
    private RoosterRegel regel;
    private Student student;
    private String reden;

    public Afmelding(RoosterRegel regel, Student student, String reden) throws IOException {
        this.regel = regel;
        this.student = student;
        this.reden = reden;

        writeAbsentie();
    }

    public void writeAbsentie() throws IOException {
        FileWriter fw = new FileWriter("Coding/Testing/CodeOnly/src/textfiles/Afmeldingen.txt", true);
        fw.write(String.format("%s : %s : %s : %s : %s : %s\r\n", student.getNaam(), student.getStudentNr(), regel.getDag(), regel.getCollege().getCode(), regel.getTijd().getBlok(), reden));
        fw.close();
    }

    public String toString(){
        return String.format("Van %s tijdens het %s was %s afwezig.\n", regel.getTijd(), regel.getCollege().getCode(), student.getNaam());
    }

}
