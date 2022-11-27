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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ModifyEventMenuController {
    private FXMLLoader fxmlloader;
    private Scene scene;

    @FXML
    private List<Object> changesList;

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
    public void submitModifications(ActionEvent event) {
        Object[] userData = ((Object[])((Stage)((Node)event.getSource()).getScene().getWindow()).getUserData());
        Event e = (Event)userData[1];
        Database db = (Database)userData[0];

        for(int i = 0; i < 3; i++) {
            if(((RadioButton)changesList.get(i)).isSelected())
                e.setEventType(((RadioButton)changesList.get(i)).getText());
        }
        if(((TextField)changesList.get(3)).getText() != null && !((TextField)changesList.get(3)).getText().equals(""))
            e.setName(((TextField)changesList.get(3)).getText());

        if(((TextField)changesList.get(4)).getText() != null && !((TextField)changesList.get(4)).getText().equals(""))
            e.setDate(((TextField)changesList.get(4)).getText());

        if(((TextField)changesList.get(5)).getText() != null && !((TextField)changesList.get(5)).getText().equals(""))
            e.setTime(((TextField)changesList.get(5)).getText());

        if(((TextField)changesList.get(6)).getText() != null && !((TextField)changesList.get(6)).getText().equals(""))
            e.setGeneralAdmissionPrice(Double.parseDouble(((TextField)changesList.get(6)).getText()));

        if(((TextField)changesList.get(7)).getText() != null && !((TextField)changesList.get(7)).getText().equals(""))
            e.setBronzePrice(Double.parseDouble(((TextField)changesList.get(7)).getText()));

        if(((TextField)changesList.get(8)).getText() != null && !((TextField)changesList.get(8)).getText().equals(""))
            e.setSilverPrice(Double.parseDouble(((TextField)changesList.get(8)).getText()));

        if(((TextField)changesList.get(9)).getText() != null && !((TextField)changesList.get(9)).getText().equals(""))
            e.setGoldPrice(Double.parseDouble(((TextField)changesList.get(9)).getText()));

        if(((TextField)changesList.get(10)).getText() != null && !((TextField)changesList.get(10)).getText().equals(""))
            e.setVipPrice(Double.parseDouble(((TextField)changesList.get(10)).getText()));

        for(int i = 11; i < changesList.size(); i++) {
            if(((RadioButton)changesList.get(i)).isSelected())
                e.setVenue(db.getVenue(((RadioButton)changesList.get(i)).getText()));
        }
    }
}
