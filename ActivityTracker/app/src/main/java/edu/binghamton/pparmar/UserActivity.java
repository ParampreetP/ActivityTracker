package edu.binghamton.pparmar;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
//We need to make user exit his account on button click. We’ll add click listener to this button. signOut() method is used here.
public class UserActivity extends AppCompatActivity implements SensorEventListener {
    Button btnLogOut;
    TextView tv_steps;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    int steps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sSensor= sensorManager .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        tv_steps = (TextView) findViewById(R.id.TV_STEPS);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(UserActivity.this, ActivityLogin.class);
                startActivity(I);

            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }


        if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            steps++;

        }

        tv_steps.setText(String.valueOf(steps));
        //tv_steps.setText(String.valueOf(100));


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //won't happen
    }

}
