package ru.job4j.filefinder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


public class FileVisitor extends SimpleFileVisitor<Path> {
    private final List<Path> files = new ArrayList<>();
    private String searchType;
    private String fileType;

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if ("mask".equals(searchType)) {
            if (file.getFileName().toString().endsWith(fileType)) {
                files.add(file);
            }
        }
        if ("name".equals(searchType)) {
            if (file.getFileName().toString().equals(fileType)) {
                files.add(file);
            }
        }
        if ("regex".equals(searchType)) {
            if (file.getFileName().toString().matches(fileType)) {
                files.add(file);
            }
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getList() {
        return files;
    }
}
