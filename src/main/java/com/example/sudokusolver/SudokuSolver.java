package com.example.sudokusolver;

import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SudokuSolver {

    int[][] numbers;

    public SudokuSolver(){
        numbers = new int[9][9];
    }

    public boolean solveGame(PaneHandler paneHandler) throws InterruptedException {

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (numbers[i][j] == 0){
                    for (int x = 1; x <= 9; x++){
                        numbers[i][j] = x;
                        if (isValid(numbers, i, j) && solveGame(paneHandler)){
                            return true;
                        }
                        numbers[i][j] = 0;
                    }

                    return false;
                }
            }
        }
        paneHandler.updatePane(Arrays.copyOf(numbers, numbers.length));
        return true;
    }

    private boolean isValid(int[][] numbers, int row, int column){
        if (!columnConstrained(numbers, column) &&
                !rowConstrained(numbers, row) &&
                !squareConstrained(numbers, row, column)){
            return true;
        } else{
            return false;
        }

    }

    private boolean rowConstrained(int[][] numbers, int row){

        int numDuplicates = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if ((numbers[row][i] == numbers[row][j]) && numbers[row][i] != 0) {
                    numDuplicates ++;
                }
            }
            if (numDuplicates > 1) {
                return true;
            }
            numDuplicates = 0;
        }
        return false;
    }

    private boolean columnConstrained(int[][] numbers, int column){

        int numDuplicates = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if ((numbers[i][column] == numbers[j][column]) && numbers[i][column] != 0) {
                    numDuplicates ++;
                }
            }
            if (numDuplicates > 1)
                return true;
            numDuplicates = 0;
        }
        return false;
    }

    private boolean squareConstrained(int[][] numbers, int row, int column){

        int subsectionRowStart = (row/3)*3;
        int subsectionColumnStart = (column/3)*3;
        int[] squareNumbers = new int[9];
        int squareNumberPlace = 0;

        for (int i = subsectionColumnStart; i < subsectionColumnStart+3; i++){
            for (int j = subsectionColumnStart; j < subsectionColumnStart+3; j++){
                squareNumbers[squareNumberPlace] = numbers[i][j];
                squareNumberPlace++;
            }
        }


        int numDuplicates = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if ((squareNumbers[i] == squareNumbers[j]) && squareNumbers[i] != 0) {
                    numDuplicates ++;
                }
            }
            if (numDuplicates > 1){
                return true;
            }
            numDuplicates = 0;
        }
        return false;
    }

    public int[][] getNumbers() {
        return numbers.clone();
    }

    public void setNumbers(int[][] inputNumbers) {
        for(int i=0; i<inputNumbers.length; i++)
            for(int j=0; j<inputNumbers[i].length; j++)
                numbers[i][j]=inputNumbers[i][j];
        //this.numbers = inputNumbers.clone();
    }
}
