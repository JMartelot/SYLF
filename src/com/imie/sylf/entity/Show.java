package com.imie.sylf.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Show implements Serializable {
    protected int id;

    protected String title;

    protected String plot;

    protected String runtime;

    protected String released;

    protected String actors;

    protected String writers;

    protected String directors;

    protected String poster;
    
    protected ArrayList<Genre> genres;

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
    public Show() {

    }

    /**
     * @return the id
     */
    public int getId() {
         return this.id;
    }

    /**
     * @param value the id to set
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
     * @param value the title to set
     */
    public void setTitle(final String value) {
         this.title = value;
    }

    /**
     * @return the plot
     */
    public String getPlot() {
         return this.plot;
    }

    /**
     * @param value the plot to set
     */
    public void setPlot(final String value) {
         this.plot = value;
    }

    /**
     * @return the runtime
     */
    public String getRuntime() {
         return this.runtime;
    }

    /**
     * @param value the runtime to set
     */
    public void setRuntime(final String value) {
         this.runtime = value;
    }

    /**
     * @return the released
     */
    public String getReleased() {
         return this.released;
    }

    /**
     * @param value the released to set
     */
    public void setReleased(final String value) {
         this.released = value;
    }

    /**
     * @return the actors
     */
    public String getActors() {
         return this.actors;
    }

    /**
     * @param value the actors to set
     */
    public void setActors(final String value) {
         this.actors = value;
    }

    /**
     * @return the writers
     */
    public String getWriters() {
         return this.writers;
    }

    /**
     * @param value the writers to set
     */
    public void setWriters(final String value) {
         this.writers = value;
    }

    /**
     * @return the directors
     */
    public String getDirectors() {
         return this.directors;
    }

    /**
     * @param value the directors to set
     */
    public void setDirectors(final String value) {
         this.directors = value;
    }

    /**
     * @return the poster
     */
    public String getPoster() {
         return this.poster;
    }

    /**
     * @param value the poster to set
     */
    public void setPoster(final String value) {
         this.poster = value;
    }

    /**
     * @return the genres
     */
    public ArrayList<Genre> getGenres() {
         return this.genres;
    }

    /**
     * @param value the genres to set
     */
    public void setGenres(final ArrayList<Genre> value) {
         this.genres = value;
    }
}
