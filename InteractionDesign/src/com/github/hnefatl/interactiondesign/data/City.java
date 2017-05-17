package com.github.hnefatl.interactiondesign.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
	private static final String COUNTRYCODE_URL = "https://gist.githubusercontent.com/marijn/396531/raw/5007a42db72636a9eee6efcb115fbfe348ff45ee/countries.txt";
	
	private static List<City> cities = new ArrayList<>();
	private Map<String, String> codeCountryMap = new HashMap<>();
	private Map<String, String> countryCodeMap = new HashMap<>();
	
	private int cityId;
	private String name;
	private int lat;
	private int lon;
	private String countryCode;
	
	public City(int cityId, String name, int lat, int lon, String countryCode)
	{
		this.cityId = cityId;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.countryCode = countryCode;
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
			cities.clear();
			
			in.nextLine(); // Discard header line
			while (in.hasNext())
				cities.add(new City(in.nextInt(), in.next(), in.nextInt(), in.nextInt(), in.next()));
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
	
	public static void LoadCountries() throws MalformedURLException, DataNotFoundException, InvalidFormatException
	{

	}
	
	/**
	 * Returns the city ID used by OpenWeatherMap
	 */
	public int getCityId()
	{
		return cityId;
	}
	/**
	 * Returns the English name of the city
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Returns the latitude of the city
	 */
	public int getLat()
	{
		return lat;
	}
	/**
	 * Returns the longitude of the city
	 */
	public int getLon()
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
	
}
