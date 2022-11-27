package com.example.aooppa3.database;

import com.example.aooppa3.Account;
import com.example.aooppa3.Ticket;
import com.example.aooppa3.event.Event;
import com.example.aooppa3.venue.Venue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Benjamin Hansen
 * @author Aldo Sanchez
 * @author Sebastian Balderrama
 * @author Luis Cedillo
 * @version 11/13/2022
 *
 */



public class Database {

    private HashMap<String, Account> accounts;
    private HashMap<String, Event> events;
    private HashMap<String, Venue> venues;
    private HashMap<String, Ticket> tickets;


    private int[] maxId;

    /**
     *
     * @param accountsFile
     * @param eventsFile
     * @param venuesFile
     * @param ticketsFile
     */
    public Database(String accountsFile, String eventsFile, String venuesFile, String ticketsFile) {
        this.accounts = new HashMap<>();
        this.events = new HashMap<>();
        this.venues = new HashMap<>();
        this.tickets = new HashMap<>();
        // 0:account, 1:event, 2:venue, 3:ticket
        this.maxId = new int[4];
        this.buildDatabase(accountsFile, eventsFile, venuesFile, ticketsFile);
    }

    /**
     *
     * @param key
     * @return
     */
    public Account getAccount(String key) {
        return this.accounts.get(key);
    }

    /**
     * @param key
     * @return
     */
    public Event getEvent(String key) {
        return this.events.get(key);
    }

    public Venue getVenue(String key) {
        return this.venues.get(key);
    }

    /**
     *
     * @param account
     * @throws Exception
     */
    public void addAccount(Account account) throws Exception {
        if(account == null) return;
        account.setId(String.valueOf(++maxId[0]));
        // add account to account hashmap by id
        this.accounts.put(account.getId(), account);
        // add account to account hashmap by username
        this.accounts.put(account.getUsername(), account);
    }

    /**
     *
     * @param event
     * @throws Exception
     */
    public void addEvent(Event event) throws Exception {
        if(event == null) return;
        event.setId(String.valueOf(++maxId[1]));
        // add event to event hashmap by id
        this.events.put(event.getId(), event);
        // add event to event hashmap by name
        this.events.put(event.getName(), event);
    }

    /**
     *
     * @param venue
     * @throws Exception
     */
    public void addVenue(Venue venue) throws Exception {
        if(venue == null) return;
        // add venue to venue hashmap by id
        this.venues.put(venue.getId(), venue);
        // add venue to venue hashmap by name
        this.venues.put(venue.getName(), venue);
    }

    /**
     *
     * @param account
     * @param ticket
     * @throws Exception
     */
    public void addTicket(Account account, Ticket ticket) throws Exception {
        if(ticket == null) return;
        // update confirmation number
        ticket.setConfirmationNumber(String.valueOf(++maxId[3]));
        // add ticket to customers ticket list
        account.addTicket(ticket);
        // add ticket to ticket hashmap by confirmation number
        tickets.put(String.valueOf(ticket.getConfirmationNumber()), ticket);
    }

    /**
     * This prints all venues to show to user when they are changing or creating an event venue
     */
    public void printAllVenues() {
        for(Map.Entry<String, Venue> o : venues.entrySet())
            try {
                Integer.parseInt(o.getKey());
                System.out.println(o.getKey() + " : " + o.getValue().getName());
            } catch(Exception e) {}
    }

    /**
     * add tickets to users
     */
    private void ticketMapToCustomerList() {
        for(Map.Entry<String, Ticket> o : this.tickets.entrySet())
            try {
                this.accounts.get(o.getValue().getCustomerId()).addTicket(o.getValue());
            } catch(Exception e) {}
    }

    /**
     * establishes the order that the columns are in incase they are not in the normal order
     * @param arr
     * @return
     */
    public HashMap<String , Integer> makeColumnMap(String[] arr) {
        HashMap<String, Integer> columns = new HashMap<>();
        for(int i = 0; i < arr.length; i++)
            columns.put(arr[i], i);
        return columns;
    }

    /**
     * Generic method that reads the csv file and produces the hashmap of that type with two keys
     * @param file
     * @param map
     * @param maxIdType
     * @param conv
     * @param key1
     * @param key2
     * @param <T>
     */
    // reads csv, creates objects, and places them into their respective hashmaps
    private <T> void readCsv(String file, HashMap<String, T> map, int maxIdType, Conv conv, Key<T> key1, Key<T> key2) {
        Object o;
        String temp1;
        int temp2;
        HashMap<String, Integer> columns;
        // creates scanner
        Scanner sc;
        try {
            sc = new Scanner(new File(file));
        } catch(FileNotFoundException e) {
            System.out.println("'" + file + "' file does not exist.");
            return;
        }
        // puts labels into array and creates hashmap
        columns = makeColumnMap(sc.nextLine().split(","));
        // iterates through csv
        while(sc.hasNext()) {
            // reads next line and creates object using columns
            o = conv.stringToObject(sc.nextLine().split(","), columns);
            // adds account to hashmap by name or username
            map.put(key1.key((T)o), (T)o);
            // adds account to hashmap by id
            temp1 = key2.key((T)o);
            map.put(temp1, (T)o);
            // updates maxId
            temp2 = Integer.parseInt(temp1);
            if(temp2 > maxId[maxIdType])
                maxId[maxIdType] = temp2;
        }
        sc.close();
    }

    /**
     * builds all 4 hashmaps
     * @param accountsFile
     * @param eventsFile
     * @param venuesFile
     * @param ticketsFile
     */
    // calls readCsv for every csv file
    private void buildDatabase(String accountsFile, String eventsFile, String venuesFile, String ticketsFile) {
        readCsv(accountsFile, accounts, 0, Account::new, Account::getUsername, Account::getId);
        readCsv(venuesFile, venues, 2, Venue::make, Venue::getName, Venue::getId);
        Conv stringToEvent = (String[] line, HashMap<String, Integer> map) -> {
            Event e = Event.make(line, map);
            e.setVenue(venues.get(map.containsKey("Venue ID") ? line[map.get("Venue ID")] : "2"));
            return e;
        };
        readCsv(eventsFile, events, 1, stringToEvent, Event::getName, Event::getId);
        readCsv(ticketsFile, tickets, 3, Ticket::new, Ticket::getConfirmationNumber, Ticket::getConfirmationNumber);
        ticketMapToCustomerList();
    }

    /**
     * writes the data from the hashmaps to the corresponding csv
     * @param file
     * @param map
     * @param label
     * @param <T>
     * @throws IOException
     */
    // gets string representation of every object in hashmap and writes them to csv
    private <T> void writeToCsv(String file, HashMap<String, T> map, String label) throws IOException{
        String text = label + "\n";
        for(Map.Entry<String, T> o : map.entrySet()) {
            try {
                Integer.parseInt(o.getKey());
                text +=  o.getValue()+ "\n";
            } catch(Exception e) {}
        }
        Files.writeString(Path.of(file), text);
    }

    /**
     * stores all hashmaps to their csvs
     * @param accountsFile
     * @param eventsFile
     * @param ticketsFile
     */
    // calls writeToCsv for every csv
    public void writeAll(String accountsFile, String eventsFile, String ticketsFile) {
        try {
            this.writeToCsv(accountsFile, accounts, Account.getLabel());
            this.writeToCsv(eventsFile, events, Event.getLabel());
            this.writeToCsv(ticketsFile, tickets, Ticket.getLabel());
        } catch(IOException e) {
            System.out.println("Error");
        }
    }
}
