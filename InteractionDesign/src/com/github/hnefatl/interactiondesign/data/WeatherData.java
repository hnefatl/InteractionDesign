package com.github.hnefatl.interactiondesign.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder.Units;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.Latitude;
import tk.plogitech.darksky.forecast.Longitude;
import tk.plogitech.darksky.forecast.model.DailyDataPoint;
import tk.plogitech.darksky.forecast.model.DataPoint;
import tk.plogitech.darksky.forecast.model.Forecast;

public class WeatherData
{	
	// DarkSky API key (Keith's account)
	private static final String DARKSKYAPI_KEY = "72e7cccc115c7a89c3e770d139f2c0fd";
	
	private Date dateTime; // Date/time that this data is for
	
	private double temp; // Temperature
	private String tempUnits; // Units of the temperature
	
	private double rain; // Rain volume
	private String rainUnits;
	
	private double wind; // Wind speed
	private String windUnits;
	
	private WeatherType weatherType; // "Friendly" weather type - cloudy, sunny etc.
	
	/**
	 * Returns a list of daily forecasts for the next week, starting with today.
	 */
	public static List<WeatherData> getDailyForecast(City city) throws DataNotFoundException
	{
		return getDailyForecast(city, null);
	}
	
	/**
	 * Returns a list of daily forecasts for the next week, starting with today, getting units specific for the given locale
	 */
	public static List<WeatherData> getDailyForecast(City city, Locale unitLocale) throws DataNotFoundException
	{
		try
		{
			Forecast forecast = getForecast(city, unitLocale);
			List<WeatherData> weathers = new ArrayList<>();
			
			for (DailyDataPoint d : forecast.getDaily().getData())
			{
				WeatherData data = new WeatherData();
				data.setUnits(forecast.getFlags().getUnits());
				
				data.dateTime = Date.from(d.getTime());
				data.rain = d.getPrecipIntensity();
				data.temp = (d.getTemperatureMin() + d.getTemperatureMax()) / 2; // Average the temperatures
				data.wind = d.getWindSpeed();
				data.weatherType = WeatherType.parse(d.getIcon());
				weathers.add(data);
			}
			
			return weathers;
		}
		catch (NullPointerException e)
		{
			throw new DataNotFoundException(e);
		}
	}
	/**
	 * Returns a list of hourly forecasts for the next 48 hours
	 */
	public static List<WeatherData> getHourlyForecast(City city) throws DataNotFoundException
	{
		return getHourlyForecast(city, null);
	}
	/**
	 * Returns a list of hourly forecasts for the next 48 hours, getting units specific for the given locale
	 */
	public static List<WeatherData> getHourlyForecast(City city, Locale unitLocale) throws DataNotFoundException
	{
		try
		{
			Forecast forecast = getForecast(city, unitLocale);			
			List<WeatherData> weathers = new ArrayList<>();
			
			for (DataPoint d : forecast.getHourly().getData())
			{
				WeatherData data = new WeatherData();
				data.setUnits(forecast.getFlags().getUnits());
				
				data.dateTime = Date.from(d.getTime());
				data.rain = d.getPrecipIntensity();
				data.temp = d.getTemperature();
				data.wind = d.getWindSpeed();
				data.weatherType = WeatherType.parse(d.getIcon());
				weathers.add(data);
			}
			
			return weathers;
		}
		catch (NullPointerException e)
		{
			throw new DataNotFoundException(e);
		}
	}
	private static Forecast getForecast(City city, Locale locale) throws DataNotFoundException
	{
		try
		{
			Units units;
			if (locale == null)
				units = Units.auto;
			else
			{
				if (locale == Locale.CANADA)
					units = Units.ca;
				else if (locale == Locale.UK)
					units = Units.uk2;
				else if (locale == Locale.US)
					units = Units.us;
				else
					units = Units.si;
			}
			ForecastRequest req = new ForecastRequestBuilder()
										.key(new APIKey(DARKSKYAPI_KEY))
										.units(units)
										.location(new GeoCoordinates(new Longitude(city.getLon()), new Latitude(city.getLat())))
										.build();
			
			DarkSkyJacksonClient client = new DarkSkyJacksonClient();
			return client.forecast(req);
		}
		catch (ForecastException e)
		{
			throw new DataNotFoundException(e);
		}
	}
	private void setUnits(String forecastUnits)
	{
		// Pulled from https://github.com/200Puls/darksky-forecast-api/blob/master/darksky-forecast-api/src/main/java/tk/plogitech/darksky/forecast/ForecastRequestBuilder.java
		if (	forecastUnits.equals("si") || forecastUnits.equals("auto") ||
				forecastUnits.equals("ca") || forecastUnits.equals("uk2"))
		{
			tempUnits = "°C";
			rainUnits = "mm/hr";
			windUnits = "m/s";
		}
		if (forecastUnits.equals("ca"))
			windUnits = "km/h";
		if (forecastUnits.equals("uk2"))
			windUnits = "mph";
		
		if (forecastUnits.equals("us"))
		{
			tempUnits = "°F";
			rainUnits = "in/hr";
			windUnits = "mph";
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
	 * Returns the predicted temperature
	 */
	public double getTemperature()
	{
		return temp;
	}
	/**
	 * Return the country's unit for temperature
	 */
	public String getTemperatureUnits()
	{
		return tempUnits;
	}
	
	/**
	 * Returns the predicted rate of rainfall
	 */
	public double getRainRate()
	{
		return rain;
	}
	/**
	 * Returns the country's unit for rain
	 */
	public String getRainUnits()
	{
		return rainUnits;
	}
	
	/**
	 * Returns the predicted wind speed
	 */
	public double getWindSpeed()
	{
		return wind;
	}
	/**
	 * Returns the country's units for wind speed
	 */
	public String getWindUnits()
	{
		return windUnits;
	}
	
	/**
	 * Returns the "friendly" name for the weather, useful for icons
	 */
	public WeatherType getWeatherType()
	{
		return weatherType;
	}
	
	/**
	 * Returns a human-readable form of the information
	 */
	@Override
	public String toString()
	{
		return "Time: " + dateTime.toString() + "\n" +
				"Temperature: " + temp + tempUnits + "\n" +
				"Rain: " + rain + rainUnits + "\n" +
				"Wind: " + wind + windUnits + "\n" +
				"General: " + weatherType;
	}
}
