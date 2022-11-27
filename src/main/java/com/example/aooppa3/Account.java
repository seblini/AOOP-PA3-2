package com.example.aooppa3;

import com.example.aooppa3.database.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

/**
 * Represents an Account
 *
 * @author Benjamin Hansen
 * @author Aldo Sanchez
 * @author Sebastian Balderrama
 * @author Luis Cedillo
 * @version 11/13/2022
 *
 */
public class Account {

    private double totalSaved;
    private String id;
    private String fName;
    private String lName;
    private String username;
    private String password;
    private double balance;
    private boolean premiumMember;
    private ArrayList<Ticket> tickets;
    private int concertsPurchased;

    /**
     * Creates Account with specified name, id, first name, last name, password, balance, premium member, concerts Purchased
     *
     * @param id                The Account holder id
     * @param fName             The Account holder first name
     * @param lName             The Accounts holder last name
     * @param username          The Accounts holder username
     * @param password          The Accounts holder password
     * @param balance           The Accounts holder balance
     * @param premiumMember     boolean representing if Account holder is Ticket Miner member
     * @param concertsPurchased The Accounts holder number of tickets purchased
     */
    public Account(String id, String fName, String lName, String username, String password, double balance,
                   boolean premiumMember, int concertsPurchased) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.premiumMember = premiumMember;
        this.concertsPurchased = concertsPurchased;
        this.tickets = new ArrayList<Ticket>();
        this.totalSaved = 0;
    }

    public Account(String[] line, HashMap<String, Integer> columns) {
        this.id = line[columns.get("ID")];
        this.fName = line[columns.get("First Name")];
        this.lName = line[columns.get("Last Name")];
        this.username = line[columns.get("Username")];
        this.password = line[columns.get("Password")];
        this.balance = Double.parseDouble(line[columns.get("Money Available")]);
        this.premiumMember = Boolean.parseBoolean(line[columns.get("TicketMiner Membership")]);
        this.concertsPurchased = Integer.parseInt(line[columns.get("Concerts Purchased")]);
        this.tickets = new ArrayList<Ticket>();
        this.totalSaved = 0;
    }

    public Account() {
        this.id = "id";
        this.fName = "first";
        this.lName = "last";
        this.username = "password";
        this.balance = 0;
        this.premiumMember = false;
        this.concertsPurchased = 0;
        this.tickets = new ArrayList<Ticket>();
        this.totalSaved = 0;
    }

    public double getTotalSaved() {
        return totalSaved;
    }

    public void setTotalSaved(double totalSaved) {
        this.totalSaved = totalSaved;
    }

    public String getId() {
        return this.id;
    }

    public String getFName() {
        return this.fName;
    }

    public String getLName() {
        return this.lName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean isPremiumMember() {
        return this.premiumMember;
    }

    public int getConcertsPurchased() {
        return this.concertsPurchased;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void setConcertsPurchased(int concertsPurchased) {
        this.concertsPurchased = concertsPurchased;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public boolean verify(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return this.id + "," + this.fName + "," + this.lName + ","
                + this.username + "," + this.password + "," + this.balance + ","
                + this.premiumMember + "," + this.concertsPurchased;
    }

    public static String getLabel() {
        return "ID,First Name,Last Name,Username,Password,Money Available,TicketMiner Membership,Concerts Purchased";
    }

    public String make_ETS(Database db) throws IOException {
        String text = "";
        for (Ticket t : this.tickets) {
            text += t.getSummary(db.getEvent(t.getEventId()));
            text += "\n--------------------------\n";
        }
        System.out.println();
        String filename = this.username + "_ETS.txt";
        File file = new File(filename);
        file.createNewFile();
        Files.writeString(Path.of(filename), text);
        return filename;
    }
}

