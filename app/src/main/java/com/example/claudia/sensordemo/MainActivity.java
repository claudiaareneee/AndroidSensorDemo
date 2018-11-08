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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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

            AccelerationFragment accFragment = new AccelerationFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,accFragment,null);
            fragmentTransaction.commit();
        }

        fragmentContainer = (FrameLayout)findViewById(R.id.fragmentContainer);

        Log.d(TAG, "onCreate: Registered accelerometer lists");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override //https://www.youtube.com/watch?v=ruPRpiJNJrU
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_1:
                    AccelerationFragment accelerationFragment = new AccelerationFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,accelerationFragment, null).commit();
                    break;
                case R.id.nav_2:
                    MagnetometerFragment magnetometerFragment = new MagnetometerFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,magnetometerFragment, null).commit();
                    break;
                case R.id.nav_3:
                    break;
            }
            return true;
        }
    };
}
