package me.ebenezergraham.gcu.mpd.weatherforecast.util;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import me.ebenezergraham.gcu.mpd.weatherforecast.service.WeatherUpdateService;

public class Util {
    private static final long ONE_DAY_INTERVAL = 24 * 60 * 60 * 1000L;

    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, WeatherUpdateService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(1 * 1000);
        builder.setOverrideDeadline(3 * 1000);
        builder.setPeriodic(ONE_DAY_INTERVAL);
        builder.setRequiresCharging(false);
        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());
    }

}
