package main.java.puzzles.utils;

import main.java.puzzles.time.TimeUtils;
import main.java.puzzles.windows.ErrorWindow;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public final class FileUtils {
    private static File file = new File("src/main/resources/records.txt");

    public static String[] getRecords(){
        try(Scanner scan = new Scanner(new FileReader(file))) {
            return new String[]{scan.nextLine(), scan.nextLine(), scan.nextLine()};
        } catch (FileNotFoundException e) {
            new ErrorWindow(e);
            throw new UncheckedIOException(e);
        }
    }

    public static void setRecords(int mins, int seconds) {
        String[] rec = getRecords();
        try(FileWriter writer = new FileWriter(file)) {

            String[] records = new String[]{rec[0], rec[1], rec[2], new TimeUtils().toString(mins, seconds)};
            Arrays.sort(records);
            writer.write(records[0] + "\n");
            writer.write(records[1] + "\n");
            writer.write(records[2] + "\n");
            writer.close();
        } catch (IOException e) {
            new ErrorWindow(e);
            throw new UncheckedIOException(e);
        }
    }
}
