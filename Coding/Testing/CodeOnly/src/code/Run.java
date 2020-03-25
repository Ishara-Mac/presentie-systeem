package code;

import util.ReadDoc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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

        TreeMap<String,String> accounting= ReadDoc.readShadow();
        Collection<String> keys = accounting.keySet();
        for (String str:keys
             ) {System.out.println(str);}








        }



    }

