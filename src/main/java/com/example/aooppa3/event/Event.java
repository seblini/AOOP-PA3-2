package com.example.aooppa3.event;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.aooppa3.Ticket;
import com.example.aooppa3.venue.Venue;

/**
 *
 * @author Benjamin Hansen
 * @author Aldo Sanchez
 * @author Sebastian Balderrama
 * @author Luis Cedillo
 * @version 11/13/2022
 *
 */
public abstract class Event {
    private String id;
    private String eventType;
    private String name;
    private String date;
    private String time;
    private Venue venue;
    private double vipPrice;
    private double goldPrice;
    private double silverPrice;
    private double bronzePrice;
    private double generalAdmissionPrice;

    private ArrayList<Ticket> tickets;
    private int vipSold;
    private int goldSold;
    private int silverSold;
    private int bronzeSold;
    private int generalSold;
    private double totalDiscounts;

    public Event(String id, String name, String date, String time, Venue venue, double vipPrice, double goldPrice, double silverPrice, double bronzePrice, double generalAdmissionPrice) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.vipPrice = vipPrice;
        this.goldPrice = goldPrice;
        this.silverPrice = silverPrice;
        this.bronzePrice = bronzePrice;
        this.generalAdmissionPrice = generalAdmissionPrice;
        this.tickets = new ArrayList<Ticket>();
        this.vipSold = 0;
        this.goldSold = 0;
        this.silverSold = 0;
        this.bronzeSold = 0;
        this.generalSold = 0;
        this.totalDiscounts = 0.0;
    }

    public Event(String[] line, HashMap<String, Integer> columns) {
        this.id = line[columns.get("ID")];
        this.name = line[columns.get("Name")];
        this.date = line[columns.get("Date")];
        this.time = line[columns.get("Time")];
        this.eventType = line[columns.get("Type")];
        this.venue = null;
        this.vipPrice = Double.parseDouble(line[columns.get("VIP Price")]);
        this.goldPrice = Double.parseDouble(line[columns.get("Gold Price")]);
        this.silverPrice = Double.parseDouble(line[columns.get("Silver Price")]);
        this.bronzePrice = Double.parseDouble(line[columns.get("Bronze Price")]);
        this.generalAdmissionPrice = Double.parseDouble(line[columns.get("General Admission Price")]);
        this.vipSold = 0;
        this.goldSold = 0;
        this.silverSold = 0;
        this.bronzeSold = 0;
        this.generalSold = 0;
        this.totalDiscounts = 0.0;
        if(line.length>10) {
            this.vipSold = Integer.parseInt(line[columns.get("VIP Sold")]);
            this.goldSold = Integer.parseInt(line[columns.get("Gold Sold")]);
            this.silverSold = Integer.parseInt(line[columns.get("Silver Sold")]);
            this.bronzeSold = Integer.parseInt(line[columns.get("Bronze Sold")]);
            this.generalSold = Integer.parseInt(line[columns.get("General Sold")]);
            this.totalDiscounts = Double.parseDouble(line[columns.get("Total Discounts")]);
        }
    }

    public Event() {
        this.id = "id";
        this.name = "name";
        this.date = "data";
        this.time = "time";
        this.venue = venue;
        this.vipPrice = 0;
        this.goldPrice = 0;
        this.silverPrice = 0;
        this.bronzePrice = 0;
        this.generalAdmissionPrice = 0;
        this.tickets = new ArrayList<Ticket>();
        this.vipSold = 0;
        this.goldSold = 0;
        this.silverSold = 0;
        this.bronzeSold = 0;
        this.generalSold = 0;
        this.totalDiscounts = 0.0;
    }

    public String getId() {
        return this.id;
    }

    public String getEventType() {
        return this.eventType;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public Venue getVenue() {
        return this.venue;
    }

    public int getVipSold() {
        return this.vipSold;
    }

    public int getGoldSold() {
        return this.goldSold;
    }

    public int getSilverSold() {
        return this.silverSold;
    }

    public int getBronzeSold() {
        return this.bronzeSold;
    }

    public int getGeneralSold() {
        return this.generalSold;
    }

    public double getVipPrice() {
        return this.vipPrice;
    }

    public double getGoldPrice() {
        return this.goldPrice;
    }

    public double getSilverPrice() {
        return this.silverPrice;
    }

    public double getBronzePrice() {
        return this.bronzePrice;
    }

    public double getGeneralAdmissionPrice() {
        return this.generalAdmissionPrice;
    }

    public int getTotalSold() {
        return this.vipSold + this.goldSold + this.silverSold + this.bronzeSold + this.generalSold;
    }

    public double getTotalDiscounts() {
        return this.totalDiscounts;
    }

    public void setTotalDiscounts(double discountAmount) {
        this.totalDiscounts = discountAmount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public void setGoldPrice(double goldPrice) {
        this.goldPrice = goldPrice;
    }

    public void setSilverPrice(double silverPrice) {
        this.silverPrice = silverPrice;
    }

    public void setBronzePrice(double bronzePrice) {
        this.bronzePrice = bronzePrice;
    }

    public void setGeneralAdmissionPrice(double generalAdmissionPrice) {
        this.generalAdmissionPrice = generalAdmissionPrice;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setVipSold(int vipSold) {
        this.vipSold = vipSold;
    }

    public void setGoldSold(int goldSold) {
        this.goldSold = goldSold;
    }

    public void setSilverSold(int silverSold) {
        this.silverSold = silverSold;
    }

    public void setBronzeSold(int bronzeSold) {
        this.bronzeSold = bronzeSold;
    }

    public void setGeneralSold(int generalSold) {
        this.generalSold = generalSold;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void printEventInfoCustomer(boolean isPremiumMember) {
        double discountRate = 1;
        if(isPremiumMember){
            System.out.println("Prices include discount");
            discountRate = .9;
        }
        System.out.println("---------------------------------------");
        System.out.println("event.Event ID: " + id);
        System.out.println(name);
        System.out.println(date);
        System.out.println(time);
        System.out.println("Event Type: " + eventType);
        int capacityEvent = (eventType.equals("event.Concert") ? venue.getConcertCapacity() : venue.getCapacity());
        int totalSoldDebug = this.getTotalSold();
        System.out.println("DEBUG COMMENT totalSoldDebug "+totalSoldDebug);
        System.out.println("Event Capacity: " + capacityEvent );
        System.out.println("Seats Remaining: " +(capacityEvent - this.getTotalSold()));
        System.out.println("(1) General Admission Price: " + this.generalAdmissionPrice*discountRate);
        System.out.println("(2) Bronze Price: " + this.bronzePrice*discountRate);
        System.out.println("(3) Silver Price: " + this.silverPrice*discountRate);
        System.out.println("(4) Gold Price: " + this.goldPrice*discountRate);
        System.out.println("(5) VIP Price: " + this.vipPrice*discountRate);
    }


    public void printAllEventInfo() {
        System.out.println("---------------------------------------");
        System.out.println("event.Event ID: " + id);
        System.out.println(name);
        System.out.println(date);
        System.out.println(time);
        System.out.println("event.Event Type: " + eventType);
        System.out.println("event.Event Capacity: " + (eventType.equals("event.Concert") ? venue.getConcertCapacity() : venue.getCapacity()));
        System.out.println("Total Seats Sold: " + this.getTotalSold());
        System.out.println("VIP Seats Sold: " + vipSold);
        System.out.println("Gold Seats Sold: " + goldSold);
        System.out.println("Silver Seats Sold: " + silverSold);
        System.out.println("Bronze Seats Sold: " + bronzeSold);
        System.out.println("General Admission Seats Sold: " + generalSold);
        double totalRevenue = (vipSold*vipPrice) + (goldSold*goldPrice) + (silverSold*silverPrice) + (bronzeSold*bronzePrice) + (generalSold*generalAdmissionPrice);
        System.out.println("Total Revenue For All Tickets: $" + totalRevenue);
        System.out.println("Total Revenue For VIP Tickets: $" + vipSold*vipPrice);
        System.out.println("Total Revenue For Gold Tickets: $" + goldSold*goldPrice);
        System.out.println("Total Revenue For Silver Tickets: $" + silverSold*silverPrice);
        System.out.println("Total Revenue For Bronze Tickets: $" + bronzeSold*bronzePrice);
        System.out.println("Total Revenue For General Admission Tickets: $" + generalSold*generalAdmissionPrice);
        System.out.println("Total Discounts Given: $" + this.totalDiscounts);
        double maxRevenue = (eventType.equals("event.Concert") ? venue.getConcertCapacity() : venue.getCapacity())*(venue.getVipPercent()*vipPrice + venue.getGoldPercent()*goldPrice + venue.getSilverPercent()*silverPrice + venue.getBronzePercent()*bronzePrice + venue.getGeneralAdmissionPercent()*generalAdmissionPrice)/100;
        System.out.println("Expected Profit (sellout): $" + (maxRevenue - venue.getCost()));
        System.out.println("Actual Profit: $" + (totalRevenue - venue.getCost() - this.totalDiscounts));
        System.out.println("---------------------------------------");
    }

    @Override
    public String toString() {
        return this.id + "," + this.eventType + "," + this.name + ","
                + this.date + "," + this.time + "," + this.venue.getName() + ","
                + this.vipPrice + "," + this.goldPrice + "," + this.silverPrice + ","
                + this.bronzePrice + "," + this.generalAdmissionPrice + ","
                + this.vipSold + "," + this.goldSold + "," + this.silverSold + ","
                + this.bronzeSold + "," + this.generalSold  + "," + this.totalDiscounts;
    }

    public static Event make(String[] line, HashMap<String, Integer> columns)  {
        if(line[columns.get("Type")].equals("Sport"))
            return new Sport(line, columns);
        if(line[columns.get("Type")].equals("Special"))
            return new Special(line, columns);
        if(line[columns.get("Type")].equals("Concert"))
            return new Concert(line, columns);
        return null;
    }

    public static Event make(String newEventType, String  eventID, String eventName, String eventDate, String eventTime, Venue eventVenue, Double eventVipPrice, Double eventGoldPrice, Double eventSilverPrice, Double eventBronzePrice, Double eventGeneralAdmissionPrice){
        if(newEventType.equals("Sport"))
            return new Sport(eventID, eventName, eventDate, eventTime, eventVenue, eventVipPrice, eventGoldPrice, eventSilverPrice, eventBronzePrice, eventGeneralAdmissionPrice);
        else if(newEventType.equals("Special"))
            return new Special(eventID, eventName, eventDate, eventTime, eventVenue, eventVipPrice, eventGoldPrice, eventSilverPrice, eventBronzePrice, eventGeneralAdmissionPrice);
        else if(newEventType.equals("Concert"))
            return new Concert(eventID, eventName, eventDate, eventTime, eventVenue, eventVipPrice, eventGoldPrice, eventSilverPrice, eventBronzePrice, eventGeneralAdmissionPrice);
        return null;
    }

    public static String getLabel(){
        return "ID,Type,Name,Date,Time,Venue ID,VIP Price,Gold Price,Silver Price,Bronze Price,General Admission Price,VIP Sold,Gold Sold,"+
                "Silver Sold,Bronze Sold,General Sold,Total Discounts";
    }
}
