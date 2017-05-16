package com.github.hnefatl.interactiondesign.data;

import net.aksingh.owmjapis.*;
import net.aksingh.owmjapis.OpenWeatherMap.Units;

public class WeatherData
{
	private static String API_KEY = "0542e64ec65d5312dd38db40e0b2f2f5";	
	
	private boolean hasTemp;
	private float temp;
	
	private boolean hasTempRange;
	private float tempMin;
	private float tempMax;
	
	private boolean hasRain;
	private float rain;
	
	public WeatherData(CurrentWeather weather)
	{
		hasTemp = weather.hasMainInstance() && weather.getMainInstance().hasTemperature();
		hasTempRange = 	weather.hasMainInstance() &&
						weather.getMainInstance().hasMinTemperature() &&
						weather.getMainInstance().hasMaxTemperature(); 
		
		if (hasTemp)
			temp = weather.getMainInstance().getTemperature();
			
		if (hasTempRange)
		{
			tempMin = weather.getMainInstance().getMinTemperature();
			tempMax = weather.getMainInstance().getMaxTemperature();
		}
		
		if (hasRain)
			rain = weather.getRainInstance().getRain();

	}
	
	public static WeatherData getWeather()
	{
<<<<<<< HEAD
		/*
		OpenWeatherMap owm = new OpenWeatherMap(Units.METRIC, API_KEY);
		WeatherData data = new WeatherData();
		
		return data;
		*/
=======
		OpenWeatherMap owm = new OpenWeatherMap("");
>>>>>>> 6c83d1388d60f578cb32d85ea6185fbee854ce19
		
		return null;
	}
}
