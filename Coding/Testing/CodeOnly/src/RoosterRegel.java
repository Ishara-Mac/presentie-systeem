import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RoosterRegel {
    private LocalDate dag;
    private ArrayList<RoosterRegelDeel> colleges;
    private static ArrayList<RoosterRegel> regels;

    public RoosterRegel(LocalDate dag) {
        this.dag = dag;
        regels.add(this);
    }

    public static ArrayList<RoosterRegel> getRegels() {
        return regels;
    }

    public void setregel(College les, int beginUur, int beginMinuut, int eindUur, int eindMinuut){
       boolean check=true;
       try{
        LocalDateTime begin=dag.atTime(beginUur,beginMinuut);
        LocalDateTime eind=dag.atTime(eindUur,eindMinuut);
           if(begin.isBefore(eind)){
               RoosterRegelDeel regel=new RoosterRegelDeel(begin,eind,les);
               colleges.add(regel);}
       }
       catch (Exception e){
           e.printStackTrace();
       }
    }



}
