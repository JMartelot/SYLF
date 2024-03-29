package com.imie.sylf.view.random;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.imie.sylf.R;
import com.imie.sylf.ShowActivity;
import com.imie.sylf.entity.Show;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;
import com.nostra13.universalimageloader.core.ImageLoader;

/** Random list fragment.
 *
 * This fragment gives you an interface to list all your Random.
 *
 * @see android.app.Fragment
 */
public class RandomLatestFragment extends Fragment implements Parser<Show> {


    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_POSTER = "poster_path";
    private List<Show> showList = new ArrayList<Show>();
    private JSONArray shows = null;
    private View view = null;
    private LinearLayout inHorizontalScrollView = null;
    private static final String EXTRA_SHOW = "show";

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_random_latest, container, false);

        this.inHorizontalScrollView = (LinearLayout) this.view.findViewById(R.id.inhorizontalscrollview);

        //Permet de conserver le fragment lors d'une rotation
        setRetainInstance(true);

        WebServices ws = new WebServices(this.getActivity());
        ws.parser = this;
        ws.execute("http://api.themoviedb.org/3/tv/top_rated?api_key=0d2d4cca633bc7bc04a564ac8266d3a1");

        return this.view;
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
                    int id = c.getInt(TAG_ID);
                    String poster = c.getString(TAG_POSTER);

                    show.setTitle(name);
                    show.setId(id);
                    show.setPoster(poster);

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
    
    /**
     * Method to populate and create the horizontal scroll view of the home page
     * @param liste
     */
    public void listPopulate(List<Show> liste) {

        for (Show show : liste) {

            ImageView imageView = new ImageView(this.getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(320, 
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            //Set margin of image's container
            layoutParams.setMargins(10, 0, 10, 0);

            ImageLoader.getInstance().displayImage("https://image.tmdb.org/t/p/w342"+show.getPoster()
                    , imageView); 
            
            imageView.setLayoutParams(layoutParams);
            this.inHorizontalScrollView.addView(imageView);

            final Show item = show;

            imageView.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    Show show = item;
                    Intent intent = new Intent(RandomLatestFragment.this.getActivity(), ShowActivity.class);
                    intent.putExtra(EXTRA_SHOW, show);
                    startActivity(intent);
                }
            });   

        }
    }
}


