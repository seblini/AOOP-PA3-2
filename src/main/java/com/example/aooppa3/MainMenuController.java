package com.example.aooppa3;

import com.example.aooppa3.database.Database;
import com.example.aooppa3.event.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenuController {
    FXMLLoader fxmlloader;
    private Scene scene;
    private final String path = "/Users/sebi/UTEP/Advanced OOP/AOOP-PA3-2/src/main/java/com/example/aooppa3/csvfile/";

    private final String accountCSVFilePath = path+"Customer_List_PA1.csv";
    private final String eventCSVFilePath = path+"Event_List_PA1.csv";
    private final String ticketsCSVFilePath = path+"Ticket_List_PA1.csv";

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
    public void exitProgram(ActionEvent event) {
        Database db = (Database)((Object[])((Stage)((Node)event.getSource()).getScene().getWindow()).getUserData())[0];
        db.writeAll(accountCSVFilePath,eventCSVFilePath,ticketsCSVFilePath);
        System.exit(0);
    }

    @FXML
    public void switchToAdminMenuScene(ActionEvent event){
        switchScene(event, "admin-menu.fxml");
    }

    @FXML
    public void switchToClientMenuScene(ActionEvent event){
        exitProgram(event);
    }
}
