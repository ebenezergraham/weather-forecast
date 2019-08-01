package me.ebenezergraham.gcu.mpd.weatherforecast.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import me.ebenezergraham.gcu.mpd.weatherforecast.MainActivity;
import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;
import me.ebenezergraham.gcu.mpd.weatherforecast.service.ForecastRepository;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<Forecast> mForecast = Transformations.map(mIndex, new Function<Integer, Forecast>() {
        @Override
        public Forecast apply(Integer input) {
            return new Forecast();
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<Forecast> getForecast() {
        return mForecast;
    }
}