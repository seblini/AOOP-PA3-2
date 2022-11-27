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
public class Auditorium extends Venue {
    public Auditorium(String id, String name, int capacity, int concertCapacity, double cost, int vipPercent, int goldPercent, int silverPercent, int bronzePercent, int generalAdmissionPercent, int reservedExtraPercent) {
        super(id, name, capacity, concertCapacity, cost, vipPercent, goldPercent, silverPercent, bronzePercent, generalAdmissionPercent, reservedExtraPercent);
        super.setVenueType("Auditorium");
    }

    public Auditorium(String[] line, HashMap<String, Integer> columns) {
        super(line, columns);
        super.setVenueType("Auditorium");
    }

    public Auditorium() {
        super();
        super.setVenueType("Auditorium");
    }
}
