package com.github.hnefatl.interactiondesign;

import java.util.Random;

import com.github.hnefatl.interactiondesign.data.City;
import com.github.hnefatl.interactiondesign.ui.IDColour;
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
	private City city;
	
	private IDSubFrame frame;
	
	public IDCityFrame(City city)
	{
		this.city = city;
		
		frame = new IDSubFrame(new IDLocationFrame(new IDLocation(IDPosition.ZERO, new IDSize(1242, 504))));
		
		Random r = new Random();
		frame.get().addComponent(new IDRectangle(new IDColour(r.nextInt(256), r.nextInt(256), r.nextInt(256))), new IDLocation(IDPosition.ZERO, new IDSize(1242, 504)));
		
		frame.get().addComponent(new IDText(new IDString(city.getCountryName(), IDString.getDefaultFont(20.0, IDString.BOLD)), new IDColour(0xFF, 0xFF, 0xFF)), new IDLocation(new IDPosition(54, 40), new IDSize(1242, 72)));
		frame.get().addComponent(new IDText(new IDString(city.getName(), IDString.getDefaultFont(16.0)), new IDColour(0xFF, 0xFF, 0xFF)), new IDLocation(new IDPosition(54, 40 + 72 + 14), new IDSize(1242, 60)));
	}
	
	public IDSubFrame get()
	{
		return frame;
	}
}
