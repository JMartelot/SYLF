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

import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.imie.sylf.HomeActivity;
import com.imie.sylf.R;
import com.imie.sylf.adapter.genre.GenreAdapter;
import com.imie.sylf.adapter.show.ShowAdapter;
import com.imie.sylf.entity.Show;

/** Show list fragment.
 *
 * This fragment gives you an interface to list all your Shows.
 *
 * @see android.app.Fragment
 */
public class ShowListFragment extends Fragment
        implements Parser<Show>{

    private static final String TAG_ORIGINAL_NAME = "original_name";
    private static final String TAG_NAME = "name";
    private static final String TAG_FIRST_AIR_DATE = "first_air_date";
    private static final String TAG_ORIGIN_COUNTRY = "origin_country";
    private static final String TAG_POSTER = "poster_path";
    private static final String TAG_POPULARITY = "popularity";
    private static final String TAG_NOTE = "vote_average";
    private static final String TAG_VOTE = "vote_count";
    
    private List<Show> showList = new ArrayList<Show>();
    private JSONArray shows = null;
    private View view = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_show_list, null);
         
        ListView lv = (ListView)view.findViewById(R.id.liste_show);
        
        WebServices ws = new WebServices(this.getActivity());
        ws.parser = this;
        ws.execute("http://api.themoviedb.org/3/discover/tv?api_key=0d2d4cca633bc7bc04a564ac8266d3a1&sort_by=popularity.desc");
        
        
        lv.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                    long id) {
                
             // Create new fragment and transaction
                Fragment newFragment = new ShowShowFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.random_fragment_list, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
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
                     
                    String name = c.getString(TAG_NAME);
                    String original_name = c.getString(TAG_ORIGINAL_NAME);
                    String country = c.getString(TAG_ORIGIN_COUNTRY);
                    String popularity = c.getString(TAG_POPULARITY);
                    String poster = c.getString(TAG_POSTER);
                    String note = c.getString(TAG_NOTE);
                    String vote = c.getString(TAG_VOTE);
                    String first_air_date = c.getString(TAG_FIRST_AIR_DATE);

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

        ShowAdapter adapter = new ShowAdapter(this.getActivity(), liste);
        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.liste_show);
        listView.setAdapter(adapter);
    }

}
