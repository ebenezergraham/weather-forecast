package me.ebenezergraham.gcu.mpd.weatherforecast.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import me.ebenezergraham.gcu.mpd.weatherforecast.model.Forecast;

@RunWith(RobolectricTestRunner.class)
public class ParserTest {
	final Parser parser = new Parser();
	@Test
	public void fetch() {

		Forecast forecast = parser.fetch("2302357");
		System.out.println(forecast.toString());
		Assert.assertNotNull(forecast);
	}

	@Test
	public void parse() {
		Forecast forecast =  null;
		try {
			XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
			pullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			InputStream stream = new FileInputStream("test.xml");

			pullParser.setInput(stream, null);
			forecast = new Forecast();
			parser.parse(pullParser,forecast);

		} catch (XmlPullParserException | FileNotFoundException e) {
			Assert.fail();
		}
		System.out.println(forecast.toString());
		Assert.assertNotNull(forecast);

	}
}
