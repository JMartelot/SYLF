package com.imie.sylf.data.Author;

import com.imie.sylf.entity.Author;
import com.imie.sylf.entity.Show;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Async Task to add authors in the database
 * 
 * @author Jean
 *
 */
public class AuthorTask extends AsyncTask<Void, Void, Integer>{
    private Context context;
    private Author author;
    
    /**
     * Constructor
     * @param context
     * @param show
     */
    public AuthorTask(Context context, Author author) {
        this.context = context;
        this.author = author;
    }

    /**
     * Function which insert data in the database
     */
    @Override
    protected Integer doInBackground(Void... params) {

        // On récupère la BDD et on insère un utilisateur
        AuthorSQLiteAdapter adapter = new AuthorSQLiteAdapter(this.context);

        adapter.open();
        this.author.setId(adapter.create(this.author));
        adapter.close();
        
        return author.getId();
    }

    /** 
     * Function which will hide the loader with his message
     */
    @Override
    protected void onPostExecute(Integer result) {
    }
}
