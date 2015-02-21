package com.imie.sylf.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Entity for the genre
 * 
 * @author Jean
 *
 */
public class Genre implements Serializable {
    
    /**
     * Id of the genre
     */
    protected int id;

    /**
     * Title genre
     */
    protected String title;
    
    /**
     * List of shows
     */
    protected ArrayList<Show> shows;

    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.title;
    }

    /**
     * Default constructor.
     */
    public Genre() {

    }
    /**
     * Constructor with all attributes
     * @param id
     * @param title
     */
    public Genre(int id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param value
     *            the id to set
     */
    public void setId(final int value) {
        this.id = value;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param value
     *            the title to set
     */
    public void setTitle(final String value) {
        this.title = value;
    }

    /**
     * @return the shows
     */
    public ArrayList<Show> getShows() {
        return this.shows;
    }

    /**
     * @param value
     *            the shows to set
     */
    public void setShows(final ArrayList<Show> value) {
        this.shows = value;
    }
}
