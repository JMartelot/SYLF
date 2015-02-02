/**************************************************************************
 * GenreListFragment.java, Sylf Android
 *
 * Copyright 2014
 * Description : 
 * Author(s)   : Harmony
 * Licence     : 
 * Last update : Dec 17, 2014
 *
 **************************************************************************/
package com.imie.sylf.view.genre;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

import com.imie.sylf.R;
import com.imie.sylf.adapter.genre.GenreAdapter;
import com.imie.sylf.entity.Genre;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;
import com.imie.sylf.view.show.ShowListFragment;

/** Genre list fragment.
 *
 * This fragment gives you an interface to list all your Genres.
 *
 * @see android.app.Fragment
 */
public class GenreListFragment extends Fragment implements Parser<Genre> {
    

    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
    private List<Genre> genreList = new ArrayList<Genre>();
    private JSONArray genres = null;
    private View view = null;
    private GenreAdapter adapter = null;
    private ListView lv = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
                
        view = inflater.inflate(R.layout.fragment_genre_list, null);
        
        setRetainInstance(true);
        
        this.lv = (ListView)view.findViewById(R.id.liste_genre);
        
        if(this.adapter == null){
            WebServices ws = new WebServices(this.getActivity());
            ws.parser = this;
            ws.execute("http://api.themoviedb.org/3/genre/tv/list?api_key=0d2d4cca633bc7bc04a564ac8266d3a1");
        }else{
            lv.setAdapter(adapter);
        }
        
        lv.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                    long id) {
                
                Genre genre = (Genre) lv.getItemAtPosition(position);
                
                // Create new fragment and transaction
                Fragment newFragment = new ShowListFragment();
                
                Bundle b = new Bundle();
                b.putSerializable("GENRE", genre);
                
                newFragment.setArguments(b);
                
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.genre_fragment_list, newFragment);
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
                 genres = jsonObj.getJSONArray("genres");

                // looping through All Contacts
                for (int i = 0; i < genres.length(); i++) {
                    JSONObject c = genres.getJSONObject(i);
                    Genre genre = new Genre();   
                     
                    String name = c.getString(TAG_NAME);
                    String id = c.getString(TAG_ID);

                    genre.setTitle(name);
                    genre.setId(Integer.parseInt(id));

                    genreList.add(genre);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
        
        addDataBase(genreList);
    }
    
    @Override
    public void addDataBase(List liste) {
        
        this.adapter = new GenreAdapter(this.getActivity(), liste);
        
        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.liste_genre);
        
        listView.setAdapter(adapter);
    }
}
