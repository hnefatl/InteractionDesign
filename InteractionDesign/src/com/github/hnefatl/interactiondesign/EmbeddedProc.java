package com.github.hnefatl.interactiondesign;

import processing.core.PApplet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import com.github.hnefatl.interactiondesign.data.City;
import com.github.hnefatl.interactiondesign.data.CityNotFoundException;
import com.github.hnefatl.interactiondesign.data.DataNotFoundException;
import com.github.hnefatl.interactiondesign.data.InvalidFormatException;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class EmbeddedProc extends PApplet {
	UnfoldingMap map;
	String searchName = ""; // the string we want to lookup 
	SimplePointMarker MarkerTracker=null;
	
	public void setup() {
		//CREATES THE MAP AND ADD MOUSE LISTENER TO THE MAP
		size(750,500);		
		map = new UnfoldingMap(this,new OpenStreetMap.OpenStreetMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		this.addMouseListener(this);
		
		} 	
	
	public void draw() {
		//DRAWS THE MAP AND DISPLAYS SOME TEXT TO GIVE CONTEXT TO TEXTFIELD BELOW
		map.draw();
		fill(255,255,255);
		rect(0,550, 800, 50, 0);
		fill(0);
		text("Search:" , 5 , 575);
	}
	
	public void placeMarker(Float[]  cords){
		//PLACES MARKER ON MAP BASED ON INPUT OF LAT AND LONG
		fill(255,255,255);
		rect(0,550, 800, 50, 0);
		fill(0);
		text("Search:" , 5 , 575);
		Location Location = map.getLocation(cords[0], cords[1]);
		SimplePointMarker LocationMarker = new SimplePointMarker(Location);
	    LocationMarker.setColor(color(255, 0, 0, 100));
	    LocationMarker.setStrokeColor(color(255, 0, 0));
	    LocationMarker.setStrokeWeight(4);
	    map.addMarkers(LocationMarker);
	    //text("Location Coordinates "+ Location.getLat() + ", " + Location.getLon() , 550 , 575 );
	    //USE LAT LONG TO GET PLACE NAME
	    //text("Location Name"+ Location.getLat() + ", " + Location.getLon() , 50 , 575 );	
		
		
	}
	
	//public void init() {
	//	this.addMouseListener(this);
	//	}
	
	public void mouseClicked(MouseEvent e) {
		// WHEN MOUSE CLICKED, PLACES MARKER ON MAP AND SHOULD DIPLAY NAME AND COORDINATES. IT DOES DO THAT BUT THE DISPLAY VANISHES VERY QUICKLY
		//if (MarkerTracker!=null){map.deleteMarkers(MarkerTracker);} //was trying to delete any previous markers on the map, couldn't find the command
    	
        float[] cords= new float[2];
        cords[0] = e.getX();
        cords[1] = e.getY(); 
           
		Location Location = map.getLocation(cords[0], cords[1]);
		System.out.println(cords[0] +" "+ cords[1]);
	    SimplePointMarker LocationMarker = new SimplePointMarker(Location);
	    LocationMarker.setColor(color(255, 0, 0, 100));
	    LocationMarker.setStrokeColor(color(255, 0, 0));
	    LocationMarker.setStrokeWeight(4);
	    map.addMarkers(LocationMarker);
	    text("Location Corodinates "+ Location.getLat() + ", " + Location.getLon() , 550 , 575 );
	    //USE LAT LONG TO GET PLACE NAME
	    try {
			text("Location Name: "+ City.getCity(Location.getLat(), Location.getLon()), 50 , 575 );
		} catch (DataNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CityNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
    }
	
	
}