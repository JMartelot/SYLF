package com.imie.sylf;

import com.imie.sylf.util.TabSwipeActivity;
import com.imie.sylf.view.show.ShowListFragment;
import com.imie.sylf.view.tab.GenreFragment;
import com.imie.sylf.view.tab.PreferenceFragment;
import com.imie.sylf.view.tab.ProfilFragment;
import com.imie.sylf.view.tab.RandomFragment;

import android.os.Bundle;

public class HomeActivity extends TabSwipeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
                    
        addTab( getText(R.string.random), RandomFragment.class, null );
        addTab( getText(R.string.pref), PreferenceFragment.class,null );
        addTab( getText(R.string.category), GenreFragment.class, null ); 
        addTab( getText(R.string.profil), ProfilFragment.class, ProfilFragment.createBundle((String) getText(R.string.profil)) );
    }
    
}
