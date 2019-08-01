package me.ebenezergraham.gcu.mpd.weatherforecast.ui.main;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;
import me.ebenezergraham.gcu.mpd.weatherforecast.service.WeatherService;

public class ForecastFragmentTest {

    @Test
    public void fetchAllLocations(){
        WeatherService forecastFragment = new WeatherService();
        forecastFragment.loadCities();
        List<Forecast> res =  forecastFragment.fetchWeatherForLocations();
        for (Forecast forecast : res){
            Assert.assertNotNull(forecast);
        }
        Assert.assertEquals(res.size(),7);
    }

}