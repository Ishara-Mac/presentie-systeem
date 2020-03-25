package util;

import code.Gebruiker;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;

public class ReadDoc {
    public static TreeMap<String,String> readShadow() {
        String doc="shadow";
        Path pdoc=Path.of(doc);
        TreeMap<String,String> accounts= new TreeMap<>();
        System.out.println(pdoc);
        try {
            BufferedReader reader= Files.newBufferedReader(pdoc);
            String text=reader.readLine();
            while(text!=null){
            String[] lijst=text.split(" : ");

                String username=lijst[0];
                String password=lijst[1];
                String acces=lijst[2];
                Gebruiker gebr=new Gebruiker(username,password);
                accounts.put(lijst[0],lijst[1]);
                text=reader.readLine();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }


}
