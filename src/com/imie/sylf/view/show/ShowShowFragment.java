/**************************************************************************
 * ShowShowFragment.java, Sylf Android
 *
 * Copyright 2014
 * Description : 
 * Author(s)   : Harmony
 * Licence     : 
 * Last update : Dec 17, 2014
 *
 **************************************************************************/
package com.imie.sylf.view.show;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imie.sylf.R;
import com.imie.sylf.entity.Show;
import com.imie.sylf.entity.Genre;

/** Show show fragment.
 *
 * This fragment gives you an interface to show a Show.
 * 
 * @see android.app.Fragment
 */
public class ShowShowFragment extends Fragment{
    /** Model data. */
    protected Show model;

    /* This entity's fields views */
    /** title View. */
    protected TextView titleView;
    /** plot View. */
    protected TextView plotView;
    /** runtime View. */
    protected TextView runtimeView;
    /** released View. */
    protected TextView releasedView;
    /** actors View. */
    protected TextView actorsView;
    /** writers View. */
    protected TextView writersView;
    /** directors View. */
    protected TextView directorsView;
    /** poster View. */
    protected TextView posterView;
    /** genres View. */
    protected TextView genresView;
    /** Data layout. */
    protected RelativeLayout dataLayout;
    /** Text view for no Show. */
    protected TextView emptyText;


    /** Initialize view of curr.fields.
     *
     * @param view The layout inflating
     */
    protected void initializeComponent(final View view) {
        this.titleView =
                (TextView) view.findViewById(
                        R.id.show_title);
        this.plotView =
                (TextView) view.findViewById(
                        R.id.show_plot);
        this.runtimeView =
                (TextView) view.findViewById(
                        R.id.show_runtime);
        this.releasedView =
                (TextView) view.findViewById(
                        R.id.show_released);
        this.actorsView =
                (TextView) view.findViewById(
                        R.id.show_actors);
        this.writersView =
                (TextView) view.findViewById(
                        R.id.show_writers);
        this.directorsView =
                (TextView) view.findViewById(
                        R.id.show_directors);
        this.posterView =
                (TextView) view.findViewById(
                        R.id.show_poster);

        this.genresView =
                (TextView) view.findViewById(
                        R.id.show_genres);

        this.dataLayout =
                (RelativeLayout) view.findViewById(
                        R.id.show_data_layout);

        this.emptyText =
                (TextView) view.findViewById(
                        R.id.show_empty);
    }

    /** Load data from model to fields view. */
    public void loadData() {
        if (this.model != null) {

            this.dataLayout.setVisibility(View.VISIBLE);
            this.emptyText.setVisibility(View.GONE);


            if (this.model.getTitle() != null) {
                this.titleView.setText(this.model.getTitle());
            }
            if (this.model.getPlot() != null) {
                this.plotView.setText(this.model.getPlot());
            }
            if (this.model.getRuntime() != null) {
                this.runtimeView.setText(this.model.getRuntime());
            }
            if (this.model.getReleased() != null) {
                this.releasedView.setText(this.model.getReleased());
            }
            if (this.model.getActors() != null) {
                this.actorsView.setText(this.model.getActors());
            }
            if (this.model.getWriters() != null) {
                this.writersView.setText(this.model.getWriters());
            }
            if (this.model.getDirectors() != null) {
                this.directorsView.setText(this.model.getDirectors());
            }
            if (this.model.getPoster() != null) {
                this.posterView.setText(this.model.getPoster());
            }
            if (this.model.getGenres() != null) {
                String genresValue = "";
                for (Genre item : this.model.getGenres()) {
                    genresValue += item.getId() + ",";
                }
                this.genresView.setText(genresValue);
            }
        } else {
            this.dataLayout.setVisibility(View.GONE);
            this.emptyText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view =
                inflater.inflate(
                        R.layout.fragment_show_show,
                        container,
                        false); 

        this.initializeComponent(view);        

        //Permet de conserver le fragment lors d'une rotation
        setRetainInstance(true);

//        final Intent intent =  getActivity().getIntent();
//        this.update((Show) intent.getParcelableExtra(ShowContract.PARCEL));

        return view;
    }

}

