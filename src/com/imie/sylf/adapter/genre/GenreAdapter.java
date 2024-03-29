package com.imie.sylf.adapter.genre;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.imie.sylf.R;
import com.imie.sylf.entity.Genre;

/**
 * Adapter to create a row for the list of genres
 * 
 * @author Jean
 *
 */
public class GenreAdapter extends ArrayAdapter<Genre> {
    private Context context;
    private List<Genre> genre;
    
    /**
     * Constructor
     * 
     * @param context
     * @param genre
     */
    public GenreAdapter(Context context, List<Genre> genre) {
        super(context, android.R.layout.simple_list_item_1,genre);
        this.context = context;
        this.genre = genre;
    }

    /* (non-Javadoc)
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Genre genre = getItem(position);    
        
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_genre, parent, false);
        }
        
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.row_genre_title);
        // Populate the data into the template view using the data object
        tvTitle.setText(genre.getTitle());
        // Return the completed view to render on screen
        return convertView;
    }
}
