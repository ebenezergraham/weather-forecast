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

/**
 * @author Ebenezer Graham
 * Matric Number: S1725987
 */

@RunWith(RobolectricTestRunner.class)
public class ParserTest {
	final Parser parser = new Parser();
	@Test
	public void fetch() {
		System.out.println("Fetch From API\n");
		Forecast forecast = parser.fetch("2302357");
		System.out.println(forecast.toString());
		Assert.assertNotNull(forecast);
	}

	@Test
	public void parse() {
		System.out.println("Parse XML into POJO\n");
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
