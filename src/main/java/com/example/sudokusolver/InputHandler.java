package com.example.sudokusolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class InputHandler {

    int[][] inputNumbers;

    public InputHandler(){
        inputNumbers = new int[9][9];
    }

    public void loadCSV(String file) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(file));
        int i = 0;

        inputNumbers = new int[9][9];

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            for (int j = 0; j < tokens.length; j++){
                if(!tokens[j].isBlank()) {
                    try {
                        inputNumbers[j][i] = Integer.parseInt(tokens[j].trim());
                    } catch (Exception e){
                        inputNumbers[j][i] = 0;
                    }
                } else{
                    inputNumbers[j][i] = 0;
                }
            }
            i ++;
        }
    }

    public int[][] getInputNumbers() {
        int[][] cloneNumbers = new int[9][9];
        for(int i=0; i<inputNumbers.length; i++)
            for(int j=0; j<inputNumbers[i].length; j++)
                cloneNumbers[i][j]=inputNumbers[i][j];
        return cloneNumbers;
    }

}
