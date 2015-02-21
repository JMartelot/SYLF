package com.imie.sylf.util;

/**
 * Interface use for parse JSON after the execution of an AsyncTask and web services
 * @author Jean
 *
 * @param <T>
 */
public abstract interface Parser<T>{
    /**
     * Method to parse the JSON stream
     * @param output
     */
    public abstract void parseJSON(String output);
}
