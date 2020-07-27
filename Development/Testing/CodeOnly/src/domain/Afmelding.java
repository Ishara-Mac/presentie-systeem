package domain;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Afmelding {
    private RoosterRegel regel;
    private Student student;
    private String reden;

    private static String pathNameAbsences = "Development/Testing/CodeOnly/src/textfiles/Afmeldingen.txt";
    private static ArrayList<Afmelding> afmeldingen = new ArrayList<>();

    public Afmelding(RoosterRegel regel, Student student){
        this.regel = regel;
        this.student = student;
    }

    public Afmelding(RoosterRegel regel, Student student, String reden) {
        this.regel = regel;
        this.student = student;
        this.reden = reden;
    }

    public static ArrayList<Afmelding> getAfmeldingen(){return afmeldingen;}

    public static void procesAfmeldingen() throws IOException {
        FileReader reader = new FileReader(pathNameAbsences);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arrOfStr = line.split(" : ");
            Student currentStud = null;
            LocalDate huidigeDatum;
            TijdBlok tb = null;
            RoosterRegel rr = null;

            for(Student student : Student.getAllStudents()){ if(student.getStudentNr() == Integer.parseInt(arrOfStr[1])){ currentStud = student; break; } }

            try{ huidigeDatum = LocalDate.parse(arrOfStr[2]); }catch(IllegalArgumentException e){ huidigeDatum = null; }

            for(TijdBlok tijdBlok : TijdBlok.values()){ if(tijdBlok.getBlok().equals(arrOfStr[4])){ tb = tijdBlok; } }

            for(RoosterRegel regel : RoosterRegel.getRegels()){
                if(regel.getCollege().getCode().equals(arrOfStr[3])){
                    if(regel.equals(new RoosterRegel(huidigeDatum, regel.getCollege(), tb))){
                        rr = regel;
                    }
                }
            }

            if( huidigeDatum != null && currentStud != null && rr!= null){
                Afmelding am = new Afmelding(rr, currentStud ,arrOfStr[5]);
                afmeldingen.add(am);
            }
        }
        reader.close();

        File file = new File(pathNameAbsences);
        if(file.exists()){
            file.delete();
            file.createNewFile();
        }
        for(Afmelding am : getAfmeldingen()){
            FileWriter fw = new FileWriter(file, true);
            fw.write(String.format("%s : %s : %s : %s : %s : %s\r\n", am.student.getNaam(), am.student.getStudentNr(), am.regel.getDag(), am.regel.getCollege().getCode(), am.regel.getTijd().getBlok(), am.reden));
            fw.close();
        }

    }

    public void addAbsentie() throws IOException {
        afmeldingen.add(this);

        FileWriter fw = new FileWriter(pathNameAbsences, true);
        fw.write(String.format("%s : %s : %s : %s : %s : %s\r\n", student.getNaam(), student.getStudentNr(), regel.getDag(), regel.getCollege().getCode(), regel.getTijd().getBlok(), reden));
        fw.close();
    }

    public void removeAbsentie() throws IOException{
        editAfmeldingFile();
        afmeldingen.remove(this);
    }

    public void editAfmeldingFile()throws IOException{
        File inputFile = new File(pathNameAbsences);
        ArrayList<String> lines = new ArrayList<>();

        BufferedReader bReader = new BufferedReader(new FileReader(inputFile));

        String currentLine;
        String lineToRemove = String.format("%s : %s : %s : %s : %s : %s", student.getNaam(), student.getStudentNr(), regel.getDag(), regel.getCollege().getCode(), regel.getTijd().getBlok(), reden);

        while((currentLine = bReader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            if(currentLine.trim().equals(lineToRemove)) continue;
            lines.add(currentLine + System.getProperty("line.separator"));
        }
        bReader.close();

        BufferedWriter bWriter = new BufferedWriter(new FileWriter(inputFile));

        inputFile.delete();
        inputFile.createNewFile();

        for(String line : lines){
            bWriter.write(line);
        }

        bWriter.close();
    }

    public boolean equals(Object andereObject){
        boolean isGelijk = false;

        if(andereObject instanceof Afmelding){
            Afmelding ownedGame = (Afmelding) andereObject;
            if(this.regel == ownedGame.regel &&
                    this.student == ownedGame.student){
                isGelijk = true;
            }
        }
        return isGelijk;
    }

    public String toString(){
        return String.format("Van %s tijdens het %s was %s afwezig.\n", regel.getTijd(), regel.getCollege().getCode(), student.getNaam());
    }

}
