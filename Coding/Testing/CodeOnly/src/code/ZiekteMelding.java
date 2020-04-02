package code;

import java.time.LocalDate;

public class ZiekteMelding {
    private Gebruiker student;
    private LocalDate afDatum;
    private LocalDate terugDatum;

    public ZiekteMelding(Gebruiker student,LocalDate terugDatum) {
        this.student = student;
        setAfDatum(LocalDate.now());
        this.terugDatum=terugDatum;
    }

    public void setAfDatum(LocalDate afDatum) {
        this.afDatum = afDatum;
    }

    public void setTerugDatum(LocalDate terugDatum) {
        this.terugDatum = terugDatum;
    }
}
