package com.example.aooppa3;

import com.example.aooppa3.database.Database;
import com.example.aooppa3.event.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CreateEventMenuController {
    private FXMLLoader fxmlloader;
    private Scene scene;
    @FXML
    private Text warning;

    @FXML
    private List<Object> infoList;

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
    public void switchToAdminMenuScene(ActionEvent event){
        switchScene(event, "admin-menu.fxml");
    }

    @FXML
    public void createEvent(ActionEvent event) throws Exception {
        Object[] userData = ((Object[])((Stage)((Node)event.getSource()).getScene().getWindow()).getUserData());
        Database db = (Database)userData[0];

        Event e;
        int count = 0;
        String[] line = new String[10];

        for(int i = 0; i < 3; i++) {
            if(((RadioButton)infoList.get(i)).isSelected()) {
                line[0] = ((RadioButton) infoList.get(i)).getText();
                count++;
            }
        }
        if(((TextField)infoList.get(3)).getText() != null && !((TextField)infoList.get(3)).getText().equals("")) {
            line[1] = ((TextField) infoList.get(3)).getText();
            count++;
        }

        if(((TextField)infoList.get(4)).getText() != null && !((TextField)infoList.get(4)).getText().equals("")) {
            line[2] = ((TextField) infoList.get(4)).getText();
            count++;
        }

        if(((TextField)infoList.get(5)).getText() != null && !((TextField)infoList.get(5)).getText().equals("")) {
            line[3] = ((TextField) infoList.get(5)).getText();
            count++;
        }

        if(((TextField)infoList.get(6)).getText() != null && !((TextField)infoList.get(6)).getText().equals("")) {
            line[4] = ((TextField) infoList.get(6)).getText();
            count++;
        }

        if(((TextField)infoList.get(7)).getText() != null && !((TextField)infoList.get(7)).getText().equals("")) {
            line[5] = ((TextField) infoList.get(7)).getText();
            count++;
        }

        if(((TextField)infoList.get(8)).getText() != null && !((TextField)infoList.get(8)).getText().equals("")) {
            line[6] = ((TextField) infoList.get(8)).getText();
            count++;
        }

        if(((TextField)infoList.get(9)).getText() != null && !((TextField)infoList.get(9)).getText().equals("")) {
            line[7] = ((TextField) infoList.get(9)).getText();
            count++;
        }

        if(((TextField)infoList.get(10)).getText() != null && !((TextField)infoList.get(10)).getText().equals("")) {
            line[8] = ((TextField) infoList.get(10)).getText();
            count++;
        }

        int j;
        for(j = 11; j < infoList.size(); j++) {
            if(((RadioButton)infoList.get(j)).isSelected()) {
                count++;
                break;
            }
        }

        if(count < 10) {
            warning.setVisible(true);
            return;
        }

        String[] label = {"Type","Name","Date","Time","General Admission Price","Bronze Price","Silver Price","Gold Price","VIP Price","ID"};
        HashMap<String, Integer> columns = db.makeColumnMap(label);

        e = Event.make(line, columns);
        e.setVenue(db.getVenue(((RadioButton)infoList.get(j)).getText()));

        db.addEvent(e);
        warning.setText("Event Created");
    }
}
