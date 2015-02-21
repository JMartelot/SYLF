package com.imie.sylf;



import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.imie.sylf.data.SylfSqlLiteOpenHelper;
import com.imie.sylf.data.Author.AuthorSQLiteAdapter;
import com.imie.sylf.data.Author_Show.AuthorShowSQLiteAdapter;
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
import android.database.sqlite.SQLiteDatabase;
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.split_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_profil:
                openProfil();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onBackPressed() {
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
    
    /**
     * Method to initialize the Database and table but unused in this version
     */
    private void initDB(){

        AuthorShowSQLiteAdapter as = new AuthorShowSQLiteAdapter(this);
        AuthorSQLiteAdapter author = new AuthorSQLiteAdapter(this);
        
        as.open();
        as.createTable();
        as.close();
        
        author.open();
        author.createTable();
        author.close();
        
    }

    private void openProfil(){

        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }
    
    /**
     * Unused in this version.
     * 
     */
    private void openFavourites(){

        Intent intent = new Intent(this, FavouritesActivity.class);
        startActivity(intent);
    }


}
