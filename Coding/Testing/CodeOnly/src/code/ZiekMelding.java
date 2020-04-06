package code;

import java.io.*;
import java.time.LocalDate;

public class ZiekMelding {
    private LocalDate beginDatum;
    private LocalDate eindDatum = null;
    private Student student;

    public ZiekMelding(Student student){
        this.student = student;
        this.beginDatum = LocalDate.now();
    }

    public ZiekMelding(Student student, LocalDate beginDatum){
        this.student = student;
        this.beginDatum = beginDatum;
    }

    //public void setBeginDatum(){this.beginDatum = beginDatum;}
    public void setEindDatum(){this.eindDatum = LocalDate.now();}

    public String getBeginDatum(){return beginDatum.toString();}

    public static void readingZiekMeldingen() throws IOException{
        FileReader reader = new FileReader("Coding/Testing/CodeOnly/src/textfiles/ZiekMeldingen.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] arrOfStr = line.split(" : ");
            LocalDate startDatum = LocalDate.parse(arrOfStr[2]);
            Student currentStudent = null;
            int studentNr = Integer.parseInt(arrOfStr[1]);

            // if ... then create new student

            for(Student student : Student.getAllStudents()){
                if(studentNr == student.getStudentNr()){
                    currentStudent = student;
                }
            }
            System.out.println(currentStudent);

            if(arrOfStr.length < 4){
                System.out.println("hey");
                if(currentStudent != null){
                    currentStudent.setPresentie(PresentieStatus.Ziek);
                    currentStudent.setHuidigeZiekMelding(new ZiekMelding(currentStudent, startDatum));
                }
            }
//            else{
//                String beterDatum = arrOfStr[3];
//            }
        }
    }

    public void writingZiekmeldingen() throws IOException {
        File file = new File("Coding\\Testing\\CodeOnly\\src\\textfiles\\ZiekMeldingen.txt");
        FileWriter fw = new FileWriter(file, true);
        if(eindDatum == null){
            fw.append(String.format("%s : %d : %s : ", student.getNaam(), student.getStudentNr(),beginDatum));
        }else{
            fw.append(String.format("%s\r\n", eindDatum));
        }


        fw.close();
    }

    public String toString(){
            return String.format("Van %s tot %s was %s ziek.\n", beginDatum, eindDatum, student.getNaam());
    }

}
