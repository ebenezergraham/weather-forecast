package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import me.ebenezergraham.gcu.mpd.weatherforecast.util.Util;

public class WeatherUpdateService extends JobService {
    private static final String TAG = "SyncService";

    @Override
    public boolean onStartJob(JobParameters params) {
        Intent service = new Intent(getApplicationContext(), WeatherService.class);
        getApplicationContext().startService(service);
        Util.scheduleJob(getApplicationContext()); // reschedule the job
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
