package com.imie.sylf.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Entity for a season of a tv show
 * @author Jean
 *
 */
public class Season implements Serializable{

    /**
     * Season id
     */
    private int id;
    /**
     * Season air date
     */
    private String air_date;
    /**
     * Number of episodes for the seasons
     */
    private int nb_episode;
    /**
     * Poster of the season
     */
    private String url_poster;
    /**
     * Season number
     */
    private int season_nb;
    /**
     * Tv show id
     */
    private int tvShow_id;
    /**
     * Getter for the id
     * @return
     */
    public int getId() {
        return id;
    }
    /**
     * Setter for the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter for the air date
     * @return
     */
    public String getAir_date() {
        return air_date;
    }
    /**
     * Setter for the air date
     * @param air_date
     */
    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }
    /**
     * Getter for the number of episodes 
     * @return
     */
    public int getNb_episode() {
        return nb_episode;
    }
    /**
     * Setter for the number of episodes
     * @param nb_episode
     */
    public void setNb_episode(int nb_episode) {
        this.nb_episode = nb_episode;
    }
    /**
     * Getter for the poster
     * @return
     */
    public String getUrl_poster() {
        return url_poster;
    }
    /**
     * Setter for the url poster
     * @param url_poster
     */
    public void setUrl_poster(String url_poster) {
        this.url_poster = url_poster;
    }
    /**
     * Getter for the season number
     * @return
     */
    public int getSeason_nb() {
        return season_nb;
    }
    /**
     * Setter for the season number
     * @param season_nb
     */
    public void setSeason_nb(int season_nb) {
        this.season_nb = season_nb;
    }
    /**
     * Getter for the tv show id
     * @return
     */
    public int getTvShow_id() {
        return tvShow_id;
    }
    /**
     * Setter for the tv show id
     * @param tvShow_id
     */
    public void setTvShow_id(int tvShow_id) {
        this.tvShow_id = tvShow_id;
    }
    /**
     * Constructor with all entity attributes
     * @param id
     * @param air_date
     * @param nb_episode
     * @param url_poster
     * @param season_nb
     * @param tvShow_id
     */
    public Season(int id, String air_date, int nb_episode, String url_poster,
            int season_nb, int tvShow_id) {
        super();
        this.id = id;
        this.air_date = air_date;
        this.nb_episode = nb_episode;
        this.url_poster = url_poster;
        this.season_nb = season_nb;
        this.tvShow_id = tvShow_id;
    }
    /**
     * Empty constructor
     */
    public Season(){
        
    }
}
