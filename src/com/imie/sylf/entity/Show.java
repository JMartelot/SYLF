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
    
    private String backdrop_path;
    
    private int in_production;
    
    private int vote_average;
    
    private int vote_count;
    
    protected ArrayList<Genre> genres;
    
    private ArrayList<Author> authors;
    
    private ArrayList<String> languages;

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.title;
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

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getIn_production() {
        return in_production;
    }

    public void setIn_production(int in_production) {
        this.in_production = in_production;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public Show(int id, String title, String plot, String runtime,
            String released, String actors, String writers, String directors,
            String poster, String backdrop_path, int in_production,
            int vote_average, int vote_count, ArrayList<Genre> genres,
            ArrayList<Author> authors, ArrayList<String> languages) {
        super();
        this.id = id;
        this.title = title;
        this.plot = plot;
        this.runtime = runtime;
        this.released = released;
        this.actors = actors;
        this.writers = writers;
        this.directors = directors;
        this.poster = poster;
        this.backdrop_path = backdrop_path;
        this.in_production = in_production;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.genres = genres;
        this.authors = authors;
        this.languages = languages;
    }
    

    /**
     * Default constructor.
     */
    public Show() {

    }
    
}
