package com.github.hnefatl.interactiondesign;

import java.awt.TextField;
import java.util.HashMap;

import org.geonames.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class GeoLookUp  extends PApplet {
	
	UnfoldingMap map;
	String searchName = ""; // the string we want to lookup in geonames database
	ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria(); // the object we need for our search
	HashMap <String,Float[]> LocationList= new HashMap<String,Float[]>();
	
	public GeoLookUp(String place){
		searchName=place;
		setup();
	}
	public void setup() {
		//size(800,600);
		WebService.setUserName("team5"); // add your username here
		searchCriteria.setQ(searchName); // setup the main search term, in our case "berlin"
		try {
			ToponymSearchResult searchResult = WebService.search(searchCriteria); // a toponym search result as returned by the geonames webservice.
		
		for (Toponym toponym : searchResult.getToponyms()) {
			println(toponym.getName() + " " + toponym.getCountryName()
			+ " " + toponym.getLongitude() + " "
					+ toponym.getLatitude()); // prints the search results. We have access on certain get-Functions. In our Case the Name, Country, Longitude and Latitude
			String Place= toponym.getName() + " " + toponym.getCountryName();
			Float[] PlaceCords= {(float) toponym.getLongitude(),(float) toponym.getLatitude()};
			LocationList.put(Place,PlaceCords);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}	
	
	public HashMap <String, Float[]> getLoc(){
		if (searchName==""){return null;}		
		return LocationList;
	}
	}
