package com.example.aooppa3.event;

import com.example.aooppa3.venue.Venue;

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
public class Sport extends Event {
    public Sport(String id, String name, String date, String time, Venue venue, double vipPrice, double goldPrice, double silverPrice, double bronzePrice, double generalAdmissionPrice) {
        super(id, name, date, time, venue, vipPrice, goldPrice, silverPrice, bronzePrice, generalAdmissionPrice);
        super.setEventType("Sport");
    }

    public Sport(String[] line, HashMap<String, Integer> columns) {
        super(line, columns);
        super.setEventType("Sport");
    }

    public Sport() {
        super();
        super.setEventType("Sport");
    }
}
