package main.java.puzzles.windows.gameWindow;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;

public class Cell extends ImageView{

    private int positionX;
    private int positionY;
    private int number;
    private int size;

    Cell(String imageUrl, int positionX, int positionY, int number, int size) throws FileNotFoundException {
        super(new Image(new FileInputStream(new File("src/main/resources/images/"+ imageUrl))));
        this.positionX = positionX;
        this.positionY = positionY;
        this.number = number;
        this.size = size;
        setFitHeight(size);
        setFitWidth(size);
        setLocation();
    }

    Cell(String imageUrl, int number, int size) throws FileNotFoundException {
        super(new Image(new FileInputStream(new File("src/main/resources/images/"+ imageUrl))));
        this.positionX = 0;
        this.positionY = 0;
        this.number = number;
        this.size = size;
        setFitHeight(size);
        setFitWidth(size);
        setLocation();
    }

    public boolean canCellMove(Cell empty){
        return (positionX == empty.getPositionX() || positionY == empty.getPositionY()) && number != 0;
    }

    public void setLocation(){
        setTranslateX(positionX * size);
        setTranslateY(positionY * size);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getNumber() {
        return number;
    }
}
