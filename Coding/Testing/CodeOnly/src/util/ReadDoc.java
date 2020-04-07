//package util;
//
//import code.Docent;
//import code.Gebruiker;
//import code.Klas;
//import code.Student;
//import com.sun.prism.impl.shape.MarlinRasterizer;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.TreeMap;
//
//public class ReadDoc {
//
//    private static
//
//    public static ArrayList<Gebruiker> readShadow() {
//        ArrayList<Gebruiker> readshad=new ArrayList<>();
////        String doc="shadow";
////        Path pdoc=Path.of(doc);
////        TreeMap<String,String> accounts= new TreeMap<>();
//        try {
//            FileReader reader = new FileReader("Coding/Testing/CodeOnly/src/textfiles/Studenten");
//            BufferedReader bufferedReader = new BufferedReader(reader);
//            String line;
//
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] arrOfStr = line.split(" : ");
//                String voornaam = arrOfStr[0];
//                String achternaam = arrOfStr[1];
//                Gebruiker gebr = null;
//                // if ... then create new student
//                String password = arrOfStr[2];
//                if (arrOfStr.length == 4){
//                    String huidigeKlas = arrOfStr[3];
//                    Klas klas = null;
//                    for(Klas thisKlas : Klas.getAllKlassen()){
//                        if(thisKlas.getKlasNaam().equals( huidigeKlas )){
//                            klas = thisKlas;
//                        }
//                    }
//
//                    if(voornaam != null && achternaam != null && klas!= null && password != null){
//                        gebr = new Student(voornaam, achternaam, klas, password);
//                        klas.addStudent((Student) gebr);
//                    }
//                }else{
//                    if(voornaam != null && achternaam != null && password != null){
//                        gebr= new Docent(voornaam, achternaam, password);
//                        Docent.addDocent((Docent) gebr);
//                    }
//                }
//                readshad.add(gebr);
//            }
//
////            while(text!=null){
////                String[] lijst=text.split(" : ");
////
////                Gebruiker gebr=new Gebruiker(lijst[0],lijst[1],lijst[2],lijst[3]);
////                //accounts.put(lijst[0],lijst[1]);
////                readshad.add(gebr);
////                text=reader.readLine();
////                }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return readshad;
//    }
//
//
//}
