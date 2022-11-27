package com.example.aooppa3;

import com.example.aooppa3.event.Event;

import java.util.HashMap;
/**
 *
 * @author Benjamin Hansen
 * @author Aldo Sanchez
 * @author Sebastian Balderrama
 * @author Luis Cedillo
 * @version 11/13/2022
 *
 */
public class Ticket {
    // change this to be stored in CSV

    private String confirmationNumber;
    private int numTickets;
    private double totalCost;
    private double totalTaxed;
    private double totalDiscount;
    private String eventId;
    private String customerId;
    private String ticketType;

    /**
     * Creates Ticket
     * @param numTickets A int representing number of tickets
     * @param totalCost A doubl representing the total cost of ticket purchase
     * @param totalTaxed A double representing the total taxed
     * @param totalDiscount A double representing the total discount
     * @param eventId A String representing the event Id
     * @param customerId A String representing the ID of User
     * @param ticketType A String representing the ticket type
     */

    public Ticket(int numTickets, double totalCost, double totalTaxed, double totalDiscount, String eventId,
                  String customerId, String ticketType) {
        this.numTickets = numTickets;
        this.ticketType = ticketType;
        this.totalCost = totalCost;
        this.totalTaxed = totalTaxed;
        this.totalDiscount = totalDiscount;
        this.eventId = eventId;
        this.customerId = customerId;
    }

    public Ticket(String[] line, HashMap<String, Integer> columns) {
        this.numTickets = Integer.parseInt(line[columns.get("Number of Tickets")]);
        this.totalCost = Double.parseDouble(line[columns.get("Total Cost")]);
        this.totalTaxed = Double.parseDouble(line[columns.get("Total Taxed")]);
        this.totalDiscount = Double.parseDouble(line[columns.get("Total Discount")]);
        this.eventId = line[columns.get("Event ID")];
        this.customerId = line[columns.get("Customer ID")];
        this.ticketType = line[columns.get("Ticket Type")];
        this.confirmationNumber = line[columns.get("Confirmation Number")];
    }
    /**
     * Gets number of tickets user has bought
     * @return A int representing the total number of tickets
     */

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getNumTickets() {
        return this.numTickets;
    }
    /**
     * Gets total cost of ticket purchase
     * @return double representing total cost
     */
    public double getTotalCost() {
        return this.totalCost;
    }

    public double getTotalTaxed() {
        return this.totalTaxed;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getConfirmationNumber() {
        return this.confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public void displayTickets() {
        System.out.println("\n Confirmation Number: " + this.confirmationNumber +
                "\nTotal Cost: " + this.totalCost +
                "\nEvent ID: " + this.eventId +
                "\nEvent Type: " + this.ticketType +
                "\nNumber Tickets: " + this.numTickets +
                "\nUser ID Number: " + this.customerId +
                "\nTotal Taxed: " + this.totalTaxed+
                "\nTicket Type: "+this.ticketType);
    }

    @Override
    public String toString() {
        return this.confirmationNumber + "," + this.eventId + "," + this.customerId + ","
                + this.numTickets + "," + this.totalCost + "," + this.totalTaxed + ","
                + this.totalDiscount+","+this.ticketType;
    }

    public static String getLabel(){
        return "Confirmation Number,Event ID,Customer ID,Number of Tickets,Total Cost,Total Taxed,Total Discount,Ticket Type";
    }

    public String getSummary(Event event){
        return event.getEventType()+"\n" +
                event.getName()+"\n" +
                event.getDate() +"\n" +
                this.ticketType +"\n" +
                this.numTickets+"\n" +
                this.totalCost+"\n" +
                this.confirmationNumber;
    }
}
