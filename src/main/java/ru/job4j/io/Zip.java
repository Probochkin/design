package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path a : sources) {
                zip.putNextEntry(new ZipEntry(a.toString()));

                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(a.toString()))) {
                  zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void validate(ArgsName arguments) {
        if (!Files.exists(Path.of(arguments.get("d")))) {
            throw new IllegalArgumentException("The archived directory does not exist");
        }
        if (!arguments.get("e").startsWith(".")) {
            throw new IllegalArgumentException("The file type is specified incorrectly");
        }
        if (!arguments.get("o").endsWith(".zip")) {
        throw new IllegalArgumentException("The archive name is not specified correctly");
    }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
            ArgsName arguments = ArgsName.of(args);
            validate(arguments);
            File target = new File(arguments.get("o"));
            Path directory = Path.of(arguments.get("d"));
            String exclude = arguments.get("e");

        List<Path>  filter =  Search.search(directory, p -> !p.toFile().getName().endsWith(exclude));
        zip.packFiles(filter,target);
    }
}