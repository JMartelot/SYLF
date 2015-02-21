package com.imie.sylf.view.tab;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.imie.sylf.R;
import com.imie.sylf.ShowActivity;
import com.imie.sylf.adapter.show.ShowAdapter;
import com.imie.sylf.entity.Show;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;


public class PreferenceFragment extends Fragment implements Parser<Show> {

    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_POSTER = "poster_path";
    private static final String TAG_FIRST_AIR_DATE = "first_air_date";
    private static final String EXTRA_SHOW = "show";

    String date;
    String genre;
    String popularity;
    String startDate = "";
    String endDate = "";
    String url = "";
    private ShowAdapter adapter = null;
    private ListView lv = null;
    private List<Show> showList = new ArrayList<Show>();
    private JSONArray shows = null;
    private View view = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.view = inflater.inflate(R.layout.fragment_preferences, container, false);

        loadPreferences();
        
        if (this.popularity.equals("all")) {
            this.url = "http://api.themoviedb.org/3/discover/tv" +
                    "?api_key=0d2d4cca633bc7bc04a564ac8266d3a1" +
                    "&sort_by=" + this.popularity +
                    "&with_genres=" + this.genre;

        }else{
            this.url = "http://api.themoviedb.org/3/discover/tv" +
                    "?api_key=0d2d4cca633bc7bc04a564ac8266d3a1" +
                    "&sort_by=" + this.popularity +
                    "&first_air_date.gte=" + this.startDate +
                    "&first_air_date.lte=" + this.endDate +
                    "&with_genres=" + this.genre;
        }

        LinearLayout progressBar = (LinearLayout) view.findViewById(R.id.pref_showProgressLayout);   
        RelativeLayout showContainer = (RelativeLayout) view.findViewById(R.id.pref_showListContainer);  

        this.lv = (ListView)view.findViewById(R.id.pref_liste_show);

        if (this.adapter == null){
            
            WebServices ws = new WebServices(this.getActivity(), progressBar, showContainer);
            ws.parser = this;
            ws.execute(url);

        }else{
            this.lv.setAdapter(adapter);
        }
        

        this.lv.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                    long id) {

                Show show = (Show) PreferenceFragment.this.lv.getItemAtPosition(position);           

                Intent intent = new Intent(PreferenceFragment.this.getActivity(), ShowActivity.class);
                intent.putExtra(EXTRA_SHOW, show);
                startActivity(intent);
            }
        });
        
        return view;
    }

    private void loadPreferences(){
        Activity ctx = this.getActivity();
        SharedPreferences preferences = ctx.getSharedPreferences("Pref", ctx.MODE_PRIVATE);

        this.date = preferences.getString("DATES", "all");
        this.popularity = preferences.getString("POPULARITY", "popularity");
        this.genre = preferences.getString("GENRES", "NOTHING");

        this.genre.trim();
        this.genre = this.genre.replaceAll(" ", "|");

        if (!this.date.equals("all")) {
            String[] dates = this.date.split("-");
            this.startDate = dates[0]+"-01-01";
            this.endDate = dates[1]+"-12-31";
        }

        if (this.popularity.equals("popularity")) {
            this.popularity = "popularity.desc";
        }else{
            this.popularity = "vote_average.desc";
        }

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
                    String poster = c.getString(TAG_POSTER);
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

        listPopulate(showList);

    }

    public void listPopulate(List<Show> liste) {

        this.adapter = new ShowAdapter(this.getActivity(), liste);
        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.pref_liste_show);
        listView.setAdapter(adapter);

    }
}
