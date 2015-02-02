/**************************************************************************
 * ShowShowFragment.java, Sylf Android
 *
 * Copyright 2014
 * Description : 
 * Author(s)   : Harmony
 * Licence     : 
 * Last update : Dec 17, 2014
 *
 **************************************************************************/
package com.imie.sylf.view.show;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imie.sylf.R;
import com.imie.sylf.entity.Show;
import com.imie.sylf.entity.Genre;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;

/** Show show fragment.
 *
 * This fragment gives you an interface to show a Show.
 * 
 * @see android.app.Fragment
 */
public class ShowShowFragment extends Fragment
    implements Parser<Show>{
    /** Model data. */
    protected Show model;

    /* This entity's fields views */
    /** title View. */
    protected TextView titleView;
    /** plot View. */
    protected TextView plotView;
    /** runtime View. */
    protected TextView runtimeView;
    /** released View. */
    protected TextView releasedView;
    /** actors View. */
    protected TextView actorsView;
    /** writers View. */
    protected TextView writersView;
    /** directors View. */
    protected TextView directorsView;
    /** poster View. */
    protected TextView posterView;
    /** genres View. */
    protected TextView genresView;
    /** Data layout. */
    protected RelativeLayout dataLayout;
    /** Text view for no Show. */
    protected TextView emptyText;
    
    /**Constante pour récupérer les tag du flux JSON renvoyé apr le webservice*/
    private static final String TAG_ID = "id";
    private static final String TAG_ORIGINAL_NAME = "original_name";
    private static final String TAG_NAME = "name";
    private static final String TAG_FIRST_AIR_DATE = "first_air_date";
    private static final String TAG_ORIGIN_COUNTRY = "origin_country";
    private static final String TAG_POSTER = "poster_path";
    private static final String TAG_POPULARITY = "popularity";
    private static final String TAG_NOTE = "vote_average";
    private static final String TAG_VOTE = "vote_count";
    private static final String TAG_PLOT = "vote_count";
    

    private List<Show> showList = new ArrayList<Show>();
    private JSONArray shows = null;


    /** Initialize view of curr.fields.
     *
     * @param view The layout inflating
     */
    protected void initializeComponent(final View view) {
        this.titleView =
                (TextView) view.findViewById(
                        R.id.show_title);
        this.plotView =
                (TextView) view.findViewById(
                        R.id.show_plot);
        this.runtimeView =
                (TextView) view.findViewById(
                        R.id.show_runtime);
        this.releasedView =
                (TextView) view.findViewById(
                        R.id.show_released);
        this.actorsView =
                (TextView) view.findViewById(
                        R.id.show_actors);
        this.writersView =
                (TextView) view.findViewById(
                        R.id.show_writers);
        this.directorsView =
                (TextView) view.findViewById(
                        R.id.show_directors);
        this.posterView =
                (TextView) view.findViewById(
                        R.id.show_poster);

        this.genresView =
                (TextView) view.findViewById(
                        R.id.show_genres);

        this.dataLayout =
                (RelativeLayout) view.findViewById(
                        R.id.show_data_layout);

        this.emptyText =
                (TextView) view.findViewById(
                        R.id.show_empty);
    }

    /** Load data from model to fields view. */
    public void loadData() {
        if (this.model != null) {

            this.dataLayout.setVisibility(View.VISIBLE);
            this.emptyText.setVisibility(View.GONE);


            if (this.model.getTitle() != null) {
                this.titleView.setText(this.model.getTitle());
            }
            if (this.model.getPlot() != null) {
                this.plotView.setText(this.model.getPlot());
            }
            if (this.model.getRuntime() != null) {
                this.runtimeView.setText(this.model.getRuntime());
            }
            if (this.model.getReleased() != null) {
                this.releasedView.setText(this.model.getReleased());
            }
            if (this.model.getActors() != null) {
                this.actorsView.setText(this.model.getActors());
            }
            if (this.model.getWriters() != null) {
                this.writersView.setText(this.model.getWriters());
            }
            if (this.model.getDirectors() != null) {
                this.directorsView.setText(this.model.getDirectors());
            }
            if (this.model.getPoster() != null) {
                this.posterView.setText(this.model.getPoster());
            }
            if (this.model.getGenres() != null) {
                String genresValue = "";
                for (Genre item : this.model.getGenres()) {
                    genresValue += item.getId() + ",";
                }
                this.genresView.setText(genresValue);
            }
        } else {
            this.dataLayout.setVisibility(View.GONE);
            this.emptyText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view =
                inflater.inflate(
                        R.layout.fragment_show_show,
                        container,
                        false); 

        this.initializeComponent(view);        

        //Permet de conserver le fragment lors d'une rotation
        setRetainInstance(true);

        Show show = (Show) getArguments().getSerializable("SHOW");
        
        String url = "http://api.themoviedb.org/3/tv/"+ show.getId() +"?api_key=0d2d4cca633bc7bc04a564ac8266d3a1";
        
        WebServices ws = new WebServices(this.getActivity());
        ws.parser = this;
        ws.execute(url);
        
        return view;
    }

    @Override
    public void parseJSON(String stream) {
        if (stream != null) {
            try {
                JSONObject jsonObj = new JSONObject(stream);
                 
                // Getting JSON Array node
                 shows = jsonObj.getJSONArray("results");

                // looping through All Contacts
                for (int i = 0; i < shows.length(); i++) {
                    JSONObject c = shows.getJSONObject(i);
                    Show show = new Show();   

                    String id = c.getString(TAG_ID);
                    String name = c.getString(TAG_NAME);
                    String original_name = c.getString(TAG_ORIGINAL_NAME);
                    String country = c.getString(TAG_ORIGIN_COUNTRY);
                    String popularity = c.getString(TAG_POPULARITY);
                    String poster = c.getString(TAG_POSTER);
                    String note = c.getString(TAG_NOTE);
                    String vote = c.getString(TAG_VOTE);
                    String first_air_date = c.getString(TAG_FIRST_AIR_DATE);

                    show.setId(Integer.parseInt(id));
                    show.setTitle(name);
                    show.setPoster("https://image.tmdb.org/t/p/w92".concat(poster));
                    show.setReleased(first_air_date);
                    showList.add(show);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
        
        addDataBase(showList);
        
    }

    @Override
    public void addDataBase(List<Show> liste) {
        // TODO Auto-generated method stub
        
    }

}

