package com.github.hnefatl.interactiondesign.data;

public enum WeatherType
{
	CLEAR_DAY,
	CLEAR_NIGHT,
	RAIN,
	SNOW,
	SLEET,
	WIND,
	FOG,
	CLOUDY,
	PARTLY_CLOUDY_DAY,
	PARTLY_CLOUDY_NIGHT,
	UNKNOWN;
	
	public static WeatherType parse(String text)
	{
		// Options in version 0.10 of the darksy API are:
		// clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
		
		switch (text)
		{
		case "clear-day":			return CLEAR_DAY;
		
		case "clear-night": 		return CLEAR_NIGHT;
		
		case "rain":				return RAIN;
		
		case "snow":				return SNOW;
		
		case "sleet":				return SLEET;
		
		case "wind":				return WIND;
		
		case "fog":					return FOG;
		
		case "cloudy":				return CLOUDY;
		
		case "partly-cloudy-day":	return PARTLY_CLOUDY_DAY;
		
		case "partly-cloudy-night":	return PARTLY_CLOUDY_NIGHT;
		}
		
		return UNKNOWN;
	}
}
