package com.example.sudokusolver;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PaneHandler {

    Pane pane;
    Double prefHeight, prefWidth, textGapX, textGapY, textBufferX, textBufferY;
    ArrayList<Line> lineList;
    Font inputFont, attemptFont;

    public PaneHandler() {

        prefWidth = 300.0;
        prefHeight = 300.0;
        textGapX = prefWidth/9;
        textGapY = prefHeight/9;
        textBufferX = 12.0;
        textBufferY = 20.0;

        inputFont = Font.font("Verdana", FontWeight.BOLD, 12);
        attemptFont = Font.font("Verdana", FontWeight.MEDIUM, 12);

        lineList = new ArrayList<>();

        pane = new Pane();
        pane.setPrefSize(prefWidth, prefHeight);
        //pane.setPadding(new Insets(10.0));
        prepareLines();
    }


    public Pane drawPane(int[][] inputNumbers, int[][] outputNumbers){

        pane.getChildren().removeAll();

        for (Line line:
             lineList) {
            pane.getChildren().add(line);
        }

        Font inputFont = Font.font("Verdana", FontWeight.BOLD, 12);
        Font attemptFont = Font.font("Verdana", FontWeight.MEDIUM, 12);

        drawNumbers(inputNumbers);

        System.out.println("drawn pane");
        return pane;

    }

    public void drawNumbers(int[][] numbers){

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int entry = numbers[i][j];
                if (entry != 0){
                    Text text = new Text(Integer.toString(entry));
                    text.setFont(inputFont);
                    text.setX(textGapX * i + textBufferX);
                    text.setY(textGapX * j + textBufferY);
                    pane.getChildren().add(text);
                }

            }
        }
    }

    public void updatePane(int[][] numbers){
        pane.getChildren().clear();
        for (Line line:
                lineList) {
            pane.getChildren().add(line);
        }
        drawNumbers(numbers);

    }

    public void prepareLines(){

        for (int i = 1; i <=8; i++) {
            double widthGap = prefWidth / 9;

            double startX = widthGap * i;
            double startY = 0;
            double endX = startX;
            double endY = prefHeight;

            Line line = new Line();
            line.setStartX(startX);
            line.setStartY(startY);
            line.setEndX(endX);
            line.setEndY(endY);
            line.setFill(new Color(0.5, 0.5, 0.5, 1));

            lineList.add(line);

        }

        for (int i = 0; i <=3; i++) {
            double widthGap = prefWidth / 3;

            double startX = widthGap * i;
            double startY = 0;
            double endX = startX;
            double endY = prefHeight;

            Line line = new Line();
            line.setStartX(startX);
            line.setStartY(startY);
            line.setEndX(endX);
            line.setEndY(endY);
            line.setStrokeWidth(3);

            lineList.add(line);

        }

        for (int i = 0; i <=9; i++) {
            double heightGap = prefHeight / 9;

            double startX = 0;
            double startY = heightGap * i;
            double endX = prefWidth;
            double endY = heightGap * i;

            Line line = new Line();
            line.setStartX(startX);
            line.setStartY(startY);
            line.setEndX(endX);
            line.setEndY(endY);

            lineList.add(line);
        }

        for (int i = 0; i <=3; i++) {
            double heightGap = prefHeight / 3;

            double startX = 0;
            double startY = heightGap * i;
            double endX = prefWidth;
            double endY = heightGap * i;

            Line line = new Line();
            line.setStartX(startX);
            line.setStartY(startY);
            line.setEndX(endX);
            line.setEndY(endY);
            line.setStrokeWidth(3);

            lineList.add(line);
        }
    }

}
