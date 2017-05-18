package com.github.hnefatl.interactiondesign.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import net.aksingh.owmjapis.*;
import net.aksingh.owmjapis.DailyForecast.Forecast;
import net.aksingh.owmjapis.OpenWeatherMap.Language;
import net.aksingh.owmjapis.OpenWeatherMap.Units;

// Yes, it looks like there's a lot of duplication in pulling weather data out of the API.
// The problem is that there's a lot of duplication within the API itself, and no standard
// or common superclass that provides everything. In particular, the Daily/HourlyForecast
// classes provide the same data but through different interfaces, and their abstract
// superclass doesn't actually provide any information about the weather. Wow....

public class WeatherData
{
	// OpenWeatherMap API key (registered on Keith's account)
	private static String API_KEY = "0542e64ec65d5312dd38db40e0b2f2f5";	
	
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

	private WeatherData(HourlyForecast.Forecast forecast)
	{
		// Collate availability of information variables
		hasDateTime = forecast.hasDateTime();
		hasTemp = forecast.hasMainInstance() && forecast.getMainInstance().hasTemperature();
		hasTempRange = 	forecast.hasMainInstance() &&
						forecast.getMainInstance().hasMinTemperature() &&
						forecast.getMainInstance().hasMaxTemperature(); 

		// Hourly forecast has no rain data...
		// hasRain = forecast.hasRainInstance() && forecast.getRainInstance().hasRain1h();
		hasRain = false;
		hasWind = forecast.hasWindInstance() && forecast.getWindInstance().hasWindSpeed();
		
		if (hasDateTime)
			dateTime = forecast.getDateTime();
		
		// Collect actual values for the variables, if possible
		if (hasTemp)
			temp = forecast.getMainInstance().getTemperature();
			
		if (hasTempRange)
		{
			tempMin = forecast.getMainInstance().getMinTemperature();
			tempMax = forecast.getMainInstance().getMaxTemperature();
		}
		

		if (hasWind)
			wind = forecast.getWindInstance().getWindSpeed();
		
		// TODO: weather type
	}
	private WeatherData(DailyForecast.Forecast forecast)
	{
		hasDateTime = forecast.hasDateTime();
		hasTemp = forecast.getTemperatureInstance().hasDayTemperature();
		hasTempRange = 	forecast.getTemperatureInstance().hasMinimumTemperature() &&
						forecast.getTemperatureInstance().hasMinimumTemperature();
		
		hasRain = forecast.hasRain();
		hasWind = forecast.hasWindSpeed();
		
		if (hasDateTime)
			dateTime = forecast.getDateTime();
			
		if (hasTemp)
			temp = forecast.getTemperatureInstance().getDayTemperature();
		
		if (hasTempRange)
		{
			tempMin = forecast.getTemperatureInstance().getMinimumTemperature();
			tempMax = forecast.getTemperatureInstance().getMaximumTemperature();
		}
		
		if (hasRain)
			rain = forecast.getRain();
		
		if (hasWind)
			wind = forecast.getWindSpeed();
		
		// TODO: weather type
	}
	
	
	public static List<WeatherData> getDailyForecast(City city, Unit units) throws InvalidFormatException
	{
		try
		{	
			OpenWeatherMap owm = new OpenWeatherMap(Units.METRIC, API_KEY);
			DailyForecast forecast = owm.dailyForecastByCityCode(city.getCityId(), (byte)7);
			if (!forecast.hasForecastCount())
				throw new InvalidFormatException();
			
			List<WeatherData> data = new ArrayList<>();
			for (int x = 0; x < forecast.getForecastCount(); x++)
			{
				DailyForecast.Forecast f = forecast.getForecastInstance(x);
				WeatherData curr = new WeatherData(f);
				data.add(curr);
			}
			
			return data;
		}
		catch (JSONException e)
		{
			throw new InvalidFormatException(e);
		}
	}
	public static List<WeatherData> getHourlyForecast(City city, Unit units) throws InvalidFormatException
	{
		try
		{	
			OpenWeatherMap owm = new OpenWeatherMap(Units.METRIC, API_KEY);
			HourlyForecast forecast = owm.hourlyForecastByCityCode(city.getCityId());
			if (!forecast.hasForecastCount())
				throw new InvalidFormatException();
			
			List<WeatherData> data = new ArrayList<>();
			for (int x = 0; x < forecast.getForecastCount(); x++)
			{
				HourlyForecast.Forecast f = forecast.getForecastInstance(x);
				WeatherData curr = new WeatherData(f);
				data.add(curr);
			}
			
			return data;
		}
		catch (JSONException e)
		{
			throw new InvalidFormatException(e);
		}
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
