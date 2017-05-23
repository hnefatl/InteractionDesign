package com.github.hnefatl.interactiondesign;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.List;
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
import com.github.hnefatl.interactiondesign.data.DataNotFoundException;
import com.github.hnefatl.interactiondesign.data.InvalidFormatException;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;

public class MainClass extends JFrame
{
	EmbeddedProc mEmbed;
	JFrame MainFrame;
	public MainClass() throws DataNotFoundException, InvalidFormatException
	{
		super("Embedded Test");
		MainFrame= new JFrame();
		MainFrame.setSize(750, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		MainFrame.setLayout(new BorderLayout());
		mEmbed = new EmbeddedProc();
		MainFrame.add(mEmbed, BorderLayout.CENTER);
		mEmbed.init();
		MainFrame.add(createControlPanel(),BorderLayout.SOUTH);
		MainFrame.add(createInfoPanel(""), BorderLayout.EAST);
		createInfoPanel("");
		MainFrame.setVisible(true);
		}
		
	
	public Component createInfoPanel(String place) throws DataNotFoundException, InvalidFormatException {
		//CREATING THE PANEL WITH THE LIST OF NAMES FROM THE USER INPUT+ ANY OTHER PANELS ON THE RIGHT
		Panel info =  new Panel();	
	   info.add(createCityPanel(place));
	   if(place!=""){MainFrame.repaint();}
	    return info;
	}

	public Component createCityPanel(String place) throws DataNotFoundException, InvalidFormatException{
		//THE ACTUAL CITY LIST PANEL FROM THE USER INPUT
		Panel locations = new Panel();
		ScrollPane scrollpane = new ScrollPane();
		   
		   scrollpane.setSize(200,650);
		    locations.setSize(250,700);
		    locations.add(scrollpane);
		    System.out.println("HERE");
		if (place==""){return locations;}
		
		//SHOULD TAKE IN INPUT AND CREATE AWT LIST AND DISPLAY BUT DOESN'T :(
		List nlist= new List();
	    //int count=0;
	    for (City c: City.getCities(place)){
	    	nlist.add(c.getCityName());
	    	//count++;
	    }
		scrollpane.add(nlist,ScrollPane.SCROLLBARS_AS_NEEDED); 
		
	    return locations; 
		    
	    // SHOULD TAKE SELECTED NAME FROM LIST AND PLACE MARKER ON IT 
		    //nlist.addActionListener((e)-> {String w = nlist.getSelectedItem(); 
		    //Float[] cords={City.}; mEmbed.placeMarker(cords);}); 
		   
	}


	private Panel createControlPanel() {
		//PANEL ON THE BOTTOM TO TAKE IN USER INPUT
	    Panel ctrl =  new Panel(new GridLayout(1,4));
	   
	    Button SearchButton=new Button("Search");
	    TextField SearchText= new TextField();	    		    
	    //Button Marker=new Button("Use Marker");		    
	   // Marker.addActionListener((e)-> { mEmbed.setMarkerBool(true); System.out.println("1");});
	    
	    //TAKES IN USER INPUT AND SENDS IT TO CREATEINFOPANEL() TO UPDATE THE PANEL
	    SearchButton.addActionListener((e)-> {
			try {
				createInfoPanel(SearchText.getText());
			} catch (DataNotFoundException e1) {
				//  Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidFormatException e1) {
				//  Auto-generated catch block
				e1.printStackTrace();
			}
		}); 
	    ctrl.add(SearchText);
	    ctrl.add(SearchButton);	    
	    //ctrl.add(Marker);
	   
	   
	    return ctrl;
	}
	// public static void main(String[] args) throws DataNotFoundException, InvalidFormatException{
	//	 new MainClass();
	// }
	//public boolean getMarkerBool(){
	//	return MarkerBool;
	//}
}