package code;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class AbsentieMelding {
    private RoosterRegel regel;

    public AbsentieMelding(RoosterRegel regel) {
        this.regel = regel;
    }

    public void writeAbsentie() throws IOException {
        FileWriter writer=new FileWriter("absenties.txt");
        writer.write(regel.toString());


    }

}
