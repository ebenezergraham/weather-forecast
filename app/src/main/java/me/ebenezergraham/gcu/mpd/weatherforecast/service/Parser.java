package me.ebenezergraham.gcu.mpd.weatherforecast.service;
/*
ebenezergraham created on 7/25/19
*/

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;
import me.ebenezergraham.gcu.mpd.weatherforecast.model.WeatherDetail;

import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.CHANNEL;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.DESCRIPTION;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.HUMIDITY;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.ITEM;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.LINK;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.MAXIMUM_TEMPERATURE;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.MINIMUM_TEMPERATURE;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.POLLUTION;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.PRESSURE;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.PUB_DATE;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.SUNRISE;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.SUNSET;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.TITLE;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.UV_RISK;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.VISIBILITY;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.WIND_DIRECTION;
import static me.ebenezergraham.gcu.mpd.weatherforecast.Constants.WIND_SPEED;

public class Parser extends XmlPullParserFactory {


    private static String baseUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    private boolean done = false;

    public List<WeatherDetail> parse(XmlPullParser parser, Forecast forecast) {
        List<WeatherDetail> list = forecast.getItems();
        InputStream stream = null;
        try {

            int eventType = parser.getEventType();
            boolean done = false;
            WeatherDetail item = null;
            while (eventType != XmlPullParser.END_DOCUMENT && !done) {
                String name = null;
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase(ITEM)) {
                            Log.i("new item", "Create new item");
                            item = new WeatherDetail();
                        } else if (item != null) {
                            if (name.equalsIgnoreCase(LINK)) {
                                Log.i("Attribute", "setLink");
                                item.setLink(parser.nextText());
                            } else if (name.equalsIgnoreCase(DESCRIPTION)) {
                                Log.i("Attribute", "description");
                                List<String> description = Arrays.asList(parser.nextText().trim().split(","));
                                String[] result;
                                for (String entry : description) {
                                    result = entry.split(":");
                                    switch (result[0].trim()) {
                                        case MAXIMUM_TEMPERATURE:
                                            item.setMaximumTemperature(result[1]);
                                            break;
                                        case MINIMUM_TEMPERATURE:
                                            item.setMinimumTemperature(result[1]);
                                            break;
                                        case WIND_DIRECTION:
                                            item.setWindDirection(result[1]);
                                            break;
                                        case WIND_SPEED:
                                            item.setWindSpeed(result[1]);
                                            break;
                                        case VISIBILITY:
                                            item.setVisibility(result[1]);
                                            break;
                                        case PRESSURE:
                                            item.setPressure(result[1]);
                                            break;
                                        case HUMIDITY:
                                            item.setHumidity(result[1]);
                                            break;
                                        case UV_RISK:
                                            item.setUvRisk(result[1]);
                                            break;
                                        case POLLUTION:
                                            item.setPollution(result[1]);
                                            break;
                                        case SUNRISE:
                                            item.setSunrise(result[1]);
                                            break;
                                        case SUNSET:
                                            item.setSunset(result[1]);
                                            break;
                                        default:
                                    }
                                }
                            } else if (name.equalsIgnoreCase(PUB_DATE)) {
                                Log.i("Attribute", "date");
                                //item.setDate(parser.nextText());
                            } else if (name.equalsIgnoreCase(TITLE)) {
                                Log.i("Attribute", "title");
                                item.setTitle(parser.nextText().trim());
                            }
                        } else if (forecast != null) {
                            if (name.equalsIgnoreCase(LINK)) {
                                Log.i("Attribute", "setLink");
                                forecast.setLink(parser.nextText().trim());
                            } else if (name.equalsIgnoreCase(DESCRIPTION)) {
                                Log.i("Attribute", "description");
                                forecast.setDescription(parser.nextText().trim());
                            } else if (name.equalsIgnoreCase(PUB_DATE)) {
                                Log.i("Attribute", "date");
                                forecast.setDate(new Date(parser.nextText()));
                            } else if (name.equalsIgnoreCase(TITLE)) {
                                Log.i("Attribute", "title");
                                forecast.setTitle(parser.nextText().trim());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        Log.i("End tag", name);
                        if (name.equalsIgnoreCase(ITEM) && item != null) {
                            Log.i("Added", item.toString());
                            list.add(item);
                        } else if (name.equalsIgnoreCase(CHANNEL)) {
                            done = true;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                    System.out.println(forecast.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public Forecast fetch(final String locationId) {
        Forecast forecast = null;

        try {
            URL url = new URL(baseUrl + locationId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(11000);
            conn.setConnectTimeout(25000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            InputStream stream = conn.getInputStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            XmlPullParser parser = factory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);

            forecast = new Forecast();
            parse(parser, forecast);
            stream.close();
        } catch (XmlPullParserException | IOException e) {
            Log.e("Parser", e.getLocalizedMessage());
        }

        return forecast;
    }
}
