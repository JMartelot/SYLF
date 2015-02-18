package com.imie.sylf.view.tab;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imie.sylf.R;
import com.imie.sylf.entity.Genre;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilFragment extends Fragment implements Parser<Genre>  {

    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
    private ArrayList<String> years = new ArrayList<String>();
    private List<Genre> genreList = new ArrayList<Genre>();
    private View view;
    private RelativeLayout rl_date;
    private CheckBox cb;
    private JSONArray genres;
    private LinearLayout row;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        
        setRetainInstance(true);
        
        this.view = inflater.inflate(R.layout.activity_profil, container, false);
        
        WebServices ws = new WebServices(this.getActivity());
        ws.parser = this;
        ws.execute("http://api.themoviedb.org/3/genre/tv/list?api_key=0d2d4cca633bc7bc04a564ac8266d3a1");
        
        this.cb = (CheckBox) view.findViewById(R.id.doesnt_matter);
        this.rl_date = (RelativeLayout) this.view.findViewById(R.id.date);
        
        if (!cb.isChecked()) {
            displayDate();
            rl_date.setVisibility(View.VISIBLE);
        }else{
            rl_date.setVisibility(View.GONE);
        }
        
        cb.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(ProfilFragment.this.cb.isChecked()){
                    ProfilFragment.this.rl_date.setVisibility(View.GONE);
                }else{
                    ProfilFragment.this.displayDate();
                    ProfilFragment.this.rl_date.setVisibility(View.VISIBLE);                    
                }
            }
        });

        return view;
    }

    private void displayDate(){
        //Get dates since the year 1930
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1930; i <= thisYear; i++)
        {
            this.years.add(Integer.toString(i));
        }

        //Get date field
        Spinner spinner1 = (Spinner) this.view.findViewById(R.id.start_date);
        Spinner spinner2 = (Spinner) this.view.findViewById(R.id.end_date);

        //Create an adapter for dropdown list with the list of years
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, this.years);

        //Populate dropdown list with the adapter
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(adapter.getPosition(""+thisYear));
    }

    @Override
    public void parseJSON(String stream) {
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
    
    listPopulate(genreList);
        
    }

    @Override
    public void listPopulate(List<Genre> liste) {
        int i =0;

        LinearLayout container = (LinearLayout) this.view.findViewById(R.id.genre);
        
        for (Genre genre : liste) {
            CheckBox cb = new CheckBox(this.getActivity());
            cb.setText(genre.getTitle());
            cb.setId(genre.getId());
            cb.setWidth(350);
            
            if (i%2 == 0) {
                this.row = new LinearLayout(this.getActivity());
                this.row.setOrientation(LinearLayout.HORIZONTAL);
                this.row.addView(cb);
            }else{
                this.row.addView(cb);
                container.addView(this.row);
            }
            i++;
          }
        }

    @Override
    public void entityPopulate(Genre entity) {
        // TODO Auto-generated method stub
        
    }
}
