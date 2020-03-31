package code;

import java.time.LocalDate;

public class ZiekteMelding {
    private Student student;
    private LocalDate afDatum;
    private LocalDate terugDatum;

    public ZiekteMelding(Student student) {
        this.student = student;
        setAfDatum(LocalDate.now());
        setTerugDatum(afDatum.plusDays(7));
    }

    public void setAfDatum(LocalDate afDatum) {
        this.afDatum = afDatum;
    }

    public void setTerugDatum(LocalDate terugDatum) {
        this.terugDatum = terugDatum;
    }
}
