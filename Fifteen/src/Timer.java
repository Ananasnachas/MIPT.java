import javafx.scene.text.Text;

import java.time.*;

public class Timer implements Runnable{
    private Text text;
    private static LocalDateTime startTime;

    Timer(Text text){
        this.text = text;
        startTime = getTime();
    }

    @Override
    public void run() {
        Duration duration;
        int mins = 0;
        int seconds = 0;
        Time time = new Time();

        while (!Main.primaryStage.isShowing()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (!Main.wasConfigurationSolved && Main.primaryStage.isShowing()) {
            duration = Duration.between(startTime, getTime());
            mins = (int) duration.getSeconds() / 60;
            seconds = (int) duration.getSeconds() % 60;

            text.setText(new Time().toString(mins * 60 + seconds));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (Main.wasConfigurationSolved)
            if (time.isNewTimeBetter(mins * 60 + seconds))
                time.setRecords(mins * 60 + seconds);
    }

    private LocalDateTime getTime(){
        return LocalDateTime.now();
    }
}
