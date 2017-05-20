package com.github.hnefatl.interactiondesign;

import java.util.Locale;
import java.util.Random;

import com.github.hnefatl.interactiondesign.data.City;
import com.github.hnefatl.interactiondesign.ui.IDAction;
import com.github.hnefatl.interactiondesign.ui.IDButton;
import com.github.hnefatl.interactiondesign.ui.IDColour;
import com.github.hnefatl.interactiondesign.ui.IDEvent;
import com.github.hnefatl.interactiondesign.ui.IDLocation;
import com.github.hnefatl.interactiondesign.ui.IDLocationFrame;
import com.github.hnefatl.interactiondesign.ui.IDPosition;
import com.github.hnefatl.interactiondesign.ui.IDRectangle;
import com.github.hnefatl.interactiondesign.ui.IDSize;
import com.github.hnefatl.interactiondesign.ui.IDString;
import com.github.hnefatl.interactiondesign.ui.IDSubFrame;
import com.github.hnefatl.interactiondesign.ui.IDText;

@SuppressWarnings("unused")
public class IDCityFrame
{
	private static IDColour white = new IDColour(0xFF, 0xFF, 0xFF);
	
	private City city;
	private Locale locale;
	
	private IDSubFrame frame;
	
	public IDCityFrame(IDMainScreen mainScreen, City city)
	{
		this(mainScreen, city, null);
	}
	
	public IDCityFrame(IDMainScreen mainScreen, City city, Locale locale)
	{
		this.city = city;
		this.locale = locale;
		
		frame = new IDSubFrame(new IDLocationFrame(new IDLocation(IDPosition.ZERO, new IDSize(1242, 504))));
		
		frame.get().addComponent(new IDButton(new IDAction() {
			public void onAction(IDEvent e)
			{
				mainScreen.showCityInfo(city);
			}
		}, false), new IDLocation(IDPosition.ZERO, new IDSize(1242, 504)));
		
		frame.get().addComponent(constructWeatherBackground(), new IDLocation(IDPosition.ZERO, new IDSize(1242, 504)));
		frame.get().addComponent(constructWeatherIcon(), new IDLocation(new IDPosition(74, 174), new IDSize(284, 284)));
		
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
		
		infoFrame.get().addComponent(new IDText(new IDString("10°", IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(540, 0), new IDSize(1242, 60)));
		infoFrame.get().addComponent(new IDText(new IDString("10°", IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(540, 81), new IDSize(1242, 60)));
		infoFrame.get().addComponent(new IDText(new IDString("22%", IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(540, 162), new IDSize(1242, 60)));
		infoFrame.get().addComponent(new IDText(new IDString("2 mph", IDString.getDefaultFont(15.0)), white), new IDLocation(new IDPosition(540, 243), new IDSize(1242, 60)));
		
		return infoFrame;
	}
	
	private IDRectangle constructWeatherBackground()
	{
		Random r = new Random();
		
		return new IDRectangle(new IDColour(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
	}
	
	private IDRectangle constructWeatherIcon()
	{
		return new IDRectangle(new IDColour(0xC2, 0xA8, 0xC2));
	}
	
	public IDSubFrame get()
	{
		return frame;
	}
}
