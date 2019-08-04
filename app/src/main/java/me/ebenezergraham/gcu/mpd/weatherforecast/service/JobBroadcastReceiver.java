package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import me.ebenezergraham.gcu.mpd.weatherforecast.util.Util;

public class JobBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Util.scheduleJob(context);
    }
}
