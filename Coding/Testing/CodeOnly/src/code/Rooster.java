package code;

        import java.util.ArrayList;

public class Rooster {
    private ArrayList<RoosterRegel> regels = new ArrayList<>();

    public Rooster(){
        addRegels();
    }

    public void addRegels(){regels.addAll(RoosterRegel.getRegels());}
    public ArrayList<RoosterRegel> getRegels(){return regels;}

    public String toString(){
        StringBuilder output = new StringBuilder();
        for(RoosterRegel regel : regels){
            output.append(regel.toString()).append("\n");
        }
        return output.toString();
    }
}
