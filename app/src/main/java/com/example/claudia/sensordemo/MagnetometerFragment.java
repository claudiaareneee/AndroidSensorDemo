package com.example.claudia.sensordemo;


import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MagnetometerFragment extends Fragment implements SensorEventListener{

    private TextView xMagTextView, yMagTextView, zMagTextView;
    private String xMag, yMag, zMag;

    private SensorManager mSensorManager;
    Sensor accelerometer;

    public MagnetometerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager)this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_magnetometer, container, false);
        xMagTextView = view.findViewById(R.id.xMagnetometer);
        yMagTextView = view.findViewById(R.id.yMagnetometer);
        zMagTextView = view.findViewById(R.id.zMagnetometer);

        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xMag = String.valueOf(event.values[0]);
        yMag = String.valueOf(event.values[1]);
        zMag = String.valueOf(event.values[2]);

        xMagTextView.setText("x magnetometer: " + xMag);
        yMagTextView.setText("y magnetometer: " + yMag);
        zMagTextView.setText("z magnetometer: " + zMag);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do something
    }
}
