import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;

public class Cell {

    public int positionX = 0;
    public int positionY = 0;
    public ImageView image;
    public int number = 0;

    Cell(String imageUrl, int positionX, int positionY, int number) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.number = number;

        try {
            image = new ImageView( new Image(new FileInputStream(new File("images/"+ imageUrl))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        image.setFitHeight(Main.size);
        image.setFitWidth(Main.size);
    }
}
