package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import androidx.lifecycle.MutableLiveData;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;

public class ForecastRepository {
    private static ForecastRepository forecastRepository;

    public static ForecastRepository getInstance(){
        if (forecastRepository == null){
            forecastRepository = new ForecastRepository();
        }
        return forecastRepository;
    }

    private Parser Parser = new Parser();

    public ForecastRepository(){
    }

    public MutableLiveData<Forecast> getForecast(String source){
        MutableLiveData<Forecast> data = new MutableLiveData<>();
        Parser parser = new Parser();
        data.setValue(parser.fetch(source));
        return data;
    }
}
