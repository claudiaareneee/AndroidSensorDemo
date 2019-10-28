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

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrientationFragment extends Fragment implements SensorEventListener{

    private TextView xOriTextView, yOriTextView, zOriTextView;
    private String xOri, yOri, zOri;

    private SensorManager mSensorManager;
    Sensor accelerometer, magnetometer;

    public OrientationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orientation, container, false);
        xOriTextView = view.findViewById(R.id.xOrientation);
        yOriTextView = view.findViewById(R.id.yOrientation);
        zOriTextView = view.findViewById(R.id.zOrientation);

        mSensorManager = (SensorManager)this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);

    }

    public void orientationDetails(View v) {
        // does something very interesting
    }

    float[] mGravity;
    float[] mGeomagnetic;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimuth = orientation[0];
                float pitch = orientation[2];
                float roll = orientation[1];

                zOri = String.format(Locale.US,"Azimuth: %.8f",azimuth);
                xOri = String.format(Locale.US,"Roll:    %.8f",roll);
                yOri = String.format(Locale.US,"Pitch:   %.8f",pitch);

                xOriTextView.setText(xOri);
                yOriTextView.setText(yOri);
                zOriTextView.setText(zOri);


                // orientation contains: azimuth, pitch and roll
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
