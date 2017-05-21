package com.github.hnefatl.interactiondesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.github.hnefatl.interactiondesign.data.City;
import com.github.hnefatl.interactiondesign.ui.IDAction;
import com.github.hnefatl.interactiondesign.ui.IDButton;
import com.github.hnefatl.interactiondesign.ui.IDClickEvent;
import com.github.hnefatl.interactiondesign.ui.IDColour;
import com.github.hnefatl.interactiondesign.ui.IDEvent;
import com.github.hnefatl.interactiondesign.ui.IDFrame;
import com.github.hnefatl.interactiondesign.ui.IDLocation;
import com.github.hnefatl.interactiondesign.ui.IDLocationFrame;
import com.github.hnefatl.interactiondesign.ui.IDPosition;
import com.github.hnefatl.interactiondesign.ui.IDRectangle;
import com.github.hnefatl.interactiondesign.ui.IDScrollFrame;
import com.github.hnefatl.interactiondesign.ui.IDSize;
import com.github.hnefatl.interactiondesign.ui.IDString;
import com.github.hnefatl.interactiondesign.ui.IDSubFrame;

public class IDMainScreen
{
	private static final IDPosition dfOffset = new IDPosition(0, 20);
	private static final IDSize dfSize = new IDSize(1242, 2148);
	
	private static final IDLocation dfLocation = new IDLocation(dfOffset, dfSize);
	public static final double dfScale = 3.0;
	
	private Locale defaultLocale;
	
	private IDApp app;
	
	private IDFrame mainFrame;
	
	private IDScrollFrame scrollFrame;
	
	private List<City> cityOrder;
	private Map<City, IDCityFrame> cityData;
	
	public IDMainScreen(IDApp app)
	{
		this(app, null);
	}
	
	public IDMainScreen(IDApp app, Locale defaultLocale)
	{
		this.app = app;
		this.defaultLocale = defaultLocale;
		
		cityOrder = new ArrayList<City>();
		cityData = new HashMap<City, IDCityFrame>();
		
		mainFrame = createFrame();
	}
	
	/**
	 * @param city City to add to data
	 * @return If city was successfully added
	 */
	
	public boolean addCity(City city)
	{
		if ((city == null) || cityOrder.contains(city))
		{
			return false;
		}
		
		cityOrder.add(city);
		cityData.put(city, new IDCityFrame(this, city, defaultLocale));
		
		updateScrollFrame();
		
		return true;
	}
	
	/**
	 * @param city City to remove from data
	 * @return If city was successfully removed
	 */
	
	public boolean removeCity(City city)
	{
		if ((city == null) || !cityOrder.contains(city))
		{
			return false;
		}
		
		cityOrder.remove(city);
		cityData.remove(city);
		
		updateScrollFrame();
		
		return true;
	}
	
	public void showCityInfo(City city)
	{
		app.showModal(constructCityModal(city));
	}
	
	private IDFrame constructCityModal(City city)
	{
		IDFrame modalFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		IDSubFrame subFrame = new IDSubFrame();
		
		subFrame.get().addComponent(new IDRectangle(new IDColour(1.0, 1.0, 1.0)), new IDLocation(IDPosition.ZERO, new IDSize(1242, 1104)));
		subFrame.get().addComponent(new IDButton(false), new IDLocation(IDPosition.ZERO, new IDSize(1242, 1104)));
		
		subFrame.get().addComponent(new IDRectangle(new IDColour(0.2, 0.2, 0.2)), new IDLocation(new IDPosition(60, 60), new IDSize(1122, 732))); // TODO: Graph
		subFrame.get().addComponent(new IDRectangle(new IDColour(1.0, 1.0, 1.0)), new IDLocation(new IDPosition(63, 63), new IDSize(1122 - 6, 732 - 6)));
		
		modalFrame.addComponent(subFrame, new IDLocation(new IDPosition(0, 552 - 30), new IDSize(1242, 1104)));
		
		return modalFrame;
	}
	
	private void updateScrollFrame()
	{
		IDPosition previousPosition = scrollFrame.getPosition();
		
		scrollFrame.clear();
		
		int i = 0;
		
		for (i = 0; i < cityOrder.size(); i++)
		{
			City city = cityOrder.get(i);
			IDCityFrame cityFrame = cityData.get(city);
			
			scrollFrame.addComponent(cityFrame.get(), new IDLocation(new IDPosition(0, 504 * i), new IDSize(1242, 504)));
		}
		
		scrollFrame.setPosition(previousPosition);
		
		scrollFrame.addClickAction(new IDAction() {
			public void onAction(IDEvent event)
			{
				if (event == null)
				{
					return;
				}
				
				IDClickEvent e = (IDClickEvent) event;
				IDPosition p = e.getPosition();
				
				int i = Math.floorDiv((int) p.y, 168);
				
				if (i < cityOrder.size())
				{
					showCityInfo(cityOrder.get(i));
				}
			}
		});
		
		mainFrame.repaint();
	}
	
	private IDFrame createFrame()
	{
		IDFrame rootFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		scrollFrame = new IDScrollFrame();
		
		rootFrame.addComponent(constructMenuBar(), new IDLocation(IDPosition.ZERO, new IDSize(1242, 132)));
		rootFrame.addComponent(scrollFrame, new IDLocation(new IDPosition(0, 132), new IDSize(1242, 2016)));
		
		return rootFrame;
	}
	
	private IDSubFrame constructMenuBar()
	{
		IDSubFrame menuBar = new IDSubFrame();
		
		menuBar.get().addComponent(new IDRectangle(new IDColour(0xF0, 0xF0, 0xF0)), new IDLocation(IDPosition.ZERO, new IDSize(1242, 132)));
		
		menuBar.get().addComponent(new IDButton(new IDString("\u2630", IDString.getDefaultFont(20.0)), new IDAction() {
			public void onAction(IDEvent e)
			{
				System.out.println("TODO: Open menu");
			}
		}), new IDLocation(new IDPosition(24, 0), new IDSize(132, 132)));
		
		menuBar.get().addComponent(new IDButton(new IDString("+", IDString.getDefaultFont(20.0)), new IDAction() {
			public void onAction(IDEvent e)
			{
				System.out.println("TODO: Add new");
				
				//mainFrame.setModal(constructDefaultModal());
				
				try
				{
					if (cityOrder.size() == 0)
					{
						addCity(City.getCity(52.2053, 0.1218));
					}
					
					else
					{
						addCity(City.getCity(51.5074, 0.1278));
					}
				}
				
				catch (Exception ex)
				{
					// TODO: error modal
					
					ex.printStackTrace();
				}
			}
		}), new IDLocation(new IDPosition(1242 - (24 + 132), 0), new IDSize(132, 132)));
		
		return menuBar;
	}
	
	public IDFrame getRaw()
	{
		return mainFrame;
	}
}
