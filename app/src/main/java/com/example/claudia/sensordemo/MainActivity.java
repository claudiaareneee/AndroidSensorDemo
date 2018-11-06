package com.example.claudia.sensordemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    
    private SensorManager mSensorManager;
    Sensor accelerometer;

//    private TextView xAccTextView, yAccTextView, zAccTextView;
    private String xAcc, yAcc, zAcc;


    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Initializing fragments");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (findViewById(R.id.fragmentContainer) != null){
            if (savedInstanceState != null){
                return;
            }

//            AccelerationFragment accFragment = new AccelerationFragment();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,accFragment,null);
//            fragmentTransaction.commit();
        }

        fragmentContainer = (FrameLayout)findViewById(R.id.fragmentContainer);

//        Log.d(TAG, "onCreate: Initializing textView");
//        xAccTextView= (TextView) findViewById(R.id.xAcceleration);
//        yAccTextView = (TextView) findViewById(R.id.yAcceleration);
//        zAccTextView = (TextView) findViewById(R.id.zAcceleration);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        Log.d(TAG, "onCreate: Registered accelerometer lists");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.nav_1:
//                    sendData(xAcc);
                    break;
                case R.id.nav_2:
                    break;
                case R.id.nav_3:
                    break;
            }

//            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();

            return true;
        }
    };

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        xAcc = String.valueOf(event.values[0]);
        yAcc = String.valueOf(event.values[1]);
        zAcc = String.valueOf(event.values[2]);

//        xAccTextView.setText("x acceleration: " + xAcc);
//        zAccTextView.setText("y acceleration: " + yAcc);
//        yAccTextView.setText("z acceleration: " + zAcc);

        sendData(xAcc);

        Log.d(TAG, "onSensorChanged: X: " + event.values[0] + " Y: " + event.values[1] + " Z: " + event.values[2]);
    }

    private void sendData(String data){
        //Pack Data in Bundle
        Bundle bundle = new Bundle();
        bundle.putString("xAcc", xAcc);

        AccelerationFragment accelerationFragment = new AccelerationFragment();
        accelerationFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,accelerationFragment, null).commit();
    }
}
