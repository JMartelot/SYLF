/**************************************************************************
 * ShowListFragment.java, Sylf Android
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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

import com.imie.sylf.R;
import com.imie.sylf.ShowActivity;
import com.imie.sylf.adapter.show.ShowAdapter;
import com.imie.sylf.entity.Genre;
import com.imie.sylf.entity.Show;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;

/** Show list fragment.
 *
 * This fragment gives you an interface to list all your Shows.
 *
 * @see android.app.Fragment
 */
public class ShowListFragment extends Fragment
        implements Parser<Show>{

    private static final String TAG_ID = "id";
    private static final String TAG_ORIGINAL_NAME = "original_name";
    private static final String TAG_NAME = "name";
    private static final String TAG_POSTER = "poster_path";
    private static final String TAG_FIRST_AIR_DATE = "first_air_date";
    private static final String TAG_ORIGIN_COUNTRY = "origin_country";
    private static final String TAG_POPULARITY = "popularity";
    private static final String TAG_NOTE = "vote_average";
    private static final String TAG_VOTE = "vote_count";
    private static final String EXTRA_SHOW = "show";
    
    private List<Show> showList = new ArrayList<Show>();
    private JSONArray shows = null;
    private View view = null;
    private ShowAdapter adapter = null;
    private ListView lv = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_show_list, null);

        //Permet de conserver le fragment lors d'une rotation
        setRetainInstance(true);
        
        LinearLayout progressBar = (LinearLayout) view.findViewById(R.id.showProgressLayout);	
        RelativeLayout showContainer = (RelativeLayout) view.findViewById(R.id.showListContainer);	
        
        Genre genre = (Genre) getArguments().getSerializable("GENRE");

        this.lv = (ListView)view.findViewById(R.id.liste_show);
        
        if (this.adapter == null){
            String url = "http://api.themoviedb.org/3/discover/tv?api_key=0d2d4cca633bc7bc04a564ac8266d3a1&sort_by=popularity.desc&with_genres="+ genre.getId();
            
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
                
                Show show = (Show) ShowListFragment.this.lv.getItemAtPosition(position);
                
                              
                
              Intent intent = new Intent(ShowListFragment.this.getActivity(), ShowActivity.class);
              intent.putExtra(EXTRA_SHOW, show);
              startActivity(intent);
              
            }
            
        });
        
        return view;
    }
    
    @Override
    public void parseJSON(String stream){
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
        
        listPopulate(showList);
    }
    
    @Override
    public void listPopulate(List<Show> liste) {

        this.adapter = new ShowAdapter(this.getActivity(), liste);
        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.liste_show);
        listView.setAdapter(adapter);
    }

    @Override
    public void entityPopulate(Show entity) {
        // TODO Auto-generated method stub
        
    }

}
