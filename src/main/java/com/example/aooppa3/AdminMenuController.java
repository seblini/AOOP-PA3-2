package com.example.aooppa3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuController {
    private FXMLLoader fxmlloader;
    private Scene scene;

    private void switchScene(ActionEvent event, String fxmlFile) {
        fxmlloader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            scene = new Scene(fxmlloader.load(), 1000, 800);
        } catch (IOException e) {
            System.out.println("IDK, ERROR");
            System.exit(0);
        }
        ((Stage)((Node)event.getSource()).getScene().getWindow()).setScene(scene);
    }

    @FXML
    public void switchToMainMenuScene(ActionEvent event){
        switchScene(event, "main-menu.fxml");
    }

    @FXML
    public void switchToFindEventScene(ActionEvent event){
        switchScene(event, "find-event-menu.fxml");
    }

    @FXML
    public void switchToCreateEventScene(ActionEvent event) {
        switchScene(event, "create-event-menu.fxml");
    }
}
