package com.imie.sylf.adapter.show;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imie.sylf.R;
import com.imie.sylf.entity.Show;
import com.imie.sylf.util.DownloadImageTask;

/**
 * 
 * Adapter to create a row for the list of shows
 * 
 * @author Jean
 *
 */
public class ShowAdapter extends ArrayAdapter<Show> {
    private Context context;
    private List<Show> show;

    public ShowAdapter(Context context, List<Show> show) {
        super(context, android.R.layout.simple_list_item_1,show);
        this.context = context;
        this.show = show;
    }

    /* (non-Javadoc)
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Show show = getItem(position);    
        
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_show, parent, false);
        }
        
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.row_show_title);
        // Populate the data into the template view using the data object
        tvTitle.setText(show.getTitle());
        // Return the completed view to render on screen
        

        ImageView poster = (ImageView) convertView.findViewById(R.id.row_show_poster);
        
        DownloadImageTask dl = new DownloadImageTask(poster);
        dl.execute("https://image.tmdb.org/t/p/w342"+show.getPoster());
        
        return convertView;
    }
}
