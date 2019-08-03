package me.ebenezergraham.gcu.mpd.weatherforecast.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ebenezergraham.gcu.mpd.weatherforecast.R;
import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;
import me.ebenezergraham.gcu.mpd.weatherforecast.service.Parser;
import me.ebenezergraham.gcu.mpd.weatherforecast.ui.main.ForecastFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static int[] TAB_TITLES = new int[] {R.string.glasgow, R.string.london,R.string.new_york, R.string.oman, R.string.mauritius, R.string.bangladesh, R.string.cape_coast};
    public static Map<String, String> cities;
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        this.cities = new HashMap<>();
        cities.put("Glasgow","2648579");
        cities.put("London","2643743");
        cities.put("New York","5128581");
        cities.put("Oman","287286");
        cities.put("Mauritius","934154");
        cities.put("Bangladesh","1185241");
        cities.put("Cape Coast","2302357");
    }

    @Override
    public Fragment getItem(int position) {
        String city = getPageTitle(position).toString();
        return ForecastFragment.newInstance(position + 1, city);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgTvShow;
        TextView textTvShow;
        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            imgTvShow = (ImageView)itemView.findViewById(R.id.card_image);
            textTvShow = (TextView)itemView.findViewById(R.id.card_title);
            cv = (CardView)itemView.findViewById(R.id.cv);
        }

    }

    public List<Forecast> fetchWeatherForLocations(){
        List<Forecast> forecast = new ArrayList<>();
        Parser parser = new Parser();
        for (Map.Entry entry: cities.entrySet()){
            forecast.add(parser.fetch(entry.getValue().toString()));
        }
        return forecast;
    }
}