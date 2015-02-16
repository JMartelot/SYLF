package com.imie.sylf.view.tab;

import java.util.ArrayList;
import java.util.Calendar;

import com.imie.sylf.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfilFragment extends Fragment {

    private ArrayList<String> years = new ArrayList<String>();
    private View view = null;
    private RelativeLayout rl = null;
    private Fragment fragment = null;
    private CheckBox cb = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        this.view = inflater.inflate(R.layout.fragment_profil, container, false);

        this.cb = (CheckBox) view.findViewById(R.id.doesnt_matter);
        this.rl = (RelativeLayout) this.view.findViewById(R.id.date);

        if (!cb.isChecked()) {
            displayDate();
            rl.setVisibility(View.VISIBLE);
        }else{
            rl.setVisibility(View.GONE);
        }
        
        fragment = this;
        
        cb.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(ProfilFragment.this.cb.isChecked()){
                    ProfilFragment.this.rl.setVisibility(View.GONE);
                }else{
                    ProfilFragment.this.displayDate();
                    ProfilFragment.this.rl.setVisibility(View.VISIBLE);                    
                }
            }
        });

//        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                if(!isChecked){
//                    ProfilFragment.this.rl.setVisibility(View.VISIBLE);
//                    ProfilFragment.this.displayDate();
//                }
//            }
//        });

        return view;
    }

    private void displayDate(){
        //Get dates since the year 1930
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1930; i <= thisYear; i++)
        {
            this.years.add(Integer.toString(i));
        }

        //Get date field
        Spinner spinner1 = (Spinner) this.view.findViewById(R.id.start_date);
        Spinner spinner2 = (Spinner) this.view.findViewById(R.id.end_date);

        //Create an adapter for dropdown list with the list of years
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, this.years);

        //Populate dropdown list with the adapter
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(adapter.getPosition(""+thisYear));
    }
}
