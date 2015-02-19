package com.imie.sylf.view.tab;

import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imie.sylf.R;
import com.imie.sylf.entity.Genre;
import com.imie.sylf.util.Parser;


public class PreferenceFragment extends Fragment implements Parser<Genre> {

    String date;
    String genre;
    String popularity;
    String startDate = "";
    String endDate = "";

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_preferences, container, false);

        loadPreferences();
        
        if (this.popularity.equals("all")) {
            String url = "http://api.themoviedb.org/3/discover/tv" +
                    "?api_key=0d2d4cca633bc7bc04a564ac8266d3a1" +
                    "&sort_by=" + this.popularity +
                    "&with_genres=" + this.genre;

        }else{
            String url = "http://api.themoviedb.org/3/discover/tv" +
                    "?api_key=0d2d4cca633bc7bc04a564ac8266d3a1" +
                    "&sort_by=" + this.popularity +
                    "&first_air_date.gte=1930-01-01" + this.startDate +
                    "&first_air_date.lte=1950-12-31" + this.endDate +
                    "&with_genres=" + this.genre;

        }


        // TODO Send URL to web service and create parser
        
        return view;
    }

    private void loadPreferences(){
        Activity ctx = this.getActivity();
        SharedPreferences preferences = ctx.getSharedPreferences("Pref", ctx.MODE_PRIVATE);

        this.date = preferences.getString("DATES", "all");
        this.popularity = preferences.getString("POPULARITY", "popularity");
        this.genre = preferences.getString("GENRES", "NOTHING");

        this.genre.trim();
        this.genre.replaceAll(" ", ",");

        if (!this.date.equals("all")) {
            String[] dates = this.popularity.split("-");
            this.startDate = dates[0]+"01-01";
            this.endDate = dates[1]+"12-31";
        }

        if (this.popularity.equals("popularity")) {
            this.popularity = "popularity.desc";
        }else{
            this.popularity = "vote_average.desc";
        }

    }

    @Override
    public void parseJSON(String output) {
        // TODO Auto-generated method stub

    }

    @Override
    public void listPopulate(List<Genre> liste) {
        // TODO Auto-generated method stub

    }

    @Override
    public void entityPopulate(Genre entity) {
        // TODO Auto-generated method stub

    }
}
