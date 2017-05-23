package com.github.hnefatl.interactiondesign;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import org.geonames.*;
import com.github.hnefatl.interactiondesign.data.City;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;

public class MainClass extends JFrame
{
	EmbeddedProc mEmbed;
	Button mAButton;
	Boolean MarkerBool= false;
	JFrame MainFrame;
	public MainClass()
	{
		super("Embedded Test");
		MainFrame= new JFrame();
		MainFrame.setSize(750, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		MainFrame.setLayout(new BorderLayout());
		mEmbed = new EmbeddedProc();
		MainFrame.add(mEmbed, BorderLayout.CENTER);
		mEmbed.init();
		
		//mAButton = new Button("A Button");
		//mAButton = new Button("A Button");
		MainFrame.add(createControlPanel(),BorderLayout.SOUTH);
		MainFrame.add(createInfoPanel(""), BorderLayout.EAST);
		MainFrame.setVisible(true);
		}
		
	
	public Component createInfoPanel(String place) {
		Panel info =  new Panel(new GridLayout(2,1));	
	   info.add(createCityPanel(place));
	   MainFrame.add(info, BorderLayout.EAST);
	    return info;
	}

	public Component createCityPanel(String place){
		Panel locations = new Panel();
		if (place==""){return locations;}
		 //GeoLookUp g= new City(place); 		 
		 //HashMap <String,Float[]> LocationList= g.getLoc();
		
		List<City> nlist= City.getCities(place);

		    //String [] namelist= new String [LocationList.size()];
		    //List nlist= new List<>();
		    //int count=0;
		   // for (String p:LocationList.keySet()){
		   // 	nlist.add(p);
		    	//count++;
		   // }
		    //System.out.println(count);
		    
		    		    
		    //nlist.addActionListener((e)-> {String w = nlist.getSelectedItem(); 
		    //Float[] cords=LocationList.get(w); mEmbed.placeMarker(cords);}); 
		    
		    ScrollPane scrollpane = new ScrollPane();
		   scrollpane.add(nlist,ScrollPane.SCROLLBARS_AS_NEEDED);
		   scrollpane.setSize(200,650);
		    locations.setSize(250,700);
		    locations.add(scrollpane);
		    System.out.println("HERE");
		    return locations; 
	}


	private Panel createControlPanel() {
	    Panel ctrl =  new Panel(new GridLayout(1,4));
	   
	    Button SearchButton=new Button("Search");
	    TextField SearchText= new TextField();	    		    
	    Button Marker=new Button("Use Marker");		    
	   // Marker.addActionListener((e)-> { mEmbed.setMarkerBool(true); System.out.println("1");});
	    
	    SearchButton.addActionListener((e)-> createInfoPanel(SearchText.getText())); 
	    ctrl.add(SearchText);
	    ctrl.add(SearchButton);
	    //ctrl.add(mPlayButton);
	    ctrl.add(Marker);
	   
	   
	    return ctrl;
	}
	
	//public boolean getMarkerBool(){
	//	return MarkerBool;
	//}
}