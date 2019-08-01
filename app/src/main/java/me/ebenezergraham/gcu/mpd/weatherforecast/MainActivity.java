package me.ebenezergraham.gcu.mpd.weatherforecast;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import me.ebenezergraham.gcu.mpd.weatherforecast.service.WeatherService;
import me.ebenezergraham.gcu.mpd.weatherforecast.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {


    public static WeatherService weatherService = new WeatherService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        weatherService.loadCities();
//        weatherService.fetchWeatherForLocations();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        final TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("Position"+position+" PositionOffset"+positionOffset+" OffsetPixels"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}