package fifteenPuzzles.file;

import fifteenPuzzles.time.TimeUtils;
import fifteenPuzzles.windows.ErrorWindow;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class FileUtils {
    private File file = new File("records.txt");

    public String[] getRecords(){
        Scanner scan;
        try {
            scan = new Scanner(new FileReader(file));
        } catch (FileNotFoundException e) {
            ErrorWindow ew = new ErrorWindow(e);
            throw new UncheckedIOException(e);
        }
        return new String[]{scan.nextLine(), scan.nextLine(), scan.nextLine()};
    }

    public void setRecords(int mins, int seconds) {
        String[] rec = getRecords();
        FileWriter writer = null;

        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            ErrorWindow ew = new ErrorWindow(e);
            throw new UncheckedIOException(e);
        }
        String[] records = new String[]{rec[0], rec[1], rec[2], new TimeUtils().toString(mins, seconds)};
        Arrays.sort(records);

        try {
            writer.write(records[0] + "\n");
            writer.write(records[1] + "\n");
            writer.write(records[2] + "\n");
            writer.close();
        } catch (IOException e) {
            ErrorWindow ew = new ErrorWindow(e);
            throw new UncheckedIOException(e);
        }
    }
}
