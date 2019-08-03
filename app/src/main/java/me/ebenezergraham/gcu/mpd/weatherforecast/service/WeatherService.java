package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;


public class WeatherService  extends AsyncTask<String, Integer, Forecast> {

    private Parser parser = new Parser();
    public Map<String,String> cities;

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Forecast result) {
    }

    public WeatherService() {
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
    protected Forecast doInBackground(String... strings) {
        int count = strings.length;
        long totalSize = 0;
        Forecast forecast = null;
        for (int i = 0; i < count; i++) {
            forecast = parser.fetch(strings[i]);
            publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }
        return forecast;
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
