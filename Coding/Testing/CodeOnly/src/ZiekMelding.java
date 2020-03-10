import java.text.SimpleDateFormat;
import java.util.Date;

public class ZiekMelding {
    private Date beginDatum = new Date();
    private Date eindDatum = new Date();
    private Student student;

    public ZiekMelding(Student student){
        this.student = student;
    }

    public void setBeginDatum(Date beginDatum){this.beginDatum = beginDatum;}
    public void setEindDatum(Date eindDatum){this.eindDatum = eindDatum;}

    public String getBeginDatum(){return formatDates(beginDatum);}

    public String toString(){
        return String.format("Van %s tot %s was %s ziek.\n",formatDates(beginDatum), formatDates(eindDatum), student.getNaam());
    }

    private String formatDates(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm");
        return formatter.format(date);
    }

}
