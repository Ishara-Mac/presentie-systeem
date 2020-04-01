package code;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ZiekMelding {
    private LocalDate beginDatum;
    private LocalDate eindDatum = null;
    private Student student;

    public ZiekMelding(Student student){
        this.student = student;
        this.beginDatum = LocalDate.now();
    }

    //public void setBeginDatum(){this.beginDatum = beginDatum;}
    public void setEindDatum(){this.eindDatum = LocalDate.now();}

    public String getBeginDatum(){return beginDatum.toString();}

    public void verwerkZiekmelding() throws IOException {
        File file = new File("Coding\\Testing\\CodeOnly\\src\\textfiles\\ZiekMeldingen.txt");
        String oldLine = "nog steeds ziek";
        FileWriter fw;
        StringBuilder buffer;
        String currentLine;
        BufferedReader br = new BufferedReader(new FileReader(file));

        buffer = new StringBuilder();
        while ((currentLine = br.readLine()) != null){ buffer.append(currentLine).append("\r\n"); }
        String fileContents = buffer.toString();
        br.close();

        if(fileContents.contains(oldLine)){
            fileContents = fileContents.replaceAll(oldLine, eindDatum.toString());
            fw = new FileWriter(file);
            fw.append(fileContents);
        }else{
            fw = new FileWriter(file, true);
            fw.append(String.format("%s : %d\n%s - nog steeds ziek\r\n", student.getNaam(), student.getStudentNr(),beginDatum));
        }
        fw.close();

    }

    public String toString(){
        return String.format("Van %s tot %s was %s ziek.\n",beginDatum, eindDatum, student.getNaam());
    }

}
