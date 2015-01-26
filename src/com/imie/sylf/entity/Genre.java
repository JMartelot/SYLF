package com.imie.sylf.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Genre implements Serializable {

    protected int id;

    protected String title;

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
