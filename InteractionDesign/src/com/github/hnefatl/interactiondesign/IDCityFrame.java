package com.github.hnefatl.interactiondesign;

import java.text.DecimalFormat;
import java.util.Locale;

import com.github.hnefatl.interactiondesign.data.City;
import com.github.hnefatl.interactiondesign.data.DataNotFoundException;
import com.github.hnefatl.interactiondesign.data.WeatherData;
import com.github.hnefatl.interactiondesign.data.WeatherType;
import com.github.hnefatl.interactiondesign.ui.IDColour;
import com.github.hnefatl.interactiondesign.ui.IDIcon;
import com.github.hnefatl.interactiondesign.ui.IDImage;
import com.github.hnefatl.interactiondesign.ui.IDLocation;
import com.github.hnefatl.interactiondesign.ui.IDLocationFrame;
import com.github.hnefatl.interactiondesign.ui.IDPosition;
import com.github.hnefatl.interactiondesign.ui.IDRectangle;
import com.github.hnefatl.interactiondesign.ui.IDSize;
import com.github.hnefatl.interactiondesign.ui.IDString;
import com.github.hnefatl.interactiondesign.ui.IDSubFrame;
import com.github.hnefatl.interactiondesign.ui.IDText;

public class IDCityFrame
{
	private static IDColour white = new IDColour(0xFF, 0xFF, 0xFF);
	
	private static IDColour clearDayColour = new IDColour(0x87, 0xCE, 0xFA);
	private static IDColour clearNightColour = new IDColour(0x25, 0x30, 0x4F);
	private static IDColour rainColour = new IDColour(0x34, 0x34, 0x34);
	private static IDColour snowColour = rainColour;
	private static IDColour sleetColour = rainColour;
	private static IDColour windColour = new IDColour(0x92, 0x92, 0x92);
	private static IDColour fogColour = windColour;
	private static IDColour cloudyColour = new IDColour(0x53, 0x53, 0x53);
	private static IDColour partlyCloudyDayColour = clearDayColour;
	private static IDColour partlyCloudyNightColour = clearNightColour;
	
	private static IDImage clearDayImage = new IDImage("resources/icons/IDIcons_Sun.png");
	private static IDImage clearNightImage = new IDImage("resources/icons/IDIcons_Moon.png");
	private static IDImage rainImage = new IDImage("resources/icons/IDIcons_Rain.png");
	private static IDImage snowImage = new IDImage("resources/icons/IDIcons_Snow.png");
	private static IDImage sleetImage = new IDImage("resources/icons/IDIcons_Sleet.png");
	private static IDImage windImage = new IDImage("resources/icons/IDIcons_Wind.png");
	private static IDImage fogImage = new IDImage("resources/icons/IDIcons_Fog.png");
	private static IDImage cloudyImage = new IDImage("resources/icons/IDIcons_Cloud.png");
	private static IDImage partlyCloudyDayImage = new IDImage("resources/icons/IDIcons_PartialSun.png");
	private static IDImage partlyCloudyNightImage = new IDImage("resources/icons/IDIcons_PartialMoon.png");
	
	private City city;
	private Locale locale;
	
	private IDSubFrame frame;
	
	private WeatherData currentWeather;
	private WeatherType weatherType;
	
	public IDCityFrame(IDMainScreen mainScreen, City city)
	{
		this(mainScreen, city, null);
	}
	
	public IDCityFrame(IDMainScreen mainScreen, City city, Locale locale)
	{
		this.city = city;
		this.locale = locale;
		
		currentWeather = null;
		weatherType = WeatherType.UNKNOWN;
		
		try
		{
			currentWeather = WeatherData.getCurrentWeather(this.city);
			
			if (currentWeather != null)
			{
				weatherType = currentWeather.getWeatherType();
			}
		}
		
		catch (DataNotFoundException e)
		{
			// TODO: Error modal
			
			e.printStackTrace();
		}
		
		frame = new IDSubFrame(new IDLocationFrame(new IDLocation(IDPosition.ZERO, new IDSize(1242, 504))));
		
		frame.get().addComponent(constructWeatherBackground(), new IDLocation(IDPosition.ZERO, new IDSize(1242, 504)));
		
		IDIcon weatherIcon = constructWeatherIcon();
		
		if (weatherIcon != null)
		{
			frame.get().addComponent(weatherIcon, new IDLocation(new IDPosition(74 + 30, 174 + 30), new IDSize(284 - 60, 284 - 60)));
		}
		
		frame.get().addComponent(new IDText(new IDString(city.getCountryName(), IDString.getDefaultFont(16.0, IDString.BOLD)), white), new IDLocation(new IDPosition(54, 24), new IDSize(1242, 72)));
		frame.get().addComponent(new IDText(new IDString(city.getCityName(), IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(54, 24 + 72), new IDSize(1242, 60)));
		
		frame.get().addComponent(constructInfoSubFrame(), new IDLocation(new IDPosition(523, 152), new IDSize(680, 300)));
	}
	
	private IDSubFrame constructInfoSubFrame()
	{
		IDSubFrame infoFrame = new IDSubFrame();
		
		infoFrame.get().addComponent(new IDText(new IDString(IDLocaleManager.get("ui.temperature", locale), IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(0, 0), new IDSize(1242, 60)));
		infoFrame.get().addComponent(new IDText(new IDString(IDLocaleManager.get("ui.feels_like", locale), IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(0, 81), new IDSize(1242, 60)));
		infoFrame.get().addComponent(new IDText(new IDString(IDLocaleManager.get("ui.rain_chance", locale), IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(0, 162), new IDSize(1242, 60)));
		infoFrame.get().addComponent(new IDText(new IDString(IDLocaleManager.get("ui.wind_speed", locale), IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(0, 243), new IDSize(1242, 60)));
		
		String temperature = "N/A";
		String feelsLike = "N/A";
		String rainChance = "N/A";
		String windSpeed = "N/A";
		
		if (currentWeather != null)
		{
			DecimalFormat dFormat = new DecimalFormat("#,##0");
			
			temperature = dFormat.format(currentWeather.getTemperature()) + " " + currentWeather.getTemperatureUnits();
			feelsLike = dFormat.format(currentWeather.getApparentTemperature()) + " " + currentWeather.getTemperatureUnits();
			rainChance = dFormat.format(currentWeather.getRainChance()) + " " + currentWeather.getRainChanceUnits();
			windSpeed = dFormat.format(currentWeather.getWindSpeed()) + " " + currentWeather.getWindUnits();
		}
		
		infoFrame.get().addComponent(new IDText(new IDString(temperature, IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(540 - 45, 0), new IDSize(1242, 60)));
		infoFrame.get().addComponent(new IDText(new IDString(feelsLike, IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(540 - 45, 81), new IDSize(1242, 60)));
		infoFrame.get().addComponent(new IDText(new IDString(rainChance, IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(540 - 45, 162), new IDSize(1242, 60)));
		infoFrame.get().addComponent(new IDText(new IDString(windSpeed, IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(540 - 45, 243), new IDSize(1242, 60)));
		
		return infoFrame;
	}
	
	private IDRectangle constructWeatherBackground()
	{
		IDColour weatherColour = null;
		
		switch (weatherType)
		{
		case CLEAR_DAY:
			weatherColour = clearDayColour;
			break;
			
		case CLEAR_NIGHT:
			weatherColour = clearNightColour;
			break;
			
		case RAIN:
			weatherColour = rainColour;
			break;
			
		case SNOW:
			weatherColour = snowColour;
			break;
			
		case SLEET:
			weatherColour = sleetColour;
			break;
			
		case WIND:
			weatherColour = windColour;
			break;
			
		case FOG:
			weatherColour = fogColour;
			break;
			
		case CLOUDY:
			weatherColour = cloudyColour;
			break;
			
		case PARTLY_CLOUDY_DAY:
			weatherColour = partlyCloudyDayColour;
			break;
			
		case PARTLY_CLOUDY_NIGHT:
			weatherColour = partlyCloudyNightColour;
			break;
			
		default:
			weatherColour = new IDColour(0xC2, 0xA8, 0xC2);
		}
		
		return new IDRectangle(weatherColour);
	}
	
	private IDIcon constructWeatherIcon()
	{
		IDIcon weatherIcon = null;
		
		switch (weatherType)
		{
		case CLEAR_DAY:
			weatherIcon = new IDIcon(clearDayImage);
			break;
			
		case CLEAR_NIGHT:
			weatherIcon = new IDIcon(clearNightImage);
			break;
			
		case RAIN:
			weatherIcon = new IDIcon(rainImage);
			break;
			
		case SNOW:
			weatherIcon = new IDIcon(snowImage);
			break;
			
		case SLEET:
			weatherIcon = new IDIcon(sleetImage);
			break;
			
		case WIND:
			weatherIcon = new IDIcon(windImage);
			break;
			
		case FOG:
			weatherIcon = new IDIcon(fogImage);
			break;
			
		case CLOUDY:
			weatherIcon = new IDIcon(cloudyImage);
			break;
			
		case PARTLY_CLOUDY_DAY:
			weatherIcon = new IDIcon(partlyCloudyDayImage);
			break;
			
		case PARTLY_CLOUDY_NIGHT:
			weatherIcon = new IDIcon(partlyCloudyNightImage);
			break;
			
		default:
			// TODO: Maybe use question mark image?
			
			weatherIcon = null;
			break;
		}
		
		return weatherIcon;
	}
	
	public IDSubFrame get()
	{
		return frame;
	}
}
