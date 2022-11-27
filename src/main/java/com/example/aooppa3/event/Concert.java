package com.example.aooppa3.event;

/**
 *
 * @author Benjamin Hansen
 * @author Aldo Sanchez
 * @author Sebastian Balderrama
 * @author Luis Cedillo
 * @version 11/13/2022
 *
 */

import com.example.aooppa3.venue.Venue;

import java.util.HashMap;

public class Concert extends Event {
    public Concert(String id, String name, String date, String time, Venue venue, double vipPrice, double goldPrice, double silverPrice, double bronzePrice, double generalAdmissionPrice) {
        super(id, name, date, time, venue, vipPrice, goldPrice, silverPrice, bronzePrice, generalAdmissionPrice);
        super.setEventType("Concert");
    }

    public Concert(String[] line, HashMap<String, Integer> columns) {
        super(line, columns);
        super.setEventType("Concert");
    }

    public Concert() {
        super();
        super.setEventType("Concert");
    }
}
