package com.imie.sylf;


import java.util.ArrayList;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.imie.sylf.data.Show.ShowSQLiteAdapter;
import com.imie.sylf.entity.Show;
import com.imie.sylf.util.TabSwipeActivity;
import com.imie.sylf.view.tab.GenreFragment;
import com.imie.sylf.view.tab.PreferenceFragment;
import com.imie.sylf.view.tab.RandomFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Intent;
import android.os.Bundle;

public class HomeActivity extends TabSwipeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initUIL();

        addTab( R.drawable.random, RandomFragment.class, null );
        addTab( R.drawable.favorite, PreferenceFragment.class,null );
        addTab( R.drawable.genre, GenreFragment.class, null ); 

        ShowSQLiteAdapter showAdapter = new ShowSQLiteAdapter(this);        

        showAdapter.open();     
        //Valeurs à afficher dans la liste
        ArrayList<Show> shows = showAdapter.getShows();
        showAdapter.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.split_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_profil) {
            openProfil();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUIL(){

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .threadPoolSize(5)
        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config);
    }

    private void openProfil(){

        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }


}
