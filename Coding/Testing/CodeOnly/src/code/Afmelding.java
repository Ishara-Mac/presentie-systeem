package code;

public class Afmelding {
    private College les;
    private Student student;

    public Afmelding(College les, Student student){
        this.les = les;
        this.student = student;
    }

    public String toString(){
        return String.format("Van %s tijdens het %s %s was %s afwezig.\n", les.getTijd(), les.getType(), les.getNaam(), student.getNaam());
    }

}
