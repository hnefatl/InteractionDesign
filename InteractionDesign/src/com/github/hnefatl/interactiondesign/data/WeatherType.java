package com.github.hnefatl.interactiondesign.data;

public enum WeatherType
{
	CLEAR,
	RAIN,
	SNOW,
	SLEET,
	WIND,
	FOG,
	CLOUDY,
	PARTLY_CLOUDY,
	UNKNOWN;
	
	public static WeatherType parse(String text)
	{
		// Options in version 0.10 of the darksy API are:
		// clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
		
		switch (text)
		{
		case "clear-day":
		case "clear-night": 		return CLEAR;
		
		case "rain":				return RAIN;
		
		case "snow":				return SNOW;
		
		case "sleet":				return SLEET;
		
		case "wind":				return WIND;
		
		case "fog":					return FOG;
		
		case "cloudy":				return CLOUDY;
		
		case "partly-cloudy-day":
		case "partly-cloudy-night":	return PARTLY_CLOUDY;
		}
		
		return UNKNOWN;
	}
}
