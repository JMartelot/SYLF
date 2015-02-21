package com.imie.sylf.view.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imie.sylf.R;
import com.imie.sylf.view.random.RandomLatestFragment;
import com.imie.sylf.view.random.RandomSlideFragment;

/** Random tab fragment.
 *
 * This fragment is the root for all the home fragment.
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
      transaction.replace(R.id.random_fragment_slide, new RandomSlideFragment());
      transaction.replace(R.id.random_fragment_latest, new RandomLatestFragment());
      transaction.addToBackStack(null);

      transaction.commit();

      return view;
    }
}
