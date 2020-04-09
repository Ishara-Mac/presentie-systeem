package domain;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ZiekMelding {
    private LocalDate beginDatum;
    private LocalDate eindDatum = null;
    private Student student;

    private File file = new File("src/textfiles/ZiekMeldingen.txt");

    public ZiekMelding(Student student){
        this.student = student;
        this.beginDatum = LocalDate.now();
    }

    public ZiekMelding(Student student, LocalDate beginDatum){
        this.student = student;
        this.beginDatum = beginDatum;
    }

    public void setEindDatum(){this.eindDatum = LocalDate.now();}

    public static void readingZiekMeldingen() throws IOException{
        FileReader reader = new FileReader("src/textfiles/ZiekMeldingen.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        int studentNr;
        LocalDate startDatum;
        Student currentStudent;
        String[] arrOfStr;

        while ((line = bufferedReader.readLine()) != null) {
            arrOfStr = line.split(" : ");
            studentNr = Integer.parseInt(arrOfStr[1]);
            startDatum = LocalDate.parse(arrOfStr[2]);
            currentStudent = null;

            // if ... then create new student
            for(Student student : Student.getAllStudents()){
                if(studentNr == student.getStudentNr()){
                    currentStudent = student;
                }
            }

            if(arrOfStr.length < 4){
                if(currentStudent != null){
                    currentStudent.setPresentie(PresentieStatus.Ziek);
                    currentStudent.setHuidigeZiekMelding(new ZiekMelding(currentStudent, startDatum));
                }
            }
        }
    }

    /*
    controleert per lijn of de lijn bij de ingelogde student hoort en of de einddatum is ingevuld.
    wanneer deze niet is ingevuld, eindigt de lijn met de dag waar de student zich heeft betergemeld.
    wanneer er niets is veranderd, komt er simpel weg een nieuwe ziekmelding aan het einde van het tekst bestand.
     */
    public void verwerkZiekmelding() throws IOException {
        if(student.getPresentie() == PresentieStatus.Ziek){
            writingZiekmeldingen();
        }
        else{
            FileWriter fr = new FileWriter(file, true);
            fr.write(String.format("%s : %s : %s : \r\n", student.getNaam(), student.getStudentNr(), LocalDate.now()));
            fr.close();
        }
    }

    public void writingZiekmeldingen() throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        ArrayList<String> nieuweRegels = new ArrayList<>();
        String[] arrOfStr;
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            arrOfStr = line.split(" : ");
            //huidige lijn van student
            if(student.getStudentNr() == Integer.parseInt(arrOfStr[1])){
                try{
                    LocalDate.parse(arrOfStr[3]);
                }catch(Exception e){
                    line = String.format("%s : %s : %s : %s", arrOfStr[0], arrOfStr[1], arrOfStr[2], LocalDate.now());
                }
            }
            nieuweRegels.add(line);
        }
        reader.close();
        editNewFile(nieuweRegels);
    }

    private void editNewFile(ArrayList<String> regels) throws IOException {
        FileWriter fw = new FileWriter(file);

        for(String regel: regels){fw.write(regel + "\r\n");}
        fw.close();
    }

    public String toString(){
        return String.format("Van %s tot %s was %s ziek.\n", beginDatum, eindDatum, student.getNaam());
    }

}
