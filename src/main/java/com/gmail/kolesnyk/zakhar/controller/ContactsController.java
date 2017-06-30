package com.gmail.kolesnyk.zakhar.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.gmail.kolesnyk.zakhar.MainThread.primaryStage;

public class ContactsController {


    //fields of second window
    private Stage secondStage;
    private TextArea textArea2stWin;

    //fields of main window
    public AnchorPane root;
    public TextArea textArea1stWin;
    public Button clear2ndWin;
    public Button addText2ndWin;
    public Button secondWindow;


    public void initialize() {
        secondWindow.setOnAction(secondWindowHandler());
        addText2ndWin.setOnAction(addText2ndWinHandler());
        clear2ndWin.setOnAction(clear2ndWinHandler());
    }

    private EventHandler<ActionEvent> clear2ndWinHandler() {
        return event -> {
            if (textArea2stWin != null) {
                textArea2stWin.setText("");
            }
        };
    }

    private EventHandler<ActionEvent> addText2ndWinHandler() {
        return event -> {
            if (textArea2stWin != null) {
                textArea2stWin.setText(textArea2stWin.getText() + textArea1stWin.getText());
            }
        };
    }

    private void showDialog() {
        //lazy initialize
        if (secondStage == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SecondaryWindow.fxml"));
                loader.setController(this);
                AnchorPane parent = loader.load();
                //get node by css selector
                textArea2stWin = (TextArea) parent.lookup("#textArea2stWin");
                secondStage = new Stage();
                secondStage.setScene(new Scene(parent));
                secondStage.initModality(Modality.NONE);
                //owner of secondary stage is primary stage
                secondStage.initOwner(primaryStage);
                //location near left of main window
                secondStage.setX(primaryStage.getX() + primaryStage.getWidth());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //show or hide secondary window
        if (secondStage.isShowing()) {
            secondStage.close();
            secondWindow.setStyle(null);
        } else {
            System.out.println(secondWindow.getStyle());
            secondStage.show();
            secondWindow.setStyle("-fx-background-color: dimgray");
        }
    }

    private EventHandler<ActionEvent> secondWindowHandler() {
        return event -> showDialog();
    }
}
