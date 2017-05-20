package com.github.hnefatl.interactiondesign.data;

import java.io.IOException;

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
			GeoApiContext context = new GeoApiContext();
			context.setApiKey(GOOGLE_API_KEY);
			
			GeocodingResult[] results = GeocodingApi.reverseGeocode(context, new LatLng(lat, lon)).await();
			
			// Look at each result, pull out the locality
			for (GeocodingResult r : results)
			{
				if (contains(r.types, AddressType.LOCALITY))
				{
					String country = null;
					String city = null;
					
					// Extract the town and country from the result
					for (AddressComponent a : r.addressComponents)
					{
						if (contains(a.types, AddressComponentType.LOCALITY))
							city = a.shortName;
						if (contains(a.types, AddressComponentType.COUNTRY))
							country = a.longName;
					}
					
					if (city != null && country != null)
						return new City(city, lat, lon, country);
				}
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
	private static <T> boolean contains(T[] items, T value)
	{
		for (T i : items)
		{
			if (i.equals(value))
				return true;
		}
		return false;
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
}
