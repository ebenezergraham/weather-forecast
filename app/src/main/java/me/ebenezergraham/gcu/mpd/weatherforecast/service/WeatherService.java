package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;


public class WeatherService {
    private Map<String,String> cities;

    public WeatherService() {
    }

    public void loadCities(){
        this.cities = new HashMap<>();
        cities.put("Glasgow","2648579");
        cities.put("London","2643743");
        cities.put("New York","5128581");
        cities.put("Oman","287286");
        cities.put("Mauritius","934154");
        cities.put("Bangladesh","1185241");
        cities.put("Cape Coast","2302357");
    }

    public List<Forecast> fetchWeatherForLocations(){
        loadCities();
        List<Forecast> forecast = new ArrayList<>();
        Parser parser = new Parser();
        for (Map.Entry entry: cities.entrySet()){
            forecast.add(parser.fetch(entry.getValue().toString()));
        }
        return forecast;
    }
}
