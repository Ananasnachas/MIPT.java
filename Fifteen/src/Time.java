import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Time {
    private File file = new File("records.txt");

    public boolean isNewTimeBetter(int sec){
        String[] rec = getRecords();
        return sec < parse(rec[0]) || sec < parse(rec[1]) || sec < parse(rec[2]);
    }

    private int parse(String rec){
        char[] c = rec.toCharArray();
        return (c[0] - '0') * 600 + (c[1] - '0') * 60 + (c[3] - '0') * 10 + (c[4] - '0');
    }

    public String[] getRecords(){
        Scanner scan = null;
        try {
            scan = new Scanner(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new String[]{scan.nextLine(), scan.nextLine(), scan.nextLine()};
    }

    void setRecords(int newTime) {
        String[] rec = getRecords();
        FileWriter writer = null;

        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] records = new String[]{rec[0], rec[1], rec[2], new Time().toString(newTime)};
        Arrays.sort(records);

        try {
            writer.write(records[0] + "\n");
            writer.write(records[1] + "\n");
            writer.write(records[2] + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString(int sec){
        StringBuilder sb = new StringBuilder();
        sb.append(sec / 600);
        sb.append((sec % 600) / 60);
        sb.append(':');
        sb.append((sec % 60) / 10);
        sb.append(sec % 10);
        return new String(sb);
    }
}
