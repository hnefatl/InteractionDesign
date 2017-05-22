package com.github.hnefatl.interactiondesign;

import processing.core.PApplet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class EmbeddedProc extends PApplet {
	UnfoldingMap map;
	String searchName = ""; // the string we want to lookup in geonames database
	SimplePointMarker MarkerTracker=null;
	//Boolean redraw= false;
	//boolean MarkerBool =false;
	
	ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria(); // the object we need for our search
	HashMap <String,Double[]> LocationList= new HashMap<String,Double[]>();
	
	public void setup() {
		size(750,500);		
		map = new UnfoldingMap(this,new OpenStreetMap.OpenStreetMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		this.addMouseListener(this);
		
		} 	
	
	public void draw() {
		map.draw();
		//Location location = map.getLocation(mouseX, mouseY);
		//fill(0);
		//text(location.getLat() + ", " + location.getLon()+", " + mouseX+", " + mouseY, 550, 570);
		fill(255,255,255);
		rect(0,550, 800, 50, 0);
		fill(0);
		text("Search:" , 5 , 575);
		//text("Location "+ location.getLat() + ", " + location.getLon() , 550 , 575 );		
	}
	
	public void placeMarker(Float[]  cords){
		//Location location = map.getLocation(mouseX, mouseY);
		//fill(0);
		//text(location.getLat() + ", " + location.getLon()+", " + mouseX+", " + mouseY, 550, 570);
		fill(255,255,255);
		rect(0,550, 800, 50, 0);
		fill(0);
		text("Search:" , 5 , 575);
		Location Location = map.getLocation(cords[0], cords[1]);
		//System.out.println(cords[0] +" "+ cords[1]);
	    SimplePointMarker LocationMarker = new SimplePointMarker(Location);
	    LocationMarker.setColor(color(255, 0, 0, 100));
	    LocationMarker.setStrokeColor(color(255, 0, 0));
	    LocationMarker.setStrokeWeight(4);
	    map.addMarkers(LocationMarker);
	    //text("Location Corodinates "+ Location.getLat() + ", " + Location.getLon() , 550 , 575 );
	    //USE LAT LONG TO GET PLACE NAME
	    //text("Location Name"+ Location.getLat() + ", " + Location.getLon() , 50 , 575 );	
		
		
	}
	
	//public void init() {
	//	this.addMouseListener(this);
	//	}
	
	public void mouseClicked(MouseEvent e) {
    	//if (MarkerBool==false){ return;}
		//if (MarkerTracker!=null){map.deleteMarkers(MarkerTracker);}
    	System.out.println("3");
        float[] cords= new float[2];
        cords[0] = e.getX();
        cords[1] = e.getY(); 
        //EmbdeddedProc nEmbed = new EmbdeddedProc();
        System.out.println("3");        
        System.out.println("4");        
		Location Location = map.getLocation(cords[0], cords[1]);
		System.out.println(cords[0] +" "+ cords[1]);
	    SimplePointMarker LocationMarker = new SimplePointMarker(Location);
	    LocationMarker.setColor(color(255, 0, 0, 100));
	    LocationMarker.setStrokeColor(color(255, 0, 0));
	    LocationMarker.setStrokeWeight(4);
	    map.addMarkers(LocationMarker);
	    text("Location Corodinates "+ Location.getLat() + ", " + Location.getLon() , 550 , 575 );
	    //USE LAT LONG TO GET PLACE NAME
	    text("Location Name"+ Location.getLat() + ", " + Location.getLon() , 50 , 575 );
	    //MarkerTracker= LocationMarker;
        //MarkerBool=false;
    }
	
	//public void setMarkerBool(Boolean val){
	//	 MarkerBool= val;
	//}
}