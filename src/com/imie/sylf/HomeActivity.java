package com.imie.sylf;

import java.util.ArrayList;
import java.util.List;

import com.imie.sylf.entity.Genre;
import com.imie.sylf.util.Parser;
import com.imie.sylf.util.TabSwipeActivity;
import com.imie.sylf.view.show.ShowListFragment;
import com.imie.sylf.view.tab.GenreFragment;
import com.imie.sylf.view.tab.PreferenceFragment;
import com.imie.sylf.view.tab.ProfilFragment;
import com.imie.sylf.view.tab.RandomFragment;

import android.os.Bundle;
import android.widget.ImageView;

public class HomeActivity extends TabSwipeActivity{
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
                    
        addTab( R.drawable.random, RandomFragment.class, null );
        addTab( R.drawable.favorite, PreferenceFragment.class,null );
        addTab( R.drawable.genre, GenreFragment.class, null ); 
        addTab( R.drawable.profil, ProfilFragment.class, null );
    }
}
