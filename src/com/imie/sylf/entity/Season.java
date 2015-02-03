package com.imie.sylf.entity;

import java.io.Serializable;
import java.sql.Date;

public class Season implements Serializable{

    
    private int id;
    
    private String air_date;
    
    private int nb_episode;
    
    private String url_poster;
    
    private int season_nb;
    
    private int tvShow_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public int getNb_episode() {
        return nb_episode;
    }

    public void setNb_episode(int nb_episode) {
        this.nb_episode = nb_episode;
    }

    public String getUrl_poster() {
        return url_poster;
    }

    public void setUrl_poster(String url_poster) {
        this.url_poster = url_poster;
    }

    public int getSeason_nb() {
        return season_nb;
    }

    public void setSeason_nb(int season_nb) {
        this.season_nb = season_nb;
    }

    public int getTvShow_id() {
        return tvShow_id;
    }

    public void setTvShow_id(int tvShow_id) {
        this.tvShow_id = tvShow_id;
    }

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
