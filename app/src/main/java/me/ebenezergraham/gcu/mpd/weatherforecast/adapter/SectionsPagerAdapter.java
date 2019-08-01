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

import java.util.HashMap;
import java.util.Map;

import me.ebenezergraham.gcu.mpd.weatherforecast.R;
import me.ebenezergraham.gcu.mpd.weatherforecast.ui.main.ForecastFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static int[] TAB_TITLES = new int[] {R.string.glasgow, R.string.london,R.string.new_york, R.string.oman, R.string.mauritius, R.string.bangladesh, R.string.cape_coast};
    private static Map<String, String> cities;
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return ForecastFragment.newInstance(position + 1);
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
}