package code;

public class Afmelding {
    private College les;
    private RoosterRegel regel;
    private Student student;

    public Afmelding(College les, Student student){
        this.les = les;
        this.student = student;
    }

    public String toString(){
        return String.format("Van %s tijdens het %s %s was %s afwezig.\n", regel.getTijd(), les.getType(), les.getNaam(), student.getNaam());
    }

}
