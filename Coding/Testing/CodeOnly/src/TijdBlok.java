import java.sql.Time;

public enum TijdBlok {
    ochtendBlok(new Time(8, 30, 0),new Time(11, 30, 0)),
    lunchBlok(new Time(11, 30, 0),new Time(12, 30, 0)),
    middagBlok(new Time(12, 30, 0),new Time(15, 30, 0));

    private final Time beginTijd;
    private final Time eindTijd;

    TijdBlok(Time beginTijd, Time eindTijd) {
        this.beginTijd = beginTijd;
        this.eindTijd= eindTijd;
    }

    public String toString(){
        String begin = "" + this.beginTijd;
        String eind = "" + this.beginTijd;

        return String.format("%s tot %s", begin, eind);
    }
}
