package me.ebenezergraham.gcu.mpd.weatherforecast;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import me.ebenezergraham.gcu.mpd.weatherforecast.adapter.SectionsPagerAdapter;
import me.ebenezergraham.gcu.mpd.weatherforecast.service.WeatherService;

public class MainActivity extends AppCompatActivity {


    public static WeatherService weatherService = new WeatherService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherService.fetchWeatherForLocations();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        final TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        //todo: add scroll arrows
        viewPager.arrowScroll(View.FOCUS_LEFT);
        viewPager.arrowScroll(View.FOCUS_RIGHT);
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
                if(state>0 && tabs.getSelectedTabPosition()==6){
                    viewPager.setCurrentItem(0);
                }else  if(state>0 && tabs.getSelectedTabPosition()==0){
                    viewPager.setCurrentItem(6);
                }
            }
        });
    }
}