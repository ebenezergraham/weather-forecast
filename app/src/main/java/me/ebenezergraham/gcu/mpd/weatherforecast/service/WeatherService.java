package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Map;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;
import me.ebenezergraham.gcu.mpd.weatherforecast.ui.main.PageViewModel;

/**
 * @author Ebenezer Graham
 * Matric Number: S1725987
 */
public class WeatherService extends AsyncTask<String, Integer, Forecast> {

    private Parser parser;
    PageViewModel pageViewModel;
    private Map<String, String> cities;
    ForecastRepository forecastRepository;

    public WeatherService(PageViewModel pageViewModel) {
        this.pageViewModel = pageViewModel;
        this.cities = new HashMap<>();
        cities.put("Glasgow", "2648579");
        cities.put("London", "2643743");
        cities.put("New York", "5128581");
        cities.put("Oman", "287286");
        cities.put("Mauritius", "934154");
        cities.put("Bangladesh", "1185241");
        cities.put("Cape Coast", "2302357");
        this.parser = new Parser();
        this.forecastRepository = ForecastRepository.getInstance();
    }


    @Override
    protected Forecast doInBackground(String... strings) {
        int count = strings.length;
        Forecast forecast =  null;
        for (int i = 0; i < count; i++) {
            forecast = parser.fetch(strings[i]);
            publishProgress((int) ((i / (float) count) * 100));
            forecast.setLocationId(strings[i]);
            forecastRepository.forecastList.add(forecast);
            if (isCancelled()) break;
        }
        return forecast;
    }

    @Override
    protected void onPostExecute(Forecast forecast) {
        super.onPostExecute(forecast);
        pageViewModel.setForecast(forecast);
    }
}
