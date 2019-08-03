package me.ebenezergraham.gcu.mpd.weatherforecast.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.ebenezergraham.gcu.mpd.weatherforecast.MainActivity;
import me.ebenezergraham.gcu.mpd.weatherforecast.R;
import me.ebenezergraham.gcu.mpd.weatherforecast.adapter.DaysRecycler;
import me.ebenezergraham.gcu.mpd.weatherforecast.adapter.SectionsPagerAdapter;
import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;
import me.ebenezergraham.gcu.mpd.weatherforecast.model.WeatherDetail;

/**
 * @Author Ebenezer Graham
 * Matric Number: S1725987
 */
public class ForecastFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SECTION_NAME = "section_name";

    public PageViewModel pageViewModel;
    RecyclerView recyclerView;
    DaysRecycler daysRecycler;

    public static ForecastFragment newInstance(int index, String city) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putString(ARG_SECTION_NAME, city);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

        int index = 1;
        String city = "";
        Forecast forecast = null;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            city = getArguments().getString(ARG_SECTION_NAME);
        }
        pageViewModel.setForecast(MainActivity.weatherService.forecastRepository.getRepository().get(city));
        pageViewModel.getData(SectionsPagerAdapter.cities.get(city));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.days);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Forecast forecast = pageViewModel.getForecast().getValue();
        List<WeatherDetail> data = forecast.getItems();
        daysRecycler = new DaysRecycler(data);
        recyclerView.setAdapter(daysRecycler);
        pageViewModel.getForecast().observe(this, new Observer<Forecast>() {
            @Override
            public void onChanged(@Nullable Forecast s) {
                /*Forecast forecast = pageViewModel.getForecast().getValue();
                List<WeatherDetail> data = forecast.getItems();
                daysRecycler = new DaysRecycler(data);*/
            }
        });
        return root;
    }

}