package com.example.sudokusolver;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SudokuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        //create buttons
        Button solveButton = new Button("Solve");
        Button resetButton = new Button("Reset");
        Button changeButton = new Button("Choose File");

        //add buttons to hbox along top of app
        HBox buttons = new HBox();
        buttons.getChildren().add(solveButton);
        buttons.getChildren().add(resetButton);
        buttons.getChildren().add(changeButton);
        buttons.setSpacing(10.0);
        buttons.setPadding(new Insets(10.0));

        FileChooser fileChooser = new FileChooser();

        //set up the pane for drawing
        PaneHandler paneHandler = new PaneHandler();
        SudokuSolver sudokuSolver = new SudokuSolver();
        InputHandler inputHandler = new InputHandler();

        Pane pane = paneHandler.drawPane(inputHandler.getInputNumbers().clone(), inputHandler.getInputNumbers().clone());

        solveButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try {
                    sudokuSolver.solveGame(paneHandler);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sudokuSolver.setNumbers(inputHandler.getInputNumbers());
                paneHandler.updatePane(inputHandler.getInputNumbers());
            }
        });

        changeButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    File file = fileChooser.showOpenDialog(stage);
                    try {
                        if (file != null) {
                            inputHandler.loadCSV(file.getPath());
                            sudokuSolver.setNumbers(inputHandler.getInputNumbers());
                            paneHandler.updatePane(inputHandler.getInputNumbers());
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        //create layout and add pane & buttons
        BorderPane layout = new BorderPane();
        //layout.setPadding(new Insets(10.0));
        layout.setTop(buttons);
        layout.setCenter(pane);
        Scene scene = new Scene(layout);
        stage.setTitle("Sudoku Solver");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}