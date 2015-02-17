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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imie.sylf.R;
import com.imie.sylf.entity.Author;
import com.imie.sylf.entity.Season;
import com.imie.sylf.entity.Show;
import com.imie.sylf.entity.Genre;
import com.imie.sylf.util.DownloadImageTask;
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
    /** writers View. */
    protected TextView writersView;
    /** directors View. */
    protected TextView directorsView;
    /** poster View. */
    protected ImageView posterView;
    /** backdrop View. */
    protected ImageView backdropView;
    /** genres View. */
    protected TextView genresView;
    /** Data layout. */
    protected RelativeLayout dataLayout;
    /** Text view for no Show. */
    protected TextView emptyText;

    /**Constante pour récupérer les tag du flux JSON renvoyé par le webservice*/
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_FIRST_AIR_DATE = "first_air_date";
    private static final String TAG_POSTER = "poster_path";
    private static final String TAG_BACKDROP = "backdrop_path";
    private static final String TAG_RUNTIME = "episode_run_time";
    private static final String TAG_PLOT = "overview";
    private static final String TAG_VOTE_AVERAGE = "vote_average";
    private static final String TAG_VOTE_COUNT = "vote_count";
    private static final String TAG_IN_PRODUCTION = "in_production";

    /** Genres */
    private static final String TAG_GENRES = "genres";
    private static final String TAG_ID_GENRE = "id";
    private static final String TAG_NAME_GENRE = "name";

    /** Show TV's Season */
    private static final String TAG_SEASONS = "seasons";
    private static final String TAG_AIR_DATE_SEASON = "air_date";
    private static final String TAG_NB_EPISODE_SEASON = "episode_count";
    private static final String TAG_ID_SEASON = "id";
    private static final String TAG_POSTER_PATH_SEASON = "poster_path";
    private static final String TAG_SEASON_NB = "season_number";    

    /** Show TV's Author */
    private static final String TAG_AUTHOR = "created_by";    
    private static final String TAG_ID_AUTHOR = "id";
    private static final String TAG_NAME_AUTHOR = "name";
    private static final String TAG_PROFIL_AUTHOR = "profile_path";

    private ArrayList<Author> authorList = new ArrayList<Author>();
    private JSONArray authors = null;    

    private ArrayList<Genre> genreList = new ArrayList<Genre>();
    private JSONArray genres = null;    

    private ArrayList<Season> seasonList = new ArrayList<Season>();
    private JSONArray seasons = null;

    private Show show = new Show();

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
        this.writersView =
                (TextView) view.findViewById(
                        R.id.show_writers);
        this.posterView =
                (ImageView) view.findViewById(
                        R.id.show_poster);
        this.backdropView =
                (ImageView) view.findViewById(
                        R.id.show_backdrop_path);
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
                authors = jsonObj.getJSONArray(TAG_AUTHOR);
                genres = jsonObj.getJSONArray(TAG_GENRES);
                seasons = jsonObj.getJSONArray(TAG_SEASONS);

                for (int i = 0; i < authors.length(); i++) {
                    JSONObject a = authors.getJSONObject(i);
                    Author author = new Author( a.getInt(TAG_ID_AUTHOR), 
                            a.getString(TAG_NAME_AUTHOR), 
                            a.getString(TAG_PROFIL_AUTHOR));

                    authorList.add(author);
                }

                for (int i = 0; i < genres.length(); i++) {
                    JSONObject g = genres.getJSONObject(i);
                    Genre genre = new Genre(g.getInt(TAG_ID_GENRE),g.getString(TAG_NAME_GENRE));

                    genreList.add(genre);
                }

                for (int i = 0; i < seasons.length(); i++) {
                    JSONObject s = seasons.getJSONObject(i);
                    Season season = new Season( s.getInt(TAG_ID_SEASON),
                            s.getString(TAG_AIR_DATE_SEASON), 
                            s.getInt(TAG_NB_EPISODE_SEASON),
                            s.getString(TAG_POSTER_PATH_SEASON),
                            s.getInt(TAG_SEASON_NB),
                            s.getInt(TAG_ID));

                    seasonList.add(season);
                }

                show.setId(jsonObj.getInt(TAG_ID));
                show.setTitle(jsonObj.getString(TAG_NAME));
                show.setPlot(jsonObj.getString(TAG_PLOT));
                show.setRuntime(jsonObj.getString(TAG_RUNTIME));
                show.setReleased(jsonObj.getString(TAG_FIRST_AIR_DATE));
                show.setPoster(jsonObj.getString(TAG_POSTER));
                show.setBackdrop_path(jsonObj.getString(TAG_BACKDROP));
                show.setIn_production(jsonObj.getBoolean(TAG_IN_PRODUCTION));
                show.setVote_average(jsonObj.getDouble(TAG_VOTE_AVERAGE));
                show.setVote_count(jsonObj.getInt(TAG_VOTE_COUNT));
                show.setGenres(genreList);
                show.setAuthors(authorList);
                show.setSeasons(seasonList);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        entityPopulate(show);

    }

    @Override
    public void listPopulate(List<Show> liste) {
        // TODO Auto-generated method stub

    }

    @Override
    public void entityPopulate(Show entity) {
        // TODO Auto-generated method stub
        this.titleView.setText(entity.getTitle());
        this.plotView.setText(entity.getPlot());
        this.runtimeView.setText(entity.getRuntime());
        this.releasedView.setText(entity.getReleased());

        String writersValue = "";
        for (Author item : entity.getAuthors()) {
            writersValue += item.getName() + " ";
        }
        this.writersView.setText(writersValue);

        String genresValue = "";
        for (Genre item : entity.getGenres()) {
            genresValue += item.getTitle() + " ";
        }
        this.genresView.setText(genresValue);
        
        DownloadImageTask dl = new DownloadImageTask(this.posterView);
        dl.execute("https://image.tmdb.org/t/p/w342"+show.getPoster());
        

        DownloadImageTask dl2 = new DownloadImageTask(this.backdropView);
        dl2.execute("https://image.tmdb.org/t/p/w342"+show.getBackdrop_path());
    }
}

