package code;

import java.util.ArrayList;

public class Rooster {
    ArrayList<RoosterRegel> regels;

    public void fill(){
        regels.addAll(RoosterRegel.getRegels());


    }
}
