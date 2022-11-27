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

// enable the use of lambda function to get appropriate key
@FunctionalInterface
public interface Key<I> {
    String key(I i);
}
