package com.github.hnefatl.interactiondesign.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



// TODO: Need to add acknowledgement of the DarkSky API to the UI somewhere: https://darksky.net/dev/docs



public class WeatherData
{	
	// DarkSky API key (again, Keith's account)
	private static final String DARKSKYAPI_KEY = "72e7cccc115c7a89c3e770d139f2c0fd";
	
	// DarkSky API 
	private static final String API_URL = "https://api.darksky.net/forecast/" + DARKSKYAPI_KEY + "/";
	
	private boolean hasDateTime;
	private Date dateTime;
	
	private boolean hasTemp;
	private float temp;
	
	private boolean hasTempRange;
	private float tempMin;
	private float tempMax;
	
	private boolean hasRain;
	private float rain;
	
	private boolean hasWind;
	private float wind;
	
	private WeatherType weatherType;

	
	public static List<WeatherData> getDailyForecast(City city, Unit units) throws InvalidFormatException
	{
		return null;
	}
	public static List<WeatherData> getHourlyForecast(City city, Unit units) throws InvalidFormatException
	{
		return null;
	}
	
	/**
	 * Gets the date and time of the prediction
	 */
	public Date getDateTime()
	{
		return dateTime;
	}
	
	/**
	 * Returns the predicted temperature, in degrees Celsius
	 */
	public float getTemperature() throws DataNotFoundException
	{
		if (!hasTemp)
			throw new DataNotFoundException();
		return temp;
	}

	public float getTemperatureMin() throws DataNotFoundException
	{
		if (!hasTempRange)
			throw new DataNotFoundException();
		return tempMin;
	}
	public float getTemperatureMax() throws DataNotFoundException
	{
		if (!hasTempRange)
			throw new DataNotFoundException();
		return tempMax;
	}
	
	/**
	 * Returns the predicted volume of rain, in mm
	 */
	public float getRainVolume() throws DataNotFoundException
	{
		if (!hasRain)
			throw new DataNotFoundException();
		return rain;
	}
	
	/**
	 * Returns the predicted wind speed, in kph.
	 */
	public float getWindSpeed() throws DataNotFoundException
	{
		if (!hasWind)
			throw new DataNotFoundException();
		return wind;
	}
	
	public WeatherType getWeatherType()
	{
		return weatherType;
	}
	
	@Override
	public String toString()
	{
		return "Time: " + dateTime.toString() + "\t" +
				"Temperature: " + temp + "\t" +
				"Rain: " + rain + "\t" +
				"Wind: " + wind + "\t";
	}
}
