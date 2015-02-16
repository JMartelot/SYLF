package com.imie.sylf.view.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.imie.sylf.R;


public class PreferenceFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        View view = inflater.inflate(R.layout.fragment_preferences, container, false);
       
        
        return view;
    }
}
