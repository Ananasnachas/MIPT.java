package fifteenPuzzles.windows.gameWindow;

import fifteenPuzzles.file.FileUtils;
import fifteenPuzzles.time.Timer;
import fifteenPuzzles.windows.ErrorWindow;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.*;

public class InformationField extends Pane {
    private int size;

    public InformationField(int size) {
        this.size = size;
        getChildren().add(makeFill());
        getChildren().add(makeText());
    }

    private Parent makeFill() {
        ImageView image = null;

        try {
            image = new ImageView(new Image(new FileInputStream(new File("images/staticField.png"))));
        } catch (FileNotFoundException e) {
            ErrorWindow ew = new ErrorWindow(e);
            throw new UncheckedIOException(e);
        }
        image.setFitWidth(2 * size);
        image.setFitHeight(4 * size);
        image.setTranslateX(4 * size);
        image.setTranslateY(0);

        return new Pane(image);
    }

    private Parent makeText(){
        String[] rec = new FileUtils().getRecords();

        Text time = new Text(4.8 * size, size,"00:00");
        Thread thread = new Thread(new Timer(time));
        thread.start();
        setTextStile(time);

        Text topText = new Text(4.5 * size,size / 2,"Your time:");
        setTextStile(topText);

        Text bottomText = new Text(4.6 * size,size * 2,"Records:");
        setTextStile(bottomText);

        Text records = new Text(4.8 * size,size * 2.5,rec[0] + "\n\n" + rec[1] + "\n\n" + rec[2]);
        setTextStile(records);

        return new Pane(topText, time, bottomText, records);
    }

    private void setTextStile(Text text){
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, size/4));
        text.setFill(Color.BLUE);
    }
}
