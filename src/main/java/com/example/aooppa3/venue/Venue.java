package com.example.aooppa3.venue;

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
public abstract class Venue {
    private String id;
    private String venueType;
    private String name;
    private int capacity;
    private int concertCapacity;
    private double cost;
    private double vipPercent;
    private double goldPercent;
    private double silverPercent;
    private double bronzePercent;
    private double generalAdmissionPercent;
    private double reservedExtraPercent;

    public Venue(String id, String name, int capacity, int concertCapacity, double cost, double vipPercent, double goldPercent, double silverPercent, double bronzePercent, double generalAdmissionPercent, double reservedExtraPercent) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.concertCapacity = concertCapacity;
        this.cost = cost;
        this.vipPercent = vipPercent;
        this.goldPercent = goldPercent;
        this.silverPercent = silverPercent;
        this.bronzePercent = bronzePercent;
        this.generalAdmissionPercent = generalAdmissionPercent;
        this.reservedExtraPercent = reservedExtraPercent;
    }

    public Venue(String[] line, HashMap<String, Integer> columns) {
        this.id = line[columns.get("ID")];
        this.name = line[columns.get("Name")];
        this.capacity = Integer.parseInt(line[columns.get("Capacity")]);
        this.concertCapacity = Integer.parseInt(line[columns.get("Concert Capacity")]);
        this.cost = Double.parseDouble(line[columns.get("Cost")]);
        this.vipPercent = Double.parseDouble(line[columns.get("VIP Percent")]);
        this.goldPercent = Double.parseDouble(line[columns.get("Gold Percent")]);
        this.silverPercent = Double.parseDouble(line[columns.get("Silver Percent")]);
        this.bronzePercent = Double.parseDouble(line[columns.get("Bronze Percent")]);
        this.generalAdmissionPercent = Double.parseDouble(line[columns.get("General Admission Percent")]);
        this.reservedExtraPercent = Double.parseDouble(line[columns.get("Reserved Extra Percent")]);
    }

    public Venue() {
        this.id = "id";
        this.name = "name";
        this.capacity = 0;
        this.concertCapacity = 0;
        this.cost = 0;
        this.vipPercent = 0;
        this.goldPercent = 0;
        this.silverPercent = 0;
        this.bronzePercent = 0;
        this.generalAdmissionPercent = 0;
        this.reservedExtraPercent = 0;
    }

    public int getBronzeCapacity(boolean isConcert){
        return (int)(isConcert ? this.concertCapacity*this.bronzePercent : this.capacity * this.bronzePercent);
    }
    public int getGeneralCapacity(boolean isConcert){
        return (int)(isConcert ? this.concertCapacity*this.generalAdmissionPercent : this.capacity * this.generalAdmissionPercent);

    }
    public int getVIPCapacity(boolean isConcert){
        return (int)(isConcert ? this.concertCapacity*this.vipPercent: this.capacity * this.vipPercent);

    }
    public int getGoldCapacity(boolean isConcert){
        return (int)(isConcert ? this.concertCapacity*this.goldPercent : this.capacity * this.goldPercent);
    }
    public int getSilverCapacity(boolean isConcert){
        return (int)(isConcert ? this.concertCapacity*this.silverPercent : this.capacity * this.silverPercent);

    }
    public String getId() {
        return this.id;
    }

    public String getVenueType() {
        return this.venueType;
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getConcertCapacity() {
        return this.concertCapacity;
    }

    public double getCost() {
        return this.cost;
    }

    public double getVipPercent() {
        return this.vipPercent;
    }

    public double getGoldPercent() {
        return this.goldPercent;
    }

    public double getSilverPercent() {
        return this.silverPercent;
    }

    public double getBronzePercent() {
        return this.bronzePercent;
    }

    public double getGeneralAdmissionPercent() {
        return this.generalAdmissionPercent;
    }

    public double getReservedExtraPercent() {
        return this.reservedExtraPercent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setConcertCapacity(int concertCapacity) {
        this.concertCapacity = concertCapacity;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setVipPercent(int vipPercent) {
        this.vipPercent = vipPercent;
    }

    public void setGoldPercent(int goldPercent) {
        this.goldPercent = goldPercent;
    }

    public void setSilverPercent(int silverPercent) {
        this.silverPercent = silverPercent;
    }

    public void setBronzePercent(int bronzePercent) {
        this.bronzePercent = bronzePercent;
    }

    public void setGeneralAdmissionPercent(int generalAdmissionPercent) {
        this.generalAdmissionPercent = generalAdmissionPercent;
    }

    public void setReservedExtraPercent(int reservedExtraPercent) {
        this.reservedExtraPercent = reservedExtraPercent;
    }

    @Override
    public String toString() {
        return this.id + "," + this.name + "," + this.venueType + ","
                + this.capacity + "," + this.concertCapacity + "," + this.vipPercent + ","
                + this.silverPercent + "," + this.bronzePercent + "," + this.generalAdmissionPercent + ","
                + this.reservedExtraPercent;
    }

    public static Venue make(String[] line, HashMap<String, Integer> columns) {
        if(line[columns.get("Type")].equals("Stadium"))
            return new Stadium(line, columns);
        if(line[columns.get("Type")].equals("Arena"))
            return new Arena(line, columns);
        if(line[columns.get("Type")].equals("Auditorium"))
            return new Auditorium(line, columns);
        if(line[columns.get("Type")].equals("Open Air"))
            return new OpenAir(line, columns);
        return null;
    }
}
