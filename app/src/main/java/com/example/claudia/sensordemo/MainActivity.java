package com.example.claudia.sensordemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    
    private SensorManager mSensorManager;
    Sensor accelerometer;

    private TextView xAcc;
    private TextView yAcc;
    private TextView zAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Initializing textView");
        xAcc = (TextView) findViewById(R.id.xAcceleration);
        yAcc = (TextView) findViewById(R.id.yAcceleration);
        zAcc = (TextView) findViewById(R.id.zAcceleration);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        Log.d(TAG, "onCreate: Registered accelerometer lists");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        xAcc.setText("x acceleration: " + String.valueOf(event.values[0]));
        yAcc.setText("y acceleration: " + String.valueOf(event.values[1]));
        zAcc.setText("z acceleration: " + String.valueOf(event.values[2]));

        Log.d(TAG, "onSensorChanged: X: " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2]);
    }
}
