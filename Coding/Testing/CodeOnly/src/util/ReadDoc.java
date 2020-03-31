package util;

import code.Acces;
import code.Gebruiker;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.TreeMap;

public class ReadDoc {


    public static ArrayList<Gebruiker> readShadow() {
        ArrayList<Gebruiker> readshad=new ArrayList<>();
        String doc="shadow";
        Path pdoc=Path.of(doc);
        TreeMap<String,String> accounts= new TreeMap<>();
        System.out.println(pdoc);
        try {
            BufferedReader reader= Files.newBufferedReader(pdoc);
            String text=reader.readLine();

            while(text!=null){
                System.out.println(text);
                String[] lijst=text.split(" : ");

                Gebruiker gebr=new Gebruiker(lijst[0],lijst[1],lijst[2],lijst[3]);
                //accounts.put(lijst[0],lijst[1]);
                readshad.add(gebr);
                text=reader.readLine();
                


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readshad;
    }


}
