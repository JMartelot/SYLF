package com.imie.sylf.view.random;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.imie.sylf.R;
import com.imie.sylf.adapter.genre.GenreAdapter;
import com.imie.sylf.entity.Genre;
import com.imie.sylf.entity.Show;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.WebServices;
import com.imie.sylf.view.show.ShowListFragment;

/** Genre list fragment.
 *
 * This fragment gives you an interface to list all your Genres.
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
    private GenreAdapter adapter = null;
    private LinearLayout inHorizontalScrollView = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
                
        this.view = inflater.inflate(R.layout.fragment_random_latest, null);

        this.inHorizontalScrollView = (LinearLayout) this.view.findViewById(R.id.inhorizontalscrollview);
        
        //Permet de conserver le fragment lors d'une rotation
        setRetainInstance(true);
        
        if(this.adapter == null){
            WebServices ws = new WebServices(this.getActivity());
            ws.parser = this;
            ws.execute("http://api.themoviedb.org/3/tv/top_rated?api_key=0d2d4cca633bc7bc04a564ac8266d3a1");
        }else{
        }
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
    
    @Override
    public void listPopulate(List<Show> liste) {
        
        for (Show show : liste) {

            ImageView imageView = new ImageView(this.getActivity());
            
            DownloadImageTask dl = new DownloadImageTask(imageView, inHorizontalScrollView);
            dl.execute("https://image.tmdb.org/t/p/w342"+show.getPoster());
        }
    }

    @Override
    public void entityPopulate(Show entity) {
        // TODO Auto-generated method stub
        
    }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    LinearLayout scrollView;
    
    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }
    
    public DownloadImageTask(ImageView bmImage, LinearLayout scrollView ) {
        this.bmImage = bmImage;
        this.scrollView = scrollView;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
        scrollView.addView(bmImage);
    }
}
