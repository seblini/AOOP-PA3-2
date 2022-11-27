package com.example.aooppa3;

import com.example.aooppa3.database.Database;
import com.example.aooppa3.event.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class FindEventMenuController {
    private FXMLLoader fxmlloader;
    private Scene scene;

    private Event e;
    private String info;

    @FXML
    private Text welcome;
    @FXML
    private TextField searchKey;
    @FXML
    private Button search, modifyEvent, showEventInfo;

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
    public void switchToModifyEventMenuScene(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Object[] userData = (Object[])stage.getUserData();
        userData[1] = e;
        stage.setUserData(userData);

        switchScene(event, "modify-event-menu.fxml");
    }

    public void searchEvent(ActionEvent event) {
        Database db = (Database)((Object[])((Stage)((Node)event.getSource()).getScene().getWindow()).getUserData())[0];
        e = db.getEvent(searchKey.getText());
        if(e == null) {
            welcome.setText("That event does not exist\nFind Event by Name or ID");
            modifyEvent.setVisible(false);
            showEventInfo.setVisible(false);
            return;
        }
        welcome.setText("Find Event by Name or ID");
        modifyEvent.setVisible(true);
        showEventInfo.setVisible(true);
    }

    @FXML
    public void displayInfo() {
        setInfo();
        welcome.setText(info);
        search.setVisible(false);
        searchKey.setVisible(false);
        modifyEvent.setVisible(false);
        showEventInfo.setVisible(false);
    }

    private void setInfo() {
        double totalRevenue = (e.getVipSold()*e.getVipPrice()) + (e.getGoldSold()*e.getGoldPrice()) + (e.getSilverSold()*e.getSilverPrice()) + (e.getBronzeSold()*e.getBronzePrice()) + (e.getGeneralSold()*e.getGeneralAdmissionPrice());
        double maxRevenue = (e.getEventType().equals("Concert") ? e.getVenue().getConcertCapacity() : e.getVenue().getCapacity())*(e.getVenue().getVipPercent()*e.getVipPrice() + e.getVenue().getGoldPercent()*e.getGoldPrice() + e.getVenue().getSilverPercent()*e.getSilverPrice() + e.getVenue().getBronzePercent()*e.getBronzePrice() + e.getVenue().getGeneralAdmissionPercent()*e.getGeneralAdmissionPrice())/100;
        info =  "Event ID: " + e.getId() + "\n" +
                e.getName() + "\n" +
                e.getDate() + "\n" +
                e.getTime() + "\n" +
                "Event Type: " + e.getEventType() + "\n" +
                "Event Capacity: " + (e.getEventType().equals("Concert") ? e.getVenue().getConcertCapacity() : e.getVenue().getCapacity()) + "\n" +
                "Total Seats Sold: " + e.getTotalSold() + "\n" +
                "VIP Seats Sold: " + e.getVipSold() + "\n" +
                "Gold Seats Sold: " + e.getGoldSold() + "\n" +
                "Silver Seats Sold: " + e.getSilverSold() + "\n" +
                "Bronze Seats Sold: " + e.getBronzeSold() + "\n" +
                "General Admission Seats Sold: " + e.getGeneralSold() + "\n" +
                "Total Revenue For All Tickets: $" + totalRevenue + "\n" +
                "Total Revenue For VIP Tickets: $" + e.getVipSold()*e.getVipPrice() + "\n" +
                "Total Revenue For Gold Tickets: $" + e.getGoldSold()*e.getGoldPrice() + "\n" +
                "Total Revenue For Silver Tickets: $" + e.getSilverSold()*e.getSilverSold() + "\n" +
                "Total Revenue For Bronze Tickets: $" + e.getBronzeSold()*e.getBronzePrice() + "\n" +
                "Total Revenue For General Admission Tickets: $" + e.getGeneralSold()*e.getGeneralAdmissionPrice() + "\n" +
                "Total Discounts Given: $" + e.getTotalDiscounts() + "\n" +
                "Expected Profit (sellout): $" + (maxRevenue - e.getVenue().getCost()) + "\n" +
                "Actual Profit: $" + (totalRevenue - e.getVenue().getCost() - e.getTotalDiscounts());
    }
}
