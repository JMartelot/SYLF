package com.imie.sylf.data.Show;

import com.imie.sylf.entity.Show;

import android.content.Context;
import android.os.AsyncTask;

/**
 * AsynTask used to insert a new show in the database
 * @author Jean
 *
 */
public class ShowTask extends AsyncTask<Void, Void, Integer>{
    private Context context;
    private Show show;
    
    /**
     * Constructor
     * @param context
     * @param show
     */
    public ShowTask(Context context, Show show) {
        this.context = context;
        this.show = show;
    }

    /**
     * Function which insert data in the database
     */
    @Override
    protected Integer doInBackground(Void... params) {

        // On récupère la BDD et on insère un utilisateur
        ShowSQLiteAdapter adapter = new ShowSQLiteAdapter(this.context);

        adapter.open();
        this.show.setId(adapter.create(this.show));
        adapter.close();
        
        return show.getId();
    }

    /** 
     * Function which will hide the loader with his message
     */
    @Override
    protected void onPostExecute(Integer result) {
    }
}
