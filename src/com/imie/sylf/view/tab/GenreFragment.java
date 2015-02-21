package com.imie.sylf.view.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imie.sylf.R;
import com.imie.sylf.view.genre.GenreListFragment;

/** Genre tab fragment.
 *
 * This fragment is the root fragment for genre
 *
 * @see android.app.Fragment
 */
public class GenreFragment extends Fragment{
    

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_genre, container, false);
        

        FragmentTransaction transaction = getFragmentManager()
          .beginTransaction();
        /*
         * When this container fragment is created, we fill it with our first
         * "real" fragment
         */
        transaction.replace(R.id.genre_fragment_list, new GenreListFragment());

        transaction.commit();

        return view;
    }
}
