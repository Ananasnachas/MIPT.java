package main.java.puzzles.time;

import main.java.puzzles.utils.FileUtils;
import main.java.puzzles.windows.ErrorWindow;
import javafx.scene.text.Text;

import java.time.*;

public class MyDuration implements Runnable {
    private Text text;
    private static LocalDateTime startTime;
    private static boolean wasStoped;

    public MyDuration(Text text){
        this.text = text;
        startTime = getTime();
        wasStoped = false;
    }

    public static void stop(){
        wasStoped = true;
    }

    @Override
    public void run() {

        Duration duration;
        int mins = 0;
        int seconds = 0;
        TimeUtils time = new TimeUtils();

        while(text.getScene() == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                new ErrorWindow(e);
                throw new RuntimeException(e);
            }
        }


        while (!wasStoped && text.getScene().getWindow().isShowing()) {
            duration = Duration.between(startTime, getTime());
            mins = (int) duration.getSeconds() / 60;
            seconds = (int) duration.getSeconds() % 60;

            text.setText(new TimeUtils().toString(mins, seconds));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                new ErrorWindow(e);
                throw new RuntimeException(e);
            }
        }

        if (wasStoped)
            if (time.isNewTimeBetter(mins * 60 + seconds))
                FileUtils.setRecords(mins,seconds);
    }

    private LocalDateTime getTime(){
        return LocalDateTime.now();
    }
}
