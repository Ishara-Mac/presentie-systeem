package code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static code.CollegeType.*;
import static code.TijdBlok.*;

public class KlasTest {
    public static void main(String[] args) throws IOException {
        Klas.procesKlas();
        College.procesCollege();
        RoosterRegel.procesRooster();

        System.out.println(Klas.getAllKlassen());
        System.out.println(College.getAllCollege());

        Rooster rooster = new Rooster();
        System.out.println(rooster.getRegels());
    }

    
//    public void stuff(){
//        Klas v1 = new Klas("v1a");
//        Klas v2 = new Klas("v1a");
//        LocalDate vandaag = LocalDate.now();
//
//        System.out.println(v1.getKlasNr() +"\n" + v2.getKlasNr());
//
//        Student henk = new Student("Henk");
//        Student harry = new Student("Harry");
//
//        v1.addStudent(henk);
//        v1.addStudent(harry);
//
//        System.out.println(v1);
//
//        College oop = new College("oop", werkCollege, v1);
//        College ooad = new College("ooad", werkCollege, v2);
//
//        RoosterRegel regel1 = new RoosterRegel(vandaag, oop, ochtendBlok);
//        RoosterRegel regel2 = new RoosterRegel(vandaag, oop, middagBlok);
//        RoosterRegel regel3 = new RoosterRegel(vandaag, ooad, ochtendBlok);
//
//        Rooster rooster = new Rooster();
//        System.out.println(rooster);
//    }
}
