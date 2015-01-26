package com.imie.sylf.view.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imie.sylf.R;
import com.imie.sylf.view.show.ShowListFragment;

/** Genre list fragment.
 *
 * This fragment gives you an interface to list all your Genres.
 *
 * @see android.app.Fragment
 */
public class RandomFragment extends Fragment{
    

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_random, container, false);
        

      FragmentTransaction transaction = getFragmentManager()
              .beginTransaction();
      /*
       * When this container fragment is created, we fill it with our first
       * "real" fragment
       */
      transaction.replace(R.id.random_fragment_list, new ShowListFragment());

      transaction.commit();

        return view;
    }
}
