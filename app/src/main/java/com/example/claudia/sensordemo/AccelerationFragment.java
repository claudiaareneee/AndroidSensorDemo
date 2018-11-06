package com.example.claudia.sensordemo;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccelerationFragment extends Fragment {

    private TextView acceleration;

    public AccelerationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_acceleration, container, false);
        acceleration = view.findViewById(R.id.acceleration);

        //Unpack bundle
        try {
            String acc = this.getArguments().getString("xAcc");
            acceleration.setText(acc);

        } catch (java.lang.NullPointerException e){
            System.out.println("ERROR. UHOH");
        }

        return view;
    }
}
