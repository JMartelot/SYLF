package com.imie.sylf.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Entity Season
 * @author Quentin
 *
 */
public class Season implements Serializable{

    
    private int id;
    
    private String air_date;
    
    private int nb_episode;
    
    private String url_poster;
    
    private int season_nb;
    
    private int tvShow_id;

    /**
     * get id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * set id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get Air date
     * @return
     */
    public String getAir_date() {
        return air_date;
    }

    /**
     * set air date
     * @param air_date
     */
    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    /**
     * get nb episode
     * @return
     */
    public int getNb_episode() {
        return nb_episode;
    }

    /**
     * set nb episode
     * @param nb_episode
     */
    public void setNb_episode(int nb_episode) {
        this.nb_episode = nb_episode;
    }

    /**
     * get url poster
     * @return
     */
    public String getUrl_poster() {
        return url_poster;
    }

    /**
     * set url poster
     * @param url_poster
     */
    public void setUrl_poster(String url_poster) {
        this.url_poster = url_poster;
    }

    /**
     * get season
     * @return
     */
    public int getSeason_nb() {
        return season_nb;
    }

    /**
     * set season
     * @param season_nb
     */
    public void setSeason_nb(int season_nb) {
        this.season_nb = season_nb;
    }

    /**
     * get Tv Show
     * @return
     */
    public int getTvShow_id() {
        return tvShow_id;
    }

    /**
     * set Tv Show
     * @param tvShow_id
     */
    public void setTvShow_id(int tvShow_id) {
        this.tvShow_id = tvShow_id;
    }

    /**
     * Default constructor
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
    
    public Season(){
        
    }
}
