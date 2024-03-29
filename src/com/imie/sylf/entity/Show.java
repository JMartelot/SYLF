package com.imie.sylf.entity;

import java.io.Serializable;
import java.util.ArrayList;

import android.R.bool;
import android.graphics.Bitmap;

public class Show implements Serializable {
    /**
     * Id show
     */
    protected int id;
    /**
     * Title show
     */
    protected String title;
    /**
     * Plot show
     */
    protected String plot;
    /**
     * runtime show
     */
    protected String runtime;
    /**
     * Released show
     */
    protected String released;
    /**
     * Actors show
     */
    protected String actors;
    /**
     * Writers Show
     */
    protected String writers;
    /**
     * Directors show
     */
    protected String directors;
    /**
     * Poster path show
     */
    protected String poster;
    /**
     * Backdrop path show
     */
    private String backdrop_path;
    /**
     * Video Show
     */
    private String video;
    /**
     * Status show
     */
    private boolean in_production;
    /**
     * Vote average show
     */
    private double vote_average;
    /**
     * Vote count Show
     */
    private int vote_count;
    /**
     * Genres list of the show
     */
    protected ArrayList<Genre> genres;
    /**
     * Authors list of the show
     */
    private ArrayList<Author> authors;
    /**
     * Seasons list of the show
     */
    private ArrayList<Season> seasons;
    /**
     * Language list of the show
     */
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

    public boolean getIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
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
    
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
    
    public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	/**
	 * Constructor with all the entity attributes
	 * @param id
	 * @param title
	 * @param plot
	 * @param runtime
	 * @param released
	 * @param poster
	 * @param backdrop_path
	 * @param in_production
	 * @param vote_average
	 * @param vote_count
	 * @param genres
	 * @param authors
	 * @param seasons
	 */
	public Show(int id, String title, String plot, String runtime,
            String released, String poster, String backdrop_path, boolean in_production,
            double vote_average, int vote_count, ArrayList<Genre> genres,
            ArrayList<Author> authors, ArrayList<Season> seasons) {
        super();
        this.id = id;
        this.title = title;
        this.plot = plot;
        this.runtime = runtime;
        this.released = released;
        this.poster = poster;
        this.backdrop_path = backdrop_path;
        this.in_production = in_production;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.genres = genres;
        this.authors = authors;
        this.seasons = seasons;
    }
    

    /**
     * Default constructor.
     */
    public Show() {

    }
    
}
