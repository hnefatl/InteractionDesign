package com.github.hnefatl.interactiondesign.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class City
{
	// URl of the OpenWeatherMap list of cities and associated info
	private static final String CITYLIST_URL = "http://openweathermap.org/help/city_list.txt";
	// Regex pattern for extracting the country code/country name pairs from the above file
	private static final String CITYLIST_PATTERN = "\\t+|\\n+|\\r+";
	
	// URL of the 3rd party resource holding a map from country code to country name
	private static final String COUNTRYCODE_URL = "https://gist.githubusercontent.com/marijn/396531/raw/5007a42db72636a9eee6efcb115fbfe348ff45ee/countries.txt";
	// Regex pattern for extracting the country code/country name pairs from the above file
	private static final String COUNTRYCODE_PATTERN = "\\n+|\\r+|\\|+";
	
	private static List<City> cities = new ArrayList<>();
	private static Map<String, String> countryCodeNameMap = new HashMap<>();
	
	private long cityId;
	private String cityName;
	private float lat;
	private float lon;
	private String countryCode;
	private String countryName;
	
	private City(long cityId, String name, float lat, float lon, String countryCode, String countryName)
	{
		this.cityId = cityId;
		this.cityName = name;
		this.lat = lat;
		this.lon = lon;
		this.countryCode = countryCode;
		this.countryName = countryName;
	}
	
	/**
	 * Load cities from remote URL and parse them into objects
	 */
	public static void LoadCities() throws MalformedURLException, DataNotFoundException, InvalidFormatException
	{
		if (!cities.isEmpty())
			return;
		
		// Will throw if something goes wrong, let exception propagate
		LoadCountries();
		
		URL citylistUrl = new URL(CITYLIST_URL);
		
		try (Scanner in = new Scanner(citylistUrl.openStream()))
		{
			in.useDelimiter(CITYLIST_PATTERN);
			cities.clear();
			
			in.nextLine(); // Discard header line
			while (in.hasNext())
			{
				try
				{
					long id = in.nextLong();
					String cityName = in.next();
					float lat = in.nextFloat();
					float lon = in.nextFloat();
					String countryCode = in.next();
					String countryName = countryCodeNameMap.get(countryCode);
					if (countryName != null)
						cities.add(new City(id, cityName, lat, lon, countryCode, countryName));
				}
				catch (NoSuchElementException e) // On an invalid line, skip to end of line
				{
					in.nextLine();
				}
			}
		}
		catch (IOException e) // Failed to open file
		{
			throw new DataNotFoundException(e);
		}
		catch (NoSuchElementException e) // Handles unexpected "no more data"
		{
			if (cities.isEmpty()) // Completely invalid
				throw new InvalidFormatException(e);
			// Otherwise, it's just an invalid row like any other, we should return what we have
		}
	}
	
	/**
	 * Loads the country code/name pairs from a specified URL
	 */
	private static void LoadCountries() throws MalformedURLException, DataNotFoundException, InvalidFormatException
	{
		if (!countryCodeNameMap.isEmpty())
			return;
		
		URL countryCodeUrl = new URL(COUNTRYCODE_URL);
		
		try (Scanner in = new Scanner(countryCodeUrl.openStream()))
		{
			in.useDelimiter(COUNTRYCODE_PATTERN);
			countryCodeNameMap.clear();
			
			while (in.hasNext())
			{
				String code = in.next();
				String name = in.next();
				countryCodeNameMap.put(code, name);
			}
		}
		catch (IOException e) // Failed to open file
		{
			throw new DataNotFoundException(e);
		}
		catch (NoSuchElementException e) // Handles both failed parsing and "no more data" 
		{
			// Invalid file format
			throw new InvalidFormatException(e);
		}
	}
	
	/**
	 * Returns an unmodifiable list of the Cities
	 */
	public static List<City> getCities()
	{
		return Collections.unmodifiableList(cities);
	}
	
	/**
	 * Returns the city ID used by OpenWeatherMap
	 */
	public long getCityId()
	{
		return cityId;
	}
	/**
	 * Returns the English name of the city
	 */
	public String getName()
	{
		return cityName;
	}
	/**
	 * Returns the latitude of the city
	 */
	public float getLat()
	{
		return lat;
	}
	/**
	 * Returns the longitude of the city
	 */
	public float getLon()
	{
		return lon;
	}
	/**
	 * Returns the country code of the city (eg. RU for Russia)
	 */
	public String getCountryCode()
	{
		return countryCode;
	}
	/**
	 * Returns the country's name in English
	 */
	public String getCountryName()
	{
		return countryName;
	}
}
