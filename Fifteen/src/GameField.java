import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;

public class GameField {
    private Cell[] cells = Main.cells;
    private int size = Main.size;

    Parent gameContent(){
        makeStartConfiguration();
        move();

        ImageView[] images = new ImageView[16];
        for (int i = 0; i < 16; i++) {
            images[i] = cells[i].image;
        }

        return new Pane(images);
    }

    private void makeStartConfiguration() {
        int[] numbers = new int[16];
        numbers = makeRandomArray(numbers);

        while(!isConfigurationSolvable(numbers))
            numbers = makeRandomArray(new int[16]);

        for (int i = 0; i < 16; i++) {
            cells[numbers[i]] = new Cell(numbers[i]+".png",i%4,i/4, numbers[i]);
            setLocation(cells[numbers[i]]);
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
        Stage finalStage = new Stage();

        for (Cell cell:cells) {

            cell.image.setOnMouseClicked(e -> {
                if (!finalStage.isShowing()) {

                    if (canCellMove(cell))
                        moveCells(cell, cells[0]);

                    if (isConfigurationRight()) {
                        Main.wasConfigurationSolved = true;
                        finalStage.setTitle("Want to play again?");
                        finalStage.setScene(new Scene(FinalStage.finalContent(finalStage), 250, 100));
                        finalStage.show();
                    }
                }
            });
        }
    }

    private boolean canCellMove(Cell cell){
        return (cell.positionX == cells[0].positionX || cell.positionY == cells[0].positionY) && cell.number != 0;
    }

    private void moveCells(Cell clicked, Cell empty) {
        if (clicked.positionX == empty.positionX) {
            int clickedPosY = clicked.positionY;

            for (int i = 1; i < 16; i++) {
                if (cells[i].positionX == clicked.positionX)
                    if (isCellBetween(cells[i].positionY, empty.positionY, clickedPosY)) {

                        cells[i].positionY = moveCellToEmpty(cells[i].positionY, empty.positionY);
                        setLocation(cells[i]);
                    }
            }
            empty.positionY = clickedPosY;
            setLocation(empty);
        }

        else if (clicked.positionY == empty.positionY) {
            int clickedPosX = clicked.positionX;

            for (int i = 1; i < 16; i++) {
                if (cells[i].positionY == clicked.positionY)
                    if (isCellBetween(cells[i].positionX, empty.positionX, clickedPosX)) {

                        cells[i].positionX = moveCellToEmpty(cells[i].positionX, empty.positionX);
                        setLocation(cells[i]);
                    }
            }
            empty.positionX = clickedPosX;
            setLocation(empty);
        }
    }

    private boolean isCellBetween(int cellPos, int emptyPos, int clickedPos){
        return (emptyPos < cellPos && cellPos <= clickedPos) || (clickedPos <= cellPos && cellPos < emptyPos);
    }

    private int moveCellToEmpty(int cellPos, int emptyPos){
        int distance = emptyPos - cellPos;
        return distance / Math.abs(distance) + cellPos;
    }

    private void setLocation(Cell cell){
        cell.image.setTranslateX(cell.positionX * size);
        cell.image.setTranslateY(cell.positionY * size);
    }

    private boolean isConfigurationRight(){
        for (int i = 1; i < 16; i++) {
            if((cells[i].positionX + cells[i].positionY*4 + 1) != cells[i].number){
                return false;
            }
        }
        return true;
    }

}
