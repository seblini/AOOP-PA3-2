package com.example.aooppa3.venue;

/**
 *
 * @author Benjamin Hansen
 * @author Aldo Sanchez
 * @author Sebastian Balderrama
 * @author Luis Cedillo
 * @version 11/13/2022
 *
 */

import java.util.HashMap;
/** */
public class Arena extends Venue {
    /**
     * Creates Arena object
     * @param id A String representing ID of Venu
     * @param name A String representing the name of Venu
     * @param capacity A int representing the capacity of Venue
     * @param concertCapacity A int representing the capacity of Venue if event is Concert
     * @param cost
     * @param vipPercent
     * @param goldPercent
     * @param silverPercent
     * @param bronzePercent
     * @param generalAdmissionPercent
     * @param reservedExtraPercent
     */
    public Arena(String id, String name, int capacity, int concertCapacity, double cost, int vipPercent, int goldPercent, int silverPercent, int bronzePercent, int generalAdmissionPercent, int reservedExtraPercent) {
        super(id, name, capacity, concertCapacity, cost, vipPercent, goldPercent, silverPercent, bronzePercent, generalAdmissionPercent, reservedExtraPercent);
        super.setVenueType("Arena");
    }

    public Arena(String[] line, HashMap<String, Integer> columns) {
        super(line, columns);
        super.setVenueType("Arena");
    }

    public Arena() {
        super();
        super.setVenueType("Arena");
    }
}
