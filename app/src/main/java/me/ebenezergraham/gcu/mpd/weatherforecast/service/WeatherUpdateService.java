package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import me.ebenezergraham.gcu.mpd.weatherforecast.util.Util;

public class WeatherUpdateService extends JobService {
    private static final String TAG = WeatherUpdateService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"Job Started");
        WeatherService weatherService = new WeatherService();

        weatherService.execute();
        //jobFinished(params,true);
/*        Intent service = new Intent(getApplicationContext(), WeatherUpdateService.class);
        getApplicationContext().startService(service);*/
        jobFinished(params, true);
        Util.scheduleJob(getApplicationContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
