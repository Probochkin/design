package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;


public class Analizy {

    private final ArrayList <String> list = new ArrayList<>();
    private boolean statusServer = true;

    public void unavailable(String source, String target) {


        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
         read.lines().forEach(line -> {


             if ((line.startsWith("400") || line.startsWith("500")) && statusServer == true) {
                 int index = line.indexOf(" ");
                 list.add(line.substring(index + 1));
                 statusServer = false;
             }
             if ((line.startsWith("200") || line.startsWith("300")) && statusServer == false) {
                 int index = line.indexOf(" ");
                 list.add(line.substring(index + 1));
                 statusServer = true;
             }

           });

           try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
               for(int i=0; i < list.size(); i++) {
                   out.println(list.get(i) + ";" + list.get(i+1) + ";");
                   i++;
               }
           }

        } catch (Exception e) {
           e.printStackTrace();
        }



        }


    public static void main(String[] args) {
        String sourse = "./data/serverlog.properties";
        String target = "./data/unavailable.csv";
        new Analizy().unavailable(sourse, target);

    }
}