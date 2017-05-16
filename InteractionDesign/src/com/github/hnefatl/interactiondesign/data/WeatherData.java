package com.github.hnefatl.interactiondesign.data;

import net.aksingh.owmjapis.*;

public class WeatherData
{
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
		OpenWeatherMap owm = new OpenWeatherMap("");
	}
}