package me.ebenezergraham.gcu.mpd.weatherforecast.ui.main;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutionException;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;
import me.ebenezergraham.gcu.mpd.weatherforecast.service.WeatherService;

/**
 * @author Ebenezer Graham
 * Matric Number: S1725987
 */
public class PageViewModel extends ViewModel {

    private MutableLiveData<Forecast> mIndex = new MutableLiveData<>();

    public PageViewModel() {
    }

    public void getData(final String location) {
        WeatherService weatherService = new WeatherService(this);
        AsyncTask<String, Integer, Forecast> res = weatherService.execute(location);
        try {
            setForecast(res.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setForecast(Forecast forecast) {
        mIndex.setValue(forecast);
    }

    public LiveData<Forecast> getForecast() {
        return mIndex;
    }
}