package com.github.hnefatl.interactiondesign.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class City
{
	// API key for the Google Geocoding API (registered under Keith's account)
	private static final String GOOGLE_API_KEY = "AIzaSyAYSQffkovQev2FudqSKLVkhelDmycilkw";
	
	// Need to keep only one reference alive (API recommends)
	private static GeoApiContext context;
	
	private String cityName;
	private double lat;
	private double lon;
	private String countryName;
	
	private City(String name, double lat, double lon, String countryName)
	{
		this.cityName = name;
		this.lat = lat;
		this.lon = lon;
		this.countryName = countryName;
	}
	
	/**
	 * Returns the city closest to the given coordinates 
	 */
	public static City getCity(double lat, double lon) throws DataNotFoundException, InvalidFormatException, CityNotFoundException
	{
		try
		{			
			GeocodingResult[] results = GeocodingApi.reverseGeocode(getContext(), new LatLng(lat, lon)).await();
			
			// Look at each result, pull out the locality
			for (GeocodingResult r : results)
			{
				try
				{
					City c = parse(r);
					
					return c;
				}
				catch (CityNotFoundException e) { } // Ignore, we have other results to try
			}
			
			// City couldn't be found
			throw new CityNotFoundException();
		}
		catch (IOException|InterruptedException e) // Network error
		{
			throw new DataNotFoundException(e);
		}
		catch (ApiException e) // Returned data was invalid
		{
			throw new InvalidFormatException(e);
		}
	}
	
	/**
	 * Returns the list of cities matching the specified query
	 */
	public static List<City> getCities(String locationName) throws DataNotFoundException, InvalidFormatException
	{	
		try
		{
			List<City> cities = new ArrayList<>();
			
			GeocodingResult[] results = GeocodingApi.geocode(getContext(), locationName).await();
			for (GeocodingResult r : results)
			{
				try
				{
					City c = parse(r);
					cities.add(c);
				}
				catch (CityNotFoundException e) { } // Ignore, there's more cities to try
			}
			
			return cities;
		}
		catch (IOException|InterruptedException e) // Network error
		{
			throw new DataNotFoundException(e);
		}
		catch (ApiException e) // Returned data was invalid
		{
			throw new InvalidFormatException(e);
		} 
	}
	
	private static City parse(GeocodingResult data) throws CityNotFoundException
	{
		//if (contains(data.types, AddressType.LOCALITY))
		//{
			String country = null;
			String city = null;
			
			// Extract the town and country from the result
			for (AddressComponent a : data.addressComponents)
			{
				if (contains(a.types, AddressComponentType.LOCALITY) || contains(a.types, AddressComponentType.POSTAL_TOWN))
					city = a.shortName;
				if (contains(a.types, AddressComponentType.COUNTRY))
					country = a.longName;
			}
			
			if (city != null && country != null)
				return new City(city, data.geometry.location.lat, data.geometry.location.lng, country);
		//}
		
		throw new CityNotFoundException();
	}
	private static <T> boolean contains(T[] items, T value)
	{
		for (T i : items)
		{
			if (i.equals(value))
				return true;
		}
		return false;
	}
	
	private static GeoApiContext getContext()
	{
		if (context == null)
			context = new GeoApiContext().setApiKey(GOOGLE_API_KEY);
		return context;
	}
	
	/**
	 * Returns the English name of the city
	 */
	public String getCityName()
	{
		return cityName;
	}
	/**
	 * Returns the latitude of the city
	 */
	public double getLat()
	{
		return lat;
	}
	/**
	 * Returns the longitude of the city
	 */
	public double getLon()
	{
		return lon;
	}
	/**
	 * Returns the country's name in English
	 */
	public String getCountryName()
	{
		return countryName;
	}
	
	@Override
	public String toString()
	{
		return 	getCityName() + ", " + getCountryName() + "\n" +
				"Lat: " + getLat() + "\tLon: " + getLon();
	}
}
