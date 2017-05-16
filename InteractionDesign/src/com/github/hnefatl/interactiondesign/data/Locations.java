package com.github.hnefatl.interactiondesign.data;

import java.util.List;
import java.util.Map;

public class Locations
{
	private final String CITYLIST_URL = "http://openweathermap.org/help/city_list.txt";
	
	private static Map<String, List<String>> countryCityMap;
	private static Map<String, Coord> cityLocations;
	
	public static void Update() throws DataNotFoundException, InvalidFormatException
	{
		
	}
	
}
