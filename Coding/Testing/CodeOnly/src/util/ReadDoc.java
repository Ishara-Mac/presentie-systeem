package util;

import code.Gebruiker;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;

public class ReadDoc {
    public static TreeMap<Gebruiker,String> readShadow() {
        String doc="CodeOnly/src/shadow";
        Path pdoc=Path.of(doc);
        TreeMap<Gebruiker,String> accounts= new TreeMap<>();
        System.out.println(pdoc);
        try {
            BufferedReader reader= Files.newBufferedReader(pdoc);
            String text=reader.readLine();
            while(text!=null){
            String[] lijst=text.split(" : ");
                    accounts.put(new Gebruiker(lijst[0],lijst[1]),lijst[2]);
                    text=reader.readLine();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }


}
