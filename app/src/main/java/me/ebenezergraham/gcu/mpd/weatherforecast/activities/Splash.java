package me.ebenezergraham.gcu.mpd.weatherforecast.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import me.ebenezergraham.gcu.mpd.weatherforecast.MainActivity;
import me.ebenezergraham.gcu.mpd.weatherforecast.R;

/**
 * @author Ebenezer Graham
 * Matric Number: S1725987
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
