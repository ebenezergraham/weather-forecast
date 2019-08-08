package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;

/**
 * @author Ebenezer Graham
 * Matric Number: S1725987
 */
public class WeatherService extends AsyncTask<String, Integer, Forecast> {

    private Parser parser;
    public Map<String, String> cities;

    public WeatherService() {
        this.cities = new HashMap<>();
        cities.put("Glasgow", "2648579");
        cities.put("London", "2643743");
        cities.put("New York", "5128581");
        cities.put("Oman", "287286");
        cities.put("Mauritius", "934154");
        cities.put("Bangladesh", "1185241");
        cities.put("Cape Coast", "2302357");
        this.parser = new Parser();
    }

    @Override
    protected Forecast doInBackground(String... strings) {
        int count = strings.length;
        Forecast forecast = null;

        for (int i = 0; i < count; i++) {
            forecast = parser.fetch(strings[i]);
            publishProgress((int) ((i / (float) count) * 100));
            if (isCancelled()) break;
        }
        return forecast;
    }

    public Map<String, Forecast> fetchWeatherForLocations() {
        Map<String, Forecast> list = new HashMap<>();

        for (Map.Entry entry : cities.entrySet()) {
            AsyncTask<String, Integer, Forecast> task = new WeatherService().execute(entry.getValue().toString());
            try {
                list.put(entry.getKey().toString(), task.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
