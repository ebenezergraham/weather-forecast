package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.Timer;
import java.util.TimerTask;

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

    public void cronJob(){
        final Timer timer = new Timer();

        // Use randomized data to update the coin stats every 10 seconds.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                       // P
                    }
                });
            }
        }, 60000 / 6, 60000 / 6);
    }
}
