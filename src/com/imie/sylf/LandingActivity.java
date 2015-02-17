package com.imie.sylf;

import com.imie.sylf.util.TabSwipeActivity;
import com.imie.sylf.view.show.ShowListFragment;
import com.imie.sylf.view.tab.GenreFragment;
import com.imie.sylf.view.tab.PreferenceFragment;
import com.imie.sylf.view.tab.ProfilFragment;
import com.imie.sylf.view.tab.RandomFragment;

import android.os.Bundle;

public class LandingActivity extends TabSwipeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     

        setContentView(R.layout.activity_landing);

        getSupportActionBar().hide();  
    }
}
