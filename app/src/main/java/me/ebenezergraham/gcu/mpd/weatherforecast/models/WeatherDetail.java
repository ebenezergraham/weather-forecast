package me.ebenezergraham.gcu.mpd.weatherforecast.models;
/*
ebenezergraham created on 7/25/19
*/

import java.util.List;

public class WeatherDetail {

	private String title;
	private String link;
	private List<String> description;

	public WeatherDetail() {
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

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "WeatherDetail{" +
				"title='" + title + '\'' +
				", link='" + link + '\'' +
				", description=" + description +
				'}';
	}
}
