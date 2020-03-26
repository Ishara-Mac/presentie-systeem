package code;

import java.sql.Time;

public enum TijdBlok {
    ochtendBlok("Ochtendblok",new Time(8, 30, 0),new Time(11, 30, 0)),
    lunchBlok("Lunchblok",new Time(11, 30, 0),new Time(12, 30, 0)),
    middagBlok("Middagblok",new Time(12, 30, 0),new Time(15, 30, 0));

    private final String bloknaam;
    private final Time beginTijd;
    private final Time eindTijd;

    TijdBlok(String bloknaam, Time beginTijd, Time eindTijd) {
        this.bloknaam = bloknaam;
        this.beginTijd = beginTijd;
        this.eindTijd= eindTijd;
    }

    public Time getBeginTijd(Time time){return this.beginTijd;}
    public Time getEindTijd(Time time){return this.eindTijd;}
    public String getBlok(){return bloknaam;}

    public String toString(){
        return String.format("%s tot %s", this.beginTijd.toString(), this.eindTijd.toString());
    }
}
