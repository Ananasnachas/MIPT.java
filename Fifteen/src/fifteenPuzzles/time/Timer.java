package fifteenPuzzles.time;

import fifteenPuzzles.Main;
import fifteenPuzzles.file.FileUtils;
import fifteenPuzzles.windows.ErrorWindow;
import javafx.scene.text.Text;

import java.time.*;

public class Timer implements Runnable {
    private Text text;
    private static LocalDateTime startTime;
    private static boolean wasStoped;

    public Timer(Text text){
        this.text = text;
        startTime = getTime();
        wasStoped = false;
    }

    public static void stop(){
        wasStoped = true;
    }

    @Override
    public void run() {
        Main main = new Main();

        Duration duration;
        int mins = 0;
        int seconds = 0;
        TimeUtils time = new TimeUtils();

        while(!main.isPrimaryStageShowing()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                ErrorWindow ew = new ErrorWindow(e);
                throw new RuntimeException(e);
            }
        }

        while (!wasStoped && main.isPrimaryStageShowing()) {
            duration = Duration.between(startTime, getTime());
            mins = (int) duration.getSeconds() / 60;
            seconds = (int) duration.getSeconds() % 60;

            text.setText(new TimeUtils().toString(mins, seconds));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ErrorWindow ew = new ErrorWindow(e);
                throw new RuntimeException(e);
            }
        }

        if (wasStoped)
            if (time.isNewTimeBetter(mins * 60 + seconds))
                new FileUtils().setRecords(mins,seconds);
    }

    private LocalDateTime getTime(){
        return LocalDateTime.now();
    }
}
