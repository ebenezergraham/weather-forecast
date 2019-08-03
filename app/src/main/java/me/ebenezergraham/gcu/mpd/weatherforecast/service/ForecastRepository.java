package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;

public class ForecastRepository {
    private static ForecastRepository forecastRepository;
    private Map<String, Forecast> repository = new HashMap<>();

    public static ForecastRepository getInstance(){
        if (forecastRepository == null){
            forecastRepository = new ForecastRepository();
        }
        return forecastRepository;
    }

    public ForecastRepository(){
    }

    public Map<String, Forecast> getRepository() {
        return repository;
    }

    public void setRepository(Map<String, Forecast> repository) {
        this.repository = repository;
    }

    public MutableLiveData<Forecast> getForecast(String source){
        MutableLiveData<Forecast> data = new MutableLiveData<>();
        Parser parser = new Parser();
        data.setValue(parser.fetch(source));
        return data;
    }
}
