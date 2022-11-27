package com.example.aooppa3.database;

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

// enables the use of lambda function to get appropriate object
@FunctionalInterface
public interface Conv {
    Object stringToObject(String[] line, HashMap<String, Integer> map);
}
