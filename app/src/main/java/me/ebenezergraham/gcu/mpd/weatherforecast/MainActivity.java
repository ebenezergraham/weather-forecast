package me.ebenezergraham.gcu.mpd.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import me.ebenezergraham.gcu.mpd.weatherforecast.activities.SettingsActivity;
import me.ebenezergraham.gcu.mpd.weatherforecast.adapter.SectionsPagerAdapter;
import me.ebenezergraham.gcu.mpd.weatherforecast.service.WeatherService;

/**
 * @author Ebenezer Graham
 * Matric Number: S1725987
 */
public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{


    public static WeatherService weatherService = new WeatherService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherService.fetchWeatherForLocations();
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent setting = new Intent(this, SettingsActivity.class);
            startActivity(setting);
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