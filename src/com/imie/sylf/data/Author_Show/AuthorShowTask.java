package com.imie.sylf.data.Author_Show;

import com.imie.sylf.entity.Author;
import com.imie.sylf.entity.AuthorShow;
import com.imie.sylf.entity.Show;

import android.content.Context;
import android.os.AsyncTask;


public class AuthorShowTask extends AsyncTask<Void, Void, Integer>{
    private Context context;
    private AuthorShow author_show;
    
    /**
     * Constructor
     * @param context
     * @param show
     */
    public AuthorShowTask(Context context, AuthorShow author_show) {
        this.context = context;
        this.author_show = author_show;
    }

    /**
     * Function which insert data in the database
     */
    @Override
    protected Integer doInBackground(Void... params) {

        // On récupère la BDD et on insère un utilisateur
        AuthorShowSQLiteAdapter adapter = new AuthorShowSQLiteAdapter(this.context);

        adapter.open();
        this.author_show.setId(adapter.create(this.author_show));
        adapter.close();
        
        return author_show.getId();
    }

    /** 
     * Function which will hide the loader with his message
     */
    @Override
    protected void onPostExecute(Integer result) {
    }
}
