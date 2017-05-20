package com.github.hnefatl.interactiondesign.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private double rain; // Rain volume
	private double wind; // Wind speed
	
	private WeatherType weatherType; // "Friendly" weather type - cloudy, sunny etc.
	
	/**
	 * Returns a list of daily forecasts for the next week, starting with today.
	 */
	public static List<WeatherData> getDailyForecast(City city) throws DataNotFoundException
	{
		try
		{
			Forecast forecast = getForecast(city);
			List<WeatherData> weathers = new ArrayList<>();
			
			for (DailyDataPoint d : forecast.getDaily().getData())
			{
				WeatherData data = new WeatherData();
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
	 * Returns a list of hourly forecasts for the next 48 hours.
	 */
	public static List<WeatherData> getHourlyForecast(City city) throws DataNotFoundException
	{
		try
		{
			Forecast forecast = getForecast(city);
			List<WeatherData> weathers = new ArrayList<>();
			
			for (DataPoint d : forecast.getHourly().getData())
			{
				WeatherData data = new WeatherData();
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
	private static Forecast getForecast(City city) throws DataNotFoundException
	{
		try
		{
			ForecastRequest req = new ForecastRequestBuilder()
										.key(new APIKey(DARKSKYAPI_KEY))
										.units(Units.auto)
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
	 * Returns the predicted volume of rain, in mm
	 */
	public double getRainVolume()
	{
		return rain;
	}
	
	/**
	 * Returns the predicted wind speed, in kph.
	 */
	public double getWindSpeed()
	{
		return wind;
	}
	
	public WeatherType getWeatherType()
	{
		return weatherType;
	}
	
	@Override
	public String toString()
	{
		return "Time: " + dateTime.toString() + "\n" +
				"Temperature: " + temp + "\n" +
				"Rain: " + rain + "\n" +
				"Wind: " + wind + "\n";
	}
}
