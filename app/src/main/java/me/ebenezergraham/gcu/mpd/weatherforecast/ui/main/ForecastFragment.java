package me.ebenezergraham.gcu.mpd.weatherforecast.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ebenezergraham.gcu.mpd.weatherforecast.R;
import me.ebenezergraham.gcu.mpd.weatherforecast.models.Forecast;
import me.ebenezergraham.gcu.mpd.weatherforecast.service.Parser;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private Map<String,String> cities;

    public static ForecastFragment newInstance(int index) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        loadCities();
        //CardView cardView = (CardView) findViewById(R.id.cardView);
        //cardView.setCardBackgroundColor(R.color.colorAccent);
        //cardView.setCardElevation();
        //cardView.setMaxCardElevation(...);
        //cardView.setRadius(...);
        //cardView.setPreventCornerOverlap(...);
        //cardView.setUseCompatPadding(...);
        //fetchWeather().get()
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    private List<Forecast> fetchWeather(){
        List<Forecast> forecast = new ArrayList<>();
        Parser parser = new Parser();
        for (Map.Entry entry: cities.entrySet()){
            forecast.add(parser.fetch(entry.getValue().toString()));
        }
        return forecast;
    }

    private void loadCities(){
        this.cities = new HashMap<>();
        cities.put("Glasgow","2648579");
        cities.put("London","2643743");
        cities.put("New York","5128581");
        cities.put("Oman","287286");
        cities.put("Mauritius","934154");
        cities.put("Bangladesh","1185241");
        cities.put("Cape Coast","2302357");
    }
}