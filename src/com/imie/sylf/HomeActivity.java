package com.imie.sylf;


import com.imie.sylf.util.TabSwipeActivity;
import com.imie.sylf.view.tab.GenreFragment;
import com.imie.sylf.view.tab.PreferenceFragment;
import com.imie.sylf.view.tab.ProfilFragment;
import com.imie.sylf.view.tab.RandomFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
        addTab( R.drawable.profil, ProfilFragment.class, null );

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
}
