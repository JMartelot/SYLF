package com.imie.sylf;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.imie.sylf.view.genre.GenreListFragment;

/**
 * Class for  the favourites management, but unused in the application version 1.0
 * 
 * @author Jean
 *
 */
public class FavouritesActivity extends SherlockFragmentActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profil);

        GenreListFragment genreList = new GenreListFragment();
        
        getSupportFragmentManager().beginTransaction().add(R.id.favourites_fragment_list, genreList).commit();
   
    }

}
