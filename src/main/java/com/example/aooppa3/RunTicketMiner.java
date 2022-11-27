package com.example.aooppa3;

import com.example.aooppa3.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunTicketMiner extends Application {
    private static final String path = "src/main/java/com/example/aooppa3/csvfile/";
    private static Database db = new Database(path+"Customer_List_PA1.csv",
                                                path+"Event_List_PA1.csv",
                                                path+"Venue_List_PA1.csv",
                                                path+"Ticket_List_PA1.csv");

    @Override
    public void start(Stage stage) throws IOException {
        Object[] userData = new Object[2];
        userData[0] = db;
        stage.setUserData(userData);

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 1000, 800);

        stage.setTitle("Ticket Miner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
