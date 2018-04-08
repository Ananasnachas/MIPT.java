package main.java.puzzles.windows.gameWindow;

import main.java.puzzles.time.MyDuration;
import main.java.puzzles.windows.ErrorWindow;
import main.java.puzzles.windows.FinalWindow;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.Random;

public class GameField extends Pane{
    private Cell[] cells = new Cell[16];
    private int size;

    public GameField(int size){
        this.size = size;
        getChildren().add(gameContent());
    }

    private Parent gameContent(){
        makeStartConfiguration();
        move();
        return new Pane(cells);
    }

    private void makeStartConfiguration() {
        int[] numbers = new int[16];
        numbers = makeRandomArray(numbers);

        while(!isConfigurationSolvable(numbers))
            numbers = makeRandomArray(new int[16]);

        for (int i = 0; i < 16; i++) {
            try {
                cells[numbers[i]] = new Cell(numbers[i]+".png",i%4,i/4, numbers[i], size);
            } catch (FileNotFoundException e) {
                new ErrorWindow(e);
                throw new UncheckedIOException(e);
            }
        }
    }

    private int[] makeRandomArray(int[] numbers) {
        for (int i = 15; i > 0; i--) {

            int a = getRandom(i);
            int k = 1;

            for (int j = 0; j < 16; j++) {
                while (numbers[j] != 0)
                    j++;
                if (k == a) {
                    numbers[j] = i;
                    break;
                }
                k++;
            }
        }
        return numbers;
    }

    private int getRandom(int n){
        Random random = new Random();
        return random.nextInt((n))+1;
    }

    private boolean isConfigurationSolvable(int[] numbers){
        int n = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = i + 1; j < 16; j++) {
                if(numbers[i] > numbers[j] && numbers[j] != 0)
                    n++;
            }
        }
        return n % 2 == 0;
    }

    private void move() {
        for (Cell cell : cells) {
            cell.setOnMouseClicked(e -> {
                if (cell.canCellMove(cells[0]))
                    moveCells(cell, cells[0]);

                if (isConfigurationRight()) {
                    MyDuration.stop();
                    new FinalWindow(this);
                }
            });
        }
    }

    private void moveCells(Cell clicked, Cell empty) {
        if (clicked.getPositionX() == empty.getPositionX()) {
            int clickedPosY = clicked.getPositionY();

            for (int i = 1; i < 16; i++) {
                if (cells[i].getPositionX() == clicked.getPositionX())
                    if (isCellBetween(cells[i].getPositionY(), empty.getPositionY(), clickedPosY)) {

                        cells[i].setPositionY(moveCellToEmpty(cells[i].getPositionY(), empty.getPositionY()));
                        cells[i].setLocation();
                    }
            }
            empty.setPositionY(clickedPosY);
            empty.setLocation();
        }

        else if (clicked.getPositionY() == empty.getPositionY()) {
            int clickedPosX = clicked.getPositionX();

            for (int i = 1; i < 16; i++) {
                if (cells[i].getPositionY() == clicked.getPositionY())
                    if (isCellBetween(cells[i].getPositionX(), empty.getPositionX(), clickedPosX)) {

                        cells[i].setPositionX(moveCellToEmpty(cells[i].getPositionX(), empty.getPositionX()));
                        cells[i].setLocation();
                    }
            }
            empty.setPositionX(clickedPosX);
            empty.setLocation();
        }
    }

    private boolean isCellBetween(int cellPos, int emptyPos, int clickedPos){
        return (emptyPos < cellPos && cellPos <= clickedPos) || (clickedPos <= cellPos && cellPos < emptyPos);
    }

    private int moveCellToEmpty(int cellPos, int emptyPos){
        int distance = emptyPos - cellPos;
        return distance / Math.abs(distance) + cellPos;
    }

    private boolean isConfigurationRight(){
        for (int i = 1; i < 16; i++) {
            if((cells[i].getPositionX() + cells[i].getPositionY()*4 + 1) != cells[i].getNumber()){
                return false;
            }
        }
        return true;
    }
}
