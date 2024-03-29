package me.ebenezergraham.gcu.mpd.weatherforecast.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import me.ebenezergraham.gcu.mpd.weatherforecast.R;
import me.ebenezergraham.gcu.mpd.weatherforecast.ui.main.ForecastFragment;

/**
 * @author Ebenezer Graham
 * Matric Number: S1725987
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static int[] TAB_TITLES = new int[]{R.string.glasgow, R.string.london, R.string.new_york, R.string.oman, R.string.mauritius, R.string.bangladesh, R.string.cape_coast};
    public static Map<String, String> cities;
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        this.cities = new HashMap<>();
        cities.put("Glasgow", "2648579");
        cities.put("London", "2643743");
        cities.put("New York", "5128581");
        cities.put("Oman", "287286");
        cities.put("Mauritius", "934154");
        cities.put("Bangladesh", "1185241");
        cities.put("Cape Coast", "2302357");
    }

    @Override
    public Fragment getItem(int position) {
        String city = getPageTitle(position).toString();
        ForecastFragment fragment = ForecastFragment.newInstance(position + 1, city);
        return fragment;
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
}