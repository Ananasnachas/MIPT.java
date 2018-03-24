package fifteenPuzzles.time;

import fifteenPuzzles.file.FileUtils;

public class TimeUtils {

    public boolean isNewTimeBetter(int seconds){
        String[] records = new FileUtils().getRecords();
        return seconds < parse(records[0]) || seconds < parse(records[1]) || seconds < parse(records[2]);
    }

    private int parse(String rec){
        char[] c = rec.toCharArray();
        return (c[0] - '0') * 600 + (c[1] - '0') * 60 + (c[3] - '0') * 10 + (c[4] - '0');
    }

    public String toString(int mins, int seconds){
        return  String.format("%02d:%02d",  mins % 60, seconds);
    }
}
