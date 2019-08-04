package me.ebenezergraham.gcu.mpd.weatherforecast.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import me.ebenezergraham.gcu.mpd.weatherforecast.MainActivity;
import me.ebenezergraham.gcu.mpd.weatherforecast.R;
import me.ebenezergraham.gcu.mpd.weatherforecast.model.WeatherDetail;

public class MoreDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeatherDetail weatherDetail = (WeatherDetail) getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_more_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(weatherDetail.getTitle());
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        TextView minTemp = findViewById(R.id.minimum_temperature);
        TextView humidity = findViewById(R.id.humidity);
        TextView windSpeed = findViewById(R.id.wind_speed);
        TextView windDirection = findViewById(R.id.wind_direction);
        TextView sunrise = findViewById(R.id.sunrise);
        TextView sunset = findViewById(R.id.sunset);
        TextView uvRisk = findViewById(R.id.uv_risk);
        TextView visibility = findViewById(R.id.visibility);

        humidity.setText(weatherDetail.getHumidity());
        minTemp.setText(weatherDetail.getMinimumTemperature().split(":")[1]);
        windSpeed.setText(weatherDetail.getWindSpeed());
        windDirection.setText(weatherDetail.getWindDirection());
        sunrise.setText(weatherDetail.getSunrise());
        sunset.setText(weatherDetail.getSunset());
        uvRisk.setText(weatherDetail.getUvRisk());
        visibility.setText(weatherDetail.getVisibility());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.more_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent setting = new Intent(this,SettingsActivity.class);
        startActivity(setting);
        if (id == R.id.nav_home) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tools) {


        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String content = "You have been invited to download Weather Forecast at: " +
                    "https://ebenezergraham.me/mobileplatformdevelopment/weatherforecast.apk";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
            startActivity(Intent.createChooser(sharingIntent, "Share app via"));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
