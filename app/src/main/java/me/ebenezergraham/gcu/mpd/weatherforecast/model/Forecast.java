package me.ebenezergraham.gcu.mpd.weatherforecast.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ebenezer Graham
 * Matric Number: S1725987
 */
public class Forecast {

    private String city;
    private String title;
    private String link;
    private String description;
    private Date date;
    private String imageTitle;
    private URL imageUrl;
    private String locationId;

    List<WeatherDetail> items;

    public Forecast() {
        items = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<WeatherDetail> getItems() {
        return items;
    }

    public void setItems(List<WeatherDetail> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", imageTitle='" + imageTitle + '\'' +
                ", imageUrl=" + imageUrl +
                ", items=" + items +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
