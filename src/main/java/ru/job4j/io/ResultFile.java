package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;


public class ResultFile {
    public static void main(String[] args) {
        Integer[][] matrix = new Integer[10][10];
for (int cell = 0; cell < matrix.length; cell++){
    for (int row = 0; row < matrix[cell].length; row++) {
        matrix[cell][row] = (cell + 1) * (row + 1);
    }
}

    try (FileOutputStream out = new FileOutputStream("result.txt")) {
        for (Integer[] matrix1: matrix) {

            out.write(Arrays.deepToString(matrix1).getBytes());
            out.write(System.lineSeparator().getBytes());
        }


        } catch (Exception e) {
            e.printStackTrace();
      }
    }
}
