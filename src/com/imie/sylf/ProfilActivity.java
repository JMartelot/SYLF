package com.imie.sylf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.imie.sylf.entity.Genre;
import com.imie.sylf.entity.Show;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;

public class ProfilActivity extends SherlockActivity implements Parser<Genre> {
    
    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "id";
    private ArrayList<String> years = new ArrayList<String>();
    private List<Genre> genreList = new ArrayList<Genre>();
    private RelativeLayout rl_date;
    private JSONArray genres;
    private LinearLayout row;
    private List<CheckBox> cbGenres = new ArrayList<CheckBox>();
    
    private CheckBox doesntmatter;
    private Spinner startDate;
    private Spinner endDate;
    private CheckBox popularity;
    private CheckBox note;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profil);
        
        initializeComponent();

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        WebServices ws = new WebServices(this);
        ws.parser = this;
        ws.execute("http://api.themoviedb.org/3/genre/tv/list?api_key=0d2d4cca633bc7bc04a564ac8266d3a1");
            
        this.rl_date = (RelativeLayout) findViewById(R.id.date);
        
        
        if (!doesntmatter.isChecked()) {
            displayDate();
            rl_date.setVisibility(View.VISIBLE);
        }else{
            rl_date.setVisibility(View.GONE);
        }
        
        doesntmatter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(ProfilActivity.this.doesntmatter.isChecked()){
                    ProfilActivity.this.rl_date.setVisibility(View.GONE);
                }else{
                    ProfilActivity.this.displayDate();
                    ProfilActivity.this.rl_date.setVisibility(View.VISIBLE);                    
                }
            }
        });
    }


    private void initializeComponent() {
        this.doesntmatter = (CheckBox) findViewById(R.id.doesnt_matter);
        this.startDate = (Spinner) findViewById(R.id.start_date);
        this.endDate = (Spinner) findViewById(R.id.end_date);
        this.popularity = (CheckBox) findViewById(R.id.cb_popularite);
        this.note = (CheckBox) findViewById(R.id.cb_note);
    }
    
    private void loadGenres(){
        SharedPreferences preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        
        String genre = preferences.getString("GENRES", "RIEN");
        
        genre.trim();
        String[] genres = genre.split(" ");
        
        if (this.cbGenres.size() != 0) {
            for (CheckBox cb : cbGenres) {
                for (int i = 0; i < genres.length; i++) {
                    if(cb.getId() == Integer.parseInt(genres[i])){
                        cb.setChecked(true);
                    }
                }
            }
        }
    }

    
    private void displayDate(){
        //Get dates since the year 1930
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1930; i <= thisYear; i++)
        {
            this.years.add(Integer.toString(i));
        }

        //Get date field
        Spinner spinner1 = (Spinner) findViewById(R.id.start_date);
        Spinner spinner2 = (Spinner) findViewById(R.id.end_date);

        //Create an adapter for dropdown list with the list of years
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, this.years);

        //Populate dropdown list with the adapter
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(adapter.getPosition(""+thisYear));
    }
    
    

    @Override
    protected void onStop() {
        
        SharedPreferences preferences = getSharedPreferences("Pref", MODE_PRIVATE);

        Editor editor = preferences.edit();
        String genres = "";
        String dates = "All";
        
        if (this.cbGenres.size() != 0) {
            for (CheckBox cb : cbGenres) {
                if(cb.isChecked()){
                    genres = genres + Integer.toString(cb.getId()) + " ";
                }
            }
        }
        
        if(!this.doesntmatter.isChecked()){
            dates = this.startDate + "-" + this.endDate;
        }
        
        editor.putString("GENRES", genres);
        editor.putString("DATES", dates);
        editor.commit();
        
        super.onStop();
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

                this.genreList.add(genre);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    } else {
        Log.e("ServiceHandler", "Couldn't get any data from the url");
    }
    
    listPopulate(this.genreList);
        
    }

    @Override
    public void listPopulate(List<Genre> liste) {
        int i =0;

        LinearLayout container = (LinearLayout) findViewById(R.id.genre);
        
        for (Genre genre : liste) {
            CheckBox cb = new CheckBox(this);
            cb.setText(genre.getTitle());
            cb.setId(genre.getId());
            cb.setWidth(350);
            
            if (i%2 == 0) {
                this.row = new LinearLayout(this);
                this.row.setOrientation(LinearLayout.HORIZONTAL);
                this.row.addView(cb);
            }else{
                this.row.addView(cb);
                container.addView(this.row);
            }
            this.cbGenres.add(cb);
            i++;
          }
        loadGenres();
        }

    @Override
    public void entityPopulate(Genre entity) {
        // TODO Auto-generated method stub
        
    }
}
