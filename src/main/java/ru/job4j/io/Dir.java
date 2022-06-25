package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        String catalog ="c:\\projects";
        File file = new File(catalog);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }


        System.out.printf("Catalog: %s size : %s kb%n",catalog, file.getTotalSpace());
        for (File subfile : file.listFiles()) {
            Double lengthFile = (double) subfile.length();
            String fil = subfile.getAbsoluteFile().toString().substring(catalog.length()+1);
            System.out.printf("Name: %s, size: %.2f kb%n",fil,lengthFile);
        }

    }
}
