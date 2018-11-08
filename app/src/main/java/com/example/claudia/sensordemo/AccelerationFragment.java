package com.example.claudia.sensordemo;


import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccelerationFragment extends Fragment implements SensorEventListener{

    private TextView xAccTextView, yAccTextView, zAccTextView;
    private String xAcc, yAcc, zAcc;

    private SensorManager mSensorManager;
    Sensor accelerometer;

    public AccelerationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_acceleration, container, false);
        xAccTextView = view.findViewById(R.id.xAcceleration);
        yAccTextView = view.findViewById(R.id.yAcceleration);
        zAccTextView = view.findViewById(R.id.zAcceleration);

        mSensorManager = (SensorManager)this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);


        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xAcc = String.valueOf(event.values[0]);
        yAcc = String.valueOf(event.values[1]);
        zAcc = String.valueOf(event.values[2]);

        xAccTextView.setText("x acceleration: " + xAcc);
        yAccTextView.setText("y acceleration: " + yAcc);
        zAccTextView.setText("z acceleration: " + zAcc);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do something
    }
}
