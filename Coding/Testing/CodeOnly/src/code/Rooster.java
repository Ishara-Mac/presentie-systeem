package code;
        import java.util.ArrayList;

public class Rooster {
    private static Rooster rooster;

    public static void setRooster(Rooster hetrooster){rooster = hetrooster;}
    public static Rooster getRooster(){return rooster;}


    private ArrayList<RoosterRegel> regels = new ArrayList<>();
    public Rooster(){
        addRegels();
    }

    public void addRegels(){regels.addAll(RoosterRegel.getRegels());}
    public ArrayList<RoosterRegel> getRegels(){return regels;}


    public String toString(){return regels.toString();}
}
