package code;

import util.ReadDoc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.TreeMap;

public class Run {
    public static void main(String[] args) {

//        LocalDateTime nu=LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        String na=nu.format(formatter);
//        System.out.println(nu);
//        System.out.println(na);
        TreeMap<Gebruiker,String> accounting= ReadDoc.readShadow();
        Collection<String> keys = accounting.values();
        for (String str:keys
             ) {System.out.println(str);

        }





        }



    }

